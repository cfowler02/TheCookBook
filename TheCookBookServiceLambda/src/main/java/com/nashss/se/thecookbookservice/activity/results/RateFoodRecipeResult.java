package com.nashss.se.thecookbookservice.activity.results;

import java.util.HashMap;
import java.util.Map;

public class RateFoodRecipeResult {
    private final Map<Integer, Integer> ratings;

    public RateFoodRecipeResult(Map<Integer, Integer> ratings) {
        this.ratings = ratings;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "RateFoodRecipeResult{" +
                "ratings=" + ratings +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Map<Integer, Integer> ratings;
        public Builder withRatings(Map<Integer, Integer> ratings) {
            this.ratings = new HashMap<>(ratings);
            return this;
        }
        public RateFoodRecipeResult build() {
            return new RateFoodRecipeResult(ratings);
        }
    }
}
