package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.CreateDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.CreateDrinkRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;

import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateDrinkRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public CreateDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public CreateDrinkRecipeResult handleRequest(final CreateDrinkRecipeRequest createDrinkRecipeRequest) {
        log.info("Received CreateDrinkRecipeRequest {}", createDrinkRecipeRequest);

        DrinkRecipe newDrinkRecipe = new DrinkRecipe();
        newDrinkRecipe.setCreator(createDrinkRecipeRequest.getCreator());
        newDrinkRecipe.setRecipeTitle(createDrinkRecipeRequest.getRecipeTitle());
        newDrinkRecipe.setIngredients(createDrinkRecipeRequest.getIngredients());
        newDrinkRecipe.setInstructionSteps(createDrinkRecipeRequest.getInstructionSteps());
        newDrinkRecipe.setDescription(createDrinkRecipeRequest.getDescription());
        newDrinkRecipe.setDescriptionTags(createDrinkRecipeRequest.getDescriptionTags());
        newDrinkRecipe.setDrinkCategory(createDrinkRecipeRequest.getDrinkCategory());
        newDrinkRecipe.setDrinkItem(createDrinkRecipeRequest.getDrinkItem());
        newDrinkRecipe.setAllergies(createDrinkRecipeRequest.getAllergies());
        newDrinkRecipe.setRatings(createDrinkRecipeRequest.getRatings());

        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(newDrinkRecipe);
        return CreateDrinkRecipeResult.builder()
                .withDrinkRecipe(drinkRecipeModel)
                .build();
    }
}
