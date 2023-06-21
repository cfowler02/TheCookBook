package com.nashss.se.thecookbookservice.activity.results;

import com.nashss.se.thecookbookservice.models.FoodRecipeModel;

public class CreateFoodRecipeResult {
    private final FoodRecipeModel foodRecipe;

    private CreateFoodRecipeResult(FoodRecipeModel foodRecipe) {
        this.foodRecipe = foodRecipe;
    }

    public FoodRecipeModel getFoodRecipe() {
        return foodRecipe;
    }

    @Override
    public String toString() {
        return "CreateFoodRecipeResult{" +
                "foodRecipe=" + foodRecipe +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FoodRecipeModel foodRecipe;

        public Builder withFoodRecipe(FoodRecipeModel foodRecipe) {
            this.foodRecipe = foodRecipe;
            return this;
        }

        public CreateFoodRecipeResult build() {
            return new CreateFoodRecipeResult(foodRecipe);
        }
    }
}
