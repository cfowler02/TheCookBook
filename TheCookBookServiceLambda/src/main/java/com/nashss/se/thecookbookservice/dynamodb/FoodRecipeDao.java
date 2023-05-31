package com.nashss.se.thecookbookservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.metrics.MetricsPublisher;

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

        /*if (foodRecipe == null){
            metricsPublisher.addCount();
            throw new
        }

        */
        return foodRecipe;
    }
}
