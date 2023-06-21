package com.nashss.se.thecookbookservice.converters;

import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    public FoodRecipeModel toFoodRecipeModel(FoodRecipe foodRecipe) {
        return FoodRecipeModel.builder()
                .withCreator(foodRecipe.getCreator())
                .withRecipeTitle(foodRecipe.getRecipeTitle())
                .withIngredients(foodRecipe.getIngredients())
                .withInstructionSteps(foodRecipe.getInstructionSteps())
                .withDescription(foodRecipe.getDescription())
                .withDescriptionTags(foodRecipe.getDescriptionTags())
                .withTimeEstimate(foodRecipe.getTimeEstimate())
                .withFoodCategory(foodRecipe.getFoodCategory())
                .withFoodItem(foodRecipe.getFoodItem())
                .withAllergies(foodRecipe.getAllergies())
                .withRatings(foodRecipe.getRatings())
                .build();
    }

    public List<FoodRecipeModel> toFoodRecipeModelList(List<FoodRecipe> foodRecipes) {
        List<FoodRecipeModel> foodRecipeModels = new ArrayList<>();

        for (FoodRecipe f : foodRecipes) {
            foodRecipeModels.add(toFoodRecipeModel(f));
        }

        return foodRecipeModels;
    }

    public DrinkRecipeModel toDrinkRecipeModel(DrinkRecipe drinkRecipe) {
        return DrinkRecipeModel.builder()
                .withCreator(drinkRecipe.getCreator())
                .withRecipeTitle(drinkRecipe.getRecipeTitle())
                .withIngredients(drinkRecipe.getIngredients())
                .withInstructionSteps(drinkRecipe.getInstructionSteps())
                .withDescription(drinkRecipe.getDescription())
                .withDescriptionTags(drinkRecipe.getDescriptionTags())
                .withDrinkCategory(drinkRecipe.getDrinkCategory())
                .withDrinkItem(drinkRecipe.getDrinkItem())
                .withAllergies(drinkRecipe.getAllergies())
                .withRatings(drinkRecipe.getRatings())
                .build();
    }

    public List<DrinkRecipeModel> toDrinkRecipeModelList(List<DrinkRecipe> drinkRecipes) {
        List<DrinkRecipeModel> drinkRecipeModels = new ArrayList<>();

        for (DrinkRecipe d : drinkRecipes) {
            drinkRecipeModels.add(toDrinkRecipeModel(d));
        }

        return drinkRecipeModels;
    }
}
