package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.RateDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.RateDrinkRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;
import com.nashss.se.thecookbookservice.exceptions.DrinkRecipeNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RateDrinkRecipeActivity {
    private final DrinkRecipeDao drinkRecipeDao;

    @Inject
    public RateDrinkRecipeActivity(DrinkRecipeDao drinkRecipeDao) {
        this.drinkRecipeDao = drinkRecipeDao;
    }

    public RateDrinkRecipeResult handleRequest(
            final RateDrinkRecipeRequest rateDrinkRecipeRequest) {

        String creator = rateDrinkRecipeRequest.getCreator();
        String recipeTitle = rateDrinkRecipeRequest.getRecipeTitle();

        DrinkRecipe drinkRecipe;
        try {drinkRecipe = drinkRecipeDao.getDrinkRecipe(creator, recipeTitle);
        } catch (DrinkRecipeNotFoundException ex) {
            throw new DrinkRecipeNotFoundException("Drink Recipe is not in our database.");
        }

        int ratingToAdd = rateDrinkRecipeRequest.getRating();

        Map<Integer, Integer> ratings;

        if (drinkRecipe.getRatings() == null) {
            ratings = new HashMap<>();
            ratings.put(-1, 0);
            ratings.put(0, 0);
            ratings.put(1, 0);
        } else  {
            ratings = new HashMap<>(drinkRecipe.getRatings());
        }

        ratings.replace(ratingToAdd, (ratings.get(ratingToAdd) + 1));
        drinkRecipe.setRatings(ratings);
        drinkRecipeDao.saveDrinkRecipe(drinkRecipe);

        return RateDrinkRecipeResult.builder()
                .withRatings(ratings)
                .build();
    }
}
