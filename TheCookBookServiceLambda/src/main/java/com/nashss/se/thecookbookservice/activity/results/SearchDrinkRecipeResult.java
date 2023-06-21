package com.nashss.se.thecookbookservice.activity.results;

import com.nashss.se.thecookbookservice.models.DrinkRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class SearchDrinkRecipeResult {
    private final List<DrinkRecipeModel> drinkRecipeModels;

    private SearchDrinkRecipeResult(List<DrinkRecipeModel> drinkRecipeModels) {
        this.drinkRecipeModels = drinkRecipeModels;
    }

    public List<DrinkRecipeModel> getDrinkRecipeModels() {
        return drinkRecipeModels;
    }

    @Override
    public String toString() {
        return "SearchDrinkRecipeResult{" +
                "drinkRecipeModels=" + drinkRecipeModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchDrinkRecipeResult.Builder builder() {
        return new SearchDrinkRecipeResult.Builder();
    }

    public static class Builder {
        private List<DrinkRecipeModel> drinkRecipeModels ;

        public SearchDrinkRecipeResult.Builder withDrinkRecipeModels(List<DrinkRecipeModel> drinkRecipeModels) {
            this.drinkRecipeModels = new ArrayList<>(drinkRecipeModels);
            return this;
        }

        public SearchDrinkRecipeResult build() {
            return new SearchDrinkRecipeResult(drinkRecipeModels);
        }
    }
}
