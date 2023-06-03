package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.CreateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.CreateFoodRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
//import com.nashss.se.thecookbookservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;

//import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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

        //if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName())) {
        //    throw new InvalidAttributeValueException("Playlist name [" + createPlaylistRequest.getName() +
        //            "] contains illegal characters");
        //}

        //if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
        //    throw new InvalidAttributeValueException("Playlist customer ID [" + createPlaylistRequest.getCustomerId() +
        //            "] contains illegal characters");
        //}

        DrinkRecipe newDrinkRecipe = new DrinkRecipe;
        newDrinkRecipe.setCreator(createFoodRecipeRequest.getCreator());
        newDrinkRecipe.setRecipeTitle(createFoodRecipeRequest.getRecipeTitle());
        newDrinkRecipe.setIngredients(createFoodRecipeRequest.getIngredients());
        newDrinkRecipe.setInstructionSteps(createFoodRecipeRequest.getInstructionSteps());
        newDrinkRecipe.setDescription(createFoodRecipeRequest.getDescription());
        newDrinkRecipe.setDescriptionTags(createFoodRecipeRequest.getDescriptionTags());
        newDrinkRecipe.setFoodCategory(createFoodRecipeRequest.getFoodCategory());
        newDrinkRecipe.setFoodItem(createFoodRecipeRequest.getFoodItem());
        newDrinkRecipe.setAllergies(createFoodRecipeRequest.getAllergies());
        newDrinkRecipe.setRatings(createFoodRecipeRequest.getRatings());

        DrinkRecipeModel drinkRecipeModel = new ModelConverter().toDrinkRecipeModel(newDrinkRecipe);
        return CreateDrinkRecipeResult.builder()
                .withDrinkRecipe(drinkRecipeModel)
                .build();
    }
}