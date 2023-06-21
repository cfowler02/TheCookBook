package com.nashss.se.thecookbookservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RateFoodRecipeRequest.Builder.class)
public class RateFoodRecipeRequest {
    private final String creator;
    private final String recipeTitle;
    private final int rating;

    public RateFoodRecipeRequest(String creator, String recipeTitle, int rating) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
        this.rating = rating;
    }

    public String getCreator() {
        return creator;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RateFoodRecipeRequest{" +
                "creator='" + creator + '\'' +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", rating=" + rating +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String creator;
        private String recipeTitle;
        private int rating;

        public Builder withCreator(String creator) {
            this.creator = creator;
            return this;
        }
        public Builder withRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
            return this;
        }
        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public RateFoodRecipeRequest build() {
            return new RateFoodRecipeRequest(creator, recipeTitle, rating);
        }
    }
}
