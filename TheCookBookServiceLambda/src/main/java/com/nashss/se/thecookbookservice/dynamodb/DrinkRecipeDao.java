package com.nashss.se.thecookbookservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.exceptions.DrinkRecipeNotFoundException;
import com.nashss.se.thecookbookservice.metrics.MetricsConstants;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DrinkRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public DrinkRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public DrinkRecipe getDrinkRecipe(String creator, String recipeTitle) {
        DrinkRecipe drinkRecipe = this.dynamoDBMapper.load(DrinkRecipe.class, creator, recipeTitle);

        if (drinkRecipe == null) {
            metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 1);
            throw new DrinkRecipeNotFoundException("Could not find drink recipe with creator and title " +
                    creator + recipeTitle);
        }
        metricsPublisher.addCount(MetricsConstants.GETDRINKRECIPE_DRINKRECIPENOTFOUND_COUNT, 0);
        return drinkRecipe;
    }

    public DrinkRecipe saveDrinkRecipe(DrinkRecipe drinkRecipe) {
        this.dynamoDBMapper.save(drinkRecipe);
        return drinkRecipe;
    }

    public List<DrinkRecipe> searchDrinkRecipe(String filter, String criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        DynamoDBQueryExpression<DrinkRecipe> dynamoDBQueryExpression = new DynamoDBQueryExpression<DrinkRecipe>();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":criteria", new AttributeValue().withS(criteria));
        List<DrinkRecipe> recipeList = new ArrayList<>();

        if (filter == "Creator"){
            dynamoDBQueryExpression.withKeyConditionExpression("creator = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.query(DrinkRecipe.class, dynamoDBQueryExpression));
        } else if (filter == "RecipeTitle") {
            dynamoDBScanExpression.withFilterExpression("contains(recipe_title, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter == "Ingredient") {
            dynamoDBScanExpression.withFilterExpression("contains(ingredients, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter == "DescriptionTag") {
            dynamoDBScanExpression.withFilterExpression("contains(description_tags, :criteria)")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        } else if (filter == "DrinkCategory") {
            //dynamoDBScanExpression.withFilterExpression("drink_category = :criteria")
            //       .withExpressionAttributeValues(valueMap);
            dynamoDBQueryExpression.withIndexName("DrinkCategoryIndex")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("drink_category = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.query(DrinkRecipe.class, dynamoDBQueryExpression));
        } else if (filter == "DrinkItem") {
            //dynamoDBScanExpression.withFilterExpression("drink_item = :criteria")
            //        .withExpressionAttributeValues(valueMap);
            dynamoDBQueryExpression.withIndexName("DrinkItemIndex")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("drink_item = :criteria")
                    .withExpressionAttributeValues(valueMap);
            recipeList.addAll(dynamoDBMapper.query(DrinkRecipe.class, dynamoDBQueryExpression));
            //recipeList.addAll(dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression));
        }

        return recipeList;
    }

    public List<DrinkRecipe> viewDrinkRecipe() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return this.dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression);
    }
}
