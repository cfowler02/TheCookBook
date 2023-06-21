package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.GetDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.GetDrinkRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetDrinkRecipeActivity {

    private final Logger log = LogManager.getLogger();

    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public GetDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public GetDrinkRecipeResult handleRequest(final GetDrinkRecipeRequest getDrinkRecipeRequest) {
        log.info("Received GetDrinkRecipeRequest {}", getDrinkRecipeRequest);
        String creator = getDrinkRecipeRequest.getCreator();
        String recipeTitle = getDrinkRecipeRequest.getRecipeTitle();
        DrinkRecipe drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);
        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(drinkRecipe);

        return GetDrinkRecipeResult.builder()
                .withDrinkRecipe(drinkRecipeModel)
                .build();
    }
}
