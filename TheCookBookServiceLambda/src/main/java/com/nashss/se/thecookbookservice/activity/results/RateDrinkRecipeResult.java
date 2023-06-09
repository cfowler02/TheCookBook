package com.nashss.se.thecookbookservice.activity.results;

import java.util.HashMap;
import java.util.Map;

public class RateDrinkRecipeResult {
    private final Map<Integer, Integer> ratings;

    public RateDrinkRecipeResult(Map<Integer, Integer> ratings) {
        this.ratings = ratings;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "RateDrinkRecipeResult{" +
                "ratings=" + ratings +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static RateDrinkRecipeResult.Builder builder() {
        return new RateDrinkRecipeResult.Builder();
    }
    public static class Builder {
        private Map<Integer, Integer> ratings;
        public RateDrinkRecipeResult.Builder withRatings(Map<Integer, Integer> ratings) {
            this.ratings = new HashMap<>(ratings);
            return this;
        }
        public RateDrinkRecipeResult build() {
            return new RateDrinkRecipeResult(ratings);
        }
    }
}
