package com.nashss.se.thecookbookservice.activity;


import com.nashss.se.thecookbookservice.activity.requests.CreateFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.CreateFoodRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.verify;

public class CreateFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;
    @InjectMocks
    private CreateFoodRecipeActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        activity = new CreateFoodRecipeActivity(foodRecipeDao);
    }

    @Test
    public void handleRequest_withMostAttributes_createsAndSavesFoodRecipe() {
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
        expectedRatings.put(0, 1);
        expectedRatings.put(1, 2);

        CreateFoodRecipeRequest request1 = CreateFoodRecipeRequest.builder()
                .withCreator(expectedCreator)
                .withRecipeTitle(expectedRecipeTitle)
                .withIngredients(expectedIngredients)
                .withInstructionSteps(expectedInstructionSteps)
                .withDescription(expectedDescription)
                .withDescriptionTags(expectedDescriptionTags)
                .withTimeEstimate(expectedTimeEstimate)
                .withFoodCategory(expectedFoodCategory)
                .withFoodItem(expectedFoodItem)
                .withAllergies(expectedAllergies)
                .withRatings(expectedRatings)
                .build();
        //WHen
        CreateFoodRecipeResult result1 = activity.handleRequest(request1);
        //Then
        //verify(foodRecipeDao).saveFoodRecipe(any(FoodRecipe.class));
        assertEquals(expectedCreator, result1.getFoodRecipe().getCreator());
        assertEquals(expectedRecipeTitle, result1.getFoodRecipe().getRecipeTitle());
        assertEquals(expectedIngredients, result1.getFoodRecipe().getIngredients());
        assertEquals(expectedInstructionSteps, result1.getFoodRecipe().getInstructionSteps());
        assertEquals(expectedDescription, result1.getFoodRecipe().getDescription());
        assertEquals(expectedDescriptionTags, result1.getFoodRecipe().getDescriptionTags());
        assertEquals(expectedTimeEstimate, result1.getFoodRecipe().getTimeEstimate());
        assertEquals(expectedFoodCategory, result1.getFoodRecipe().getFoodCategory());
        assertEquals(expectedFoodItem, result1.getFoodRecipe().getFoodItem());
        assertEquals(expectedAllergies, result1.getFoodRecipe().getAllergies());
        assertEquals(expectedRatings, result1.getFoodRecipe().getRatings());
    }
}