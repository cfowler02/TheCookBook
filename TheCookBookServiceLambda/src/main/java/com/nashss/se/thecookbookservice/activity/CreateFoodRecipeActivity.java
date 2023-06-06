package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.CreateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.CreateFoodRecipeResult;
import com.nashss.se.thecookbookservice.converters.ModelConverter;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
//import com.nashss.se.thecookbookservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;

//import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

public class CreateFoodRecipeActivity {
    private final Logger log = LogManager.getLogger();
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public CreateFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public CreateFoodRecipeResult handleRequest(final CreateFoodRecipeRequest createFoodRecipeRequest) {
        log.info("Received CreateFoodRecipeRequest {}", createFoodRecipeRequest);

        //if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName())) {
        //    throw new InvalidAttributeValueException("Playlist name [" + createPlaylistRequest.getName() +
        //            "] contains illegal characters");
        //}

        //if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
        //    throw new InvalidAttributeValueException("Playlist customer ID [" + createPlaylistRequest.getCustomerId() +
        //            "] contains illegal characters");
        //}

        FoodRecipe newFoodRecipe = new FoodRecipe();
        newFoodRecipe.setCreator(createFoodRecipeRequest.getCreator());
        newFoodRecipe.setRecipeTitle(createFoodRecipeRequest.getRecipeTitle());
        newFoodRecipe.setIngredients(createFoodRecipeRequest.getIngredients());
        newFoodRecipe.setInstructionSteps(createFoodRecipeRequest.getInstructionSteps());
        newFoodRecipe.setDescription(createFoodRecipeRequest.getDescription());
        newFoodRecipe.setDescriptionTags(createFoodRecipeRequest.getDescriptionTags());
        newFoodRecipe.setTimeEstimate(createFoodRecipeRequest.getTimeEstimate());
        newFoodRecipe.setFoodCategory(createFoodRecipeRequest.getFoodCategory());
        newFoodRecipe.setFoodItem(createFoodRecipeRequest.getFoodItem());
        newFoodRecipe.setAllergies(createFoodRecipeRequest.getAllergies());
        newFoodRecipe.setRatings(createFoodRecipeRequest.getRatings());

        FoodRecipeModel foodRecipeModel = new ModelConverter().toFoodRecipeModel(newFoodRecipe);
        return CreateFoodRecipeResult.builder()
                .withFoodRecipe(foodRecipeModel)
                .build();
    }
}