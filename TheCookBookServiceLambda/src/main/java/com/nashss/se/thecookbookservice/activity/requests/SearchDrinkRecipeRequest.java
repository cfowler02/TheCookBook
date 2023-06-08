package com.nashss.se.thecookbookservice.activity.requests;

public class SearchDrinkRecipeRequest {
    private final String criteria;

    private SearchDrinkRecipeRequest(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchDrinkRecipeRequest{" +
                "criteria='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SearchDrinkRecipeRequest.Builder builder() {
        return new SearchDrinkRecipeRequest.Builder();
    }

    public static class Builder {
        private String criteria;

        public SearchDrinkRecipeRequest.Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchDrinkRecipeRequest build() {
            return new SearchDrinkRecipeRequest(criteria);
        }
    }
}
