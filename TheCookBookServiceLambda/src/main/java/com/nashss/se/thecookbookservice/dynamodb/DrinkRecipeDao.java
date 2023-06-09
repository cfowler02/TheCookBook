package com.nashss.se.thecookbookservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.exceptions.DrinkRecipeNotFoundException;
import com.nashss.se.thecookbookservice.metrics.MetricsConstants;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

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

    public List<DrinkRecipe> searchDrinkRecipe(String criteria) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();


        return this.dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression);
    }

    public List<DrinkRecipe> viewDrinkRecipe() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();


        return this.dynamoDBMapper.scan(DrinkRecipe.class, dynamoDBScanExpression);
    }
}
