package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.ViewDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.ViewDrinkRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class ViewDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public ViewDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public ViewDrinkRecipeResult handleRequest(final ViewDrinkRecipeRequest viewDrinkRecipeRequest) {
        log.info("Received ViewDrinkRecipeActivity {}", viewDrinkRecipeRequest);

        List<DrinkRecipe> results = drinkRecipeDao.viewDrinkRecipe();
        List<DrinkRecipeModel> drinkRecipeModels = new ModelConverter().toDrinkRecipeModelList(results);

        return ViewDrinkRecipeResult.builder()
                .withDrinkRecipeModels(drinkRecipeModels)
                .build();
    }
}
