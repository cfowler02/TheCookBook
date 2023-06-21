package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.RateDrinkRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.RateDrinkRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.DrinkRecipeDao;

import com.nashss.se.thecookbookservice.dynamodb.models.DrinkRecipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RateDrinkRecipeActivityTest {
    @Mock
    private DrinkRecipeDao drinkRecipeDao;
    @InjectMocks
    private RateDrinkRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        this.activity = new RateDrinkRecipeActivity(drinkRecipeDao);

    }

    @Test
    void handleRequest_withRating_updatesDrinkRecipeRatings() {
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
        String expectedDrinkCategory = "expectedDrinkCategory";
        String expectedDrinkItem = "expectedDrinkItem";
        Set<String> expectedAllergies = new HashSet<>();
        Map<Integer, Integer> expectedRatings = new HashMap<>();
        expectedRatings.put(-1, 0);
        expectedRatings.put(0, 0);
        expectedRatings.put(1, 0);

        Map<Integer, Integer> expectedRatingsTested = new HashMap<>();
        expectedRatingsTested.put(-1, 0);
        expectedRatingsTested.put(0, 0);
        expectedRatingsTested.put(1, 1);

        DrinkRecipe drinkRecipe = new DrinkRecipe();
        drinkRecipe.setCreator(expectedCreator);
        drinkRecipe.setRecipeTitle(expectedRecipeTitle);
        drinkRecipe.setIngredients(expectedIngredients);
        drinkRecipe.setInstructionSteps(expectedInstructionSteps);
        drinkRecipe.setDescription(expectedDescription);
        drinkRecipe.setDescriptionTags(expectedDescriptionTags);
        drinkRecipe.setDrinkCategory(expectedDrinkCategory);
        drinkRecipe.setDrinkItem(expectedDrinkItem);
        drinkRecipe.setAllergies(expectedAllergies);
        drinkRecipe.setRatings(expectedRatings);

        RateDrinkRecipeRequest request1 = RateDrinkRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withRating(1)
                .build();

        when(drinkRecipeDao.getDrinkRecipe(expectedCreator, expectedRecipeTitle)).thenReturn(drinkRecipe);
        when(drinkRecipeDao.saveDrinkRecipe(drinkRecipe)).thenReturn(drinkRecipe);

        //WHEN
        RateDrinkRecipeResult result1 = activity.handleRequest(request1);

        //THEN
        assertEquals(expectedRatingsTested, result1.getRatings());
        assertEquals(expectedCreator, request1.getCreator());
        assertEquals(expectedRecipeTitle, request1.getRecipeTitle());


    }
}
