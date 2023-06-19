package com.nashss.se.thecookbookservice.activity;

import com.nashss.se.thecookbookservice.activity.requests.SearchFoodRecipeRequest;
import com.nashss.se.thecookbookservice.activity.results.SearchFoodRecipeResult;
import com.nashss.se.thecookbookservice.dynamodb.FoodRecipeDao;
import com.nashss.se.thecookbookservice.dynamodb.models.FoodRecipe;
import com.nashss.se.thecookbookservice.models.FoodRecipeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ViewFoodRecipeActivityTest {
    @Mock
    private FoodRecipeDao foodRecipeDao;

    private SearchFoodRecipeActivity searchFoodRecipeActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        searchFoodRecipeActivity = new SearchFoodRecipeActivity(foodRecipeDao);
    }

    @Test
    public void handleRequest_whenRecipeCreatorMatchSearch_returnsRecipeModelListInResult() {
        // GIVEN
        String criteria = "creator";
        String filter = "Creator";

        Map<String, String> ingredients1 = new HashMap<>();
        ingredients1.put("butter", "as much as you want");
        Map<String, String> ingredients2 = new HashMap<>();
        ingredients2.put("Bread", "two slices");
        ingredients2.put("Cheese slice", "2-4");
        Set<String> descriptionTags1 = new HashSet<>();
        descriptionTags1.add("weird");
        Set<String> descriptionTags2 = new HashSet<>();
        descriptionTags2.add("easy");
        descriptionTags2.add("classic");


        List<FoodRecipe> expected = List.of(
        newRecipe(criteria, "butter soup", ingredients1, descriptionTags1, "American",
                "Butter soup"), newRecipe(criteria, "Grilled Cheese" , ingredients2,
                        descriptionTags2, "American", "Grilled Cheese"));

        when(foodRecipeDao.searchFoodRecipe(filter, criteria)).thenReturn(expected);

        SearchFoodRecipeRequest request = SearchFoodRecipeRequest.builder()
                .withCriteria(criteria)
                .withFilter(filter)
                .build();

        // WHEN
        SearchFoodRecipeResult result = searchFoodRecipeActivity.handleRequest(request);

        // THEN
        List<FoodRecipeModel> resultRecipes = result.getFoodRecipeModels();
        assertEquals(expected.size(), resultRecipes.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCreator(), resultRecipes.get(i).getCreator());
            assertEquals(expected.get(i).getRecipeTitle(), resultRecipes.get(i).getRecipeTitle());
            assertEquals(expected.get(i).getIngredients(), resultRecipes.get(i).getIngredients());
            assertEquals(expected.get(i).getInstructionSteps(), resultRecipes.get(i).getInstructionSteps());
            assertEquals(expected.get(i).getDescription(), resultRecipes.get(i).getDescription());
            assertEquals(expected.get(i).getDescriptionTags(), resultRecipes.get(i).getDescriptionTags());
            assertEquals(expected.get(i).getTimeEstimate(), resultRecipes.get(i).getTimeEstimate());
            assertEquals(expected.get(i).getFoodCategory(), resultRecipes.get(i).getFoodCategory());
            assertEquals(expected.get(i).getFoodItem(), resultRecipes.get(i).getFoodItem());
            assertEquals(expected.get(i).getAllergies(), resultRecipes.get(i).getAllergies());
            assertEquals(expected.get(i).getRatings(), resultRecipes.get(i).getRatings());
        }
    }

    @Test
    public void handleRequest_whenRecipeTitleMatchSearch_returnsRecipeModelListInResult() {
        // GIVEN
        String criteria = "title";
        String filter = "RecipeTitle";

        Map<String, String> ingredients1 = new HashMap<>();
        ingredients1.put("butter", "as much as you want");
        Map<String, String> ingredients2 = new HashMap<>();
        ingredients2.put("Bread", "two slices");
        ingredients2.put("Cheese slice", "2-4");
        Set<String> descriptionTags1 = new HashSet<>();
        descriptionTags1.add("weird");
        Set<String> descriptionTags2 = new HashSet<>();
        descriptionTags2.add("easy");
        descriptionTags2.add("classic");


        List<FoodRecipe> expected = List.of(
                newRecipe("chase fowler", "title", ingredients1, descriptionTags1, "American",
                        "Butter soup"), newRecipe("God", "grilled cheese is the title",
                        ingredients2, descriptionTags2, "American", "Grilled Cheese"));

        when(foodRecipeDao.searchFoodRecipe(filter, criteria)).thenReturn(expected);

        SearchFoodRecipeRequest request = SearchFoodRecipeRequest.builder()
                .withCriteria(criteria)
                .withFilter(filter)
                .build();

        // WHEN
        SearchFoodRecipeResult result = searchFoodRecipeActivity.handleRequest(request);

        // THEN
        List<FoodRecipeModel> resultRecipes = result.getFoodRecipeModels();
        assertEquals(expected.size(), resultRecipes.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCreator(), resultRecipes.get(i).getCreator());
            assertEquals(expected.get(i).getRecipeTitle(), resultRecipes.get(i).getRecipeTitle());
            assertEquals(expected.get(i).getIngredients(), resultRecipes.get(i).getIngredients());
            assertEquals(expected.get(i).getInstructionSteps(), resultRecipes.get(i).getInstructionSteps());
            assertEquals(expected.get(i).getDescription(), resultRecipes.get(i).getDescription());
            assertEquals(expected.get(i).getDescriptionTags(), resultRecipes.get(i).getDescriptionTags());
            assertEquals(expected.get(i).getTimeEstimate(), resultRecipes.get(i).getTimeEstimate());
            assertEquals(expected.get(i).getFoodCategory(), resultRecipes.get(i).getFoodCategory());
            assertEquals(expected.get(i).getFoodItem(), resultRecipes.get(i).getFoodItem());
            assertEquals(expected.get(i).getAllergies(), resultRecipes.get(i).getAllergies());
            assertEquals(expected.get(i).getRatings(), resultRecipes.get(i).getRatings());
        }
    }

    private static FoodRecipe newRecipe(String creator, String recipeTitle, Map<String, String> ingredients,
                                        Set<String> descriptionTags, String foodCategory, String foodItem){
        FoodRecipe foodRecipe = new FoodRecipe();

        foodRecipe.setCreator(creator);
        foodRecipe.setRecipeTitle(recipeTitle);
        foodRecipe.setIngredients(ingredients);
        foodRecipe.setDescriptionTags(descriptionTags);
        foodRecipe.setFoodCategory(foodCategory);
        foodRecipe.setFoodItem(foodItem);

        foodRecipe.setDescription("a generic description");
        LinkedList<String> instructions = new LinkedList<>();
        instructions.add("buy takeout");
        foodRecipe.setInstructionSteps(instructions);
        foodRecipe.setTimeEstimate(0);
        Map<Integer, Integer> ratings = new HashMap<>();
        ratings.put(-1,0);
        ratings.put(0,0);
        ratings.put(1,0);
        foodRecipe.setRatings(ratings);
        foodRecipe.setAllergies(null);

        return foodRecipe;
    }

}
