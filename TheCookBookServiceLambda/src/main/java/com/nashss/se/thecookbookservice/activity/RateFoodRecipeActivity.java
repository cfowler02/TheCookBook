package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.RateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.RateFoodRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.exceptions.FoodRecipeNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RateFoodRecipeActivity {
    private final FoodRecipeDao foodRecipeDao;

    @Inject
    public RateFoodRecipeActivity(FoodRecipeDao foodRecipeDao) {
        this.foodRecipeDao = foodRecipeDao;
    }

    public RateFoodRecipeResult handleRequest(
            final RateFoodRecipeRequest rateFoodRecipeRequest) {

        String creator = rateFoodRecipeRequest.getCreator();
        String recipeTitle = rateFoodRecipeRequest.getRecipeTitle();

        FoodRecipe foodRecipe = foodRecipeDao.getFoodRecipe(creator, recipeTitle);


        int ratingToAdd = rateFoodRecipeRequest.getRating();

        Map<Integer, Integer> ratings;

        if (foodRecipe.getRatings() == null) {
            ratings = new HashMap<>();
            ratings.put(-1, 0);
            ratings.put(0, 0);
            ratings.put(1, 0);
        } else  {
            ratings = new HashMap<>(foodRecipe.getRatings());
        }

        ratings.replace(ratingToAdd, (ratings.get(ratingToAdd) + 1));
        foodRecipe.setRatings(ratings);
        foodRecipeDao.saveFoodRecipe(foodRecipe);

        return RateFoodRecipeResult.builder()
                .withRatings(ratings)
                .build();
    }
}
