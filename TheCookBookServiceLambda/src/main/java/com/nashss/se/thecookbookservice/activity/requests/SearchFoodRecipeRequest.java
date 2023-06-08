package com.nashss.se.thecookbookservice.activity.requests;

public class SearchFoodRecipeRequest {
    private final String criteria;

    private SearchFoodRecipeRequest(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchFoodRecipeRequest{" +
                "criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchFoodRecipeRequest.Builder builder() {
        return new SearchFoodRecipeRequest.Builder();
    }

    public static class Builder {
        private String criteria;

        public SearchFoodRecipeRequest.Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchFoodRecipeRequest build() {
            return new SearchFoodRecipeRequest(criteria);
        }
    }
}
