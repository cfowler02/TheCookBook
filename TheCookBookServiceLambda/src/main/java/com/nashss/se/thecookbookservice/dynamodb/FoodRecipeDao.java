package com.nashss.se.thecookbookservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.exceptions.FoodRecipeNotFoundException;
import com.nashss.se.thecookbookservice.metrics.MetricsConstants;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class FoodRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public FoodRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public FoodRecipe getFoodRecipe(String creator, String recipeTitle) {
        FoodRecipe foodRecipe = this.dynamoDBMapper.load(FoodRecipe.class, creator, recipeTitle);

        if (foodRecipe == null) {
            metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 1);
            throw new FoodRecipeNotFoundException("Could not find food recipe with creator and title " +
                    creator + recipeTitle);
        }
        metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 0);
        return foodRecipe;
    }

    public FoodRecipe saveFoodRecipe(FoodRecipe foodRecipe) {
        this.dynamoDBMapper.save(foodRecipe);
        return foodRecipe;
    }

    public List<FoodRecipe> searchFoodRecipe(String criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();


        //return this.dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression);
        return null;
    }

    public List<FoodRecipe> viewFoodRecipe() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();


        return this.dynamoDBMapper.scan(FoodRecipe.class, dynamoDBScanExpression);
    }
}
