package com.nashss.se.thecookbookservice.dynamodb;

import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.metrics.MetricsConstants;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.nashss.se.thecookbookservice.exceptions.FoodRecipeNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FoodRecipeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public FoodRecipeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public FoodRecipe getFoodRecipe(String creator, String recipe_title){
        FoodRecipe foodRecipe = this.dynamoDBMapper.load(FoodRecipe.class, creator, recipe_title);

        if (foodRecipe == null){
            metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 1);
            throw new FoodRecipeNotFoundException("Could not find food recipe with creator and title " + creator + recipe_title);
        }
        metricsPublisher.addCount(MetricsConstants.GETFOODRECIPE_FOODRECIPENOTFOUND_COUNT, 0);
        return foodRecipe;
    }

    public FoodRecipe saveFoodRecipe(FoodRecipe foodRecipe){
        this.dynamoDBMapper.save(foodRecipe);
        return foodRecipe;
    }
}
