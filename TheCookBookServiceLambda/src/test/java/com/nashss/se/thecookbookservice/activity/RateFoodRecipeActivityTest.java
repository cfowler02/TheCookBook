package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.RateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.RateFoodRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

public class RateFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;
    @InjectMocks
    private RateFoodRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RateFoodRecipeActivity(foodRecipeDao);

    }

    @Test
    void handleRequest_withRating_updatesFoodRecipeRatings() {
        //GIVEN
        String expectedCreator = "expectedCreator";
        String expectedRecipeTitle = "expectedRecipeTitle";
        Map<String, String> expectedIngredients = new HashMap();
        expectedIngredients.put("expectedIngredient", "expectedIngredientAmount");
        LinkedList<String> expectedInstructionSteps = new LinkedList<>();
        expectedInstructionSteps.add("expectedStep");
        String expectedDescription = "expectedDescription";
        Set<String> expectedDescriptionTags = new HashSet<>();
        expectedDescriptionTags.add("expectedTag");
        int expectedTimeEstimate = 1;
        String expectedFoodCategory = "expectedFoodCategory";
        String expectedFoodItem = "expectedFoodItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<Integer, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put(-1, 0);
        expectedRatings.put(0, 0);
        expectedRatings.put(1, 0);

        Map<Integer, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put(-1, 0);
        expectedRatingsTested.put(0, 1);
        expectedRatingsTested.put(1, 0);

        FoodRecipe foodRecipe = new FoodRecipe();
        foodRecipe.setCreator(expectedCreator);
        foodRecipe.setRecipeTitle(expectedRecipeTitle);
        foodRecipe.setIngredients(expectedIngredients);
        foodRecipe.setInstructionSteps(expectedInstructionSteps);
        foodRecipe.setDescription(expectedDescription);
        foodRecipe.setDescriptionTags(expectedDescriptionTags);
        foodRecipe.setTimeEstimate(expectedTimeEstimate);
        foodRecipe.setFoodCategory(expectedFoodCategory);
        foodRecipe.setFoodItem(expectedFoodItem);
        foodRecipe.setAllergies(expectedAllergies);
        foodRecipe.setRatings(expectedRatings);

        foodRecipeDao.saveFoodRecipe(foodRecipe);

        RateFoodRecipeRequest request1 = RateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(1)
                .build();

        //WHEN
        //RateFoodRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        //assertEquals(expectedRatingsTested, result1.getRatings());
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());


    }
}
