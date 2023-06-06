package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.CreateDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.CreateDrinkRecipeResult;
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