package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.ViewFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.ViewFoodRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class ViewFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public ViewFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public ViewFoodRecipeResult handleRequest(final ViewFoodRecipeRequest viewFoodRecipeRequest) {
        log.info("Received ViewFoodRecipeActivity {}", viewFoodRecipeRequest);

        List<FoodRecipe> results = foodRecipeDao.viewFoodRecipe();
        List<FoodRecipeModel> foodRecipeModels = new ModelConverter().toFoodRecipeModelList(results);

        return ViewFoodRecipeResult.builder()
                .withFoodRecipeModels(foodRecipeModels)
                .build();
    }
}
