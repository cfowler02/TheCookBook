package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.GetFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.GetFoodRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetFoodRecipeActivity {

    private final Logger log = LogManager.getLogger();

    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public GetFoodRecipeActivity (FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public GetFoodRecipeResult handleRequest(final GetFoodRecipeRequest getFoodRecipeRequest) {
        log.info("Received GetFoodRecipeRequest {}", getFoodRecipeRequest);
        String creator = getFoodRecipeRequest.getCreator();
        String recipeTitle = getFoodRecipeRequest.getRecipeTitle();
        FoodRecipe foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);
        FoodRecipeModel foodRecipeModel = new ModelConverter().toFoodRecipeModel(foodRecipe);

        return GetFoodRecipeResult.builder()
                .withFoodRecipe(foodRecipeModel)
                .build();
    }
}
