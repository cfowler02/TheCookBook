package com.nashss.se.thecookbookservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static com.nashss.se.thecookbookservice.utils.CollectionUtils.copyToList;

@JsonDeserialize(builder = CreateFoodRecipeRequest.Builder.class)
public class CreateFoodRecipeRequest {
    private final String creator;
    private final String recipeTitle;
    private final List<String> ingredients;
    private final LinkedList<String> instructionSteps;
    private final String description;
    private final List<String> descriptionTags;
    private final int timeEstimate;
    private final String foodCategory;
    private final String foodItem;
    private final List<String> allergies;
    private final Map<Integer, Integer> ratings;

    public CreateFoodRecipeRequest(String creator, String recipeTitle, List<String> ingredients,
                                   LinkedList<String> instructionSteps, String description,
                                   List<String> descriptionTags, int timeEstimate, String foodCategory, String foodItem,
                                   List<String> allergies, Map<Integer, Integer> ratings) {
        this.creator = creator;
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
        this.instructionSteps = instructionSteps;
        this.description = description;
        this.descriptionTags = descriptionTags;
        this.timeEstimate = timeEstimate;
        this.foodCategory = foodCategory;
        this.foodItem = foodItem;
        this.allergies = allergies;
        this.ratings = ratings;
    }

    public String getCreator() {
        return creator;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public LinkedList<String> getInstructionSteps() {
        return instructionSteps;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDescriptionTags() {
        return descriptionTags;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "CreateFoodRecipeRequest{" +
                "creator='" + creator + '\'' +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", ingredients=" + ingredients +
                ", instructionSteps=" + instructionSteps +
                ", description='" + description + '\'' +
                ", descriptionTags=" + descriptionTags +
                ", timeEstimate=" + timeEstimate +
                ", foodCategory='" + foodCategory + '\'' +
                ", foodItem='" + foodItem + '\'' +
                ", allergies=" + allergies +
                ", ratings=" + ratings +
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
        private List<String> ingredients;
        private LinkedList<String> instructionSteps;
        private String description;
        private List<String> descriptionTags;
        private int timeEstimate;
        private String foodCategory;
        private String foodItem;
        private List<String> allergies;
        private Map<Integer, Integer> ratings;

        public Builder withCreator(String creator) {
            this.creator = creator;
            return this;
        }

        public Builder withRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
            return this;
        }

        public Builder withIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder withInstructionSteps(LinkedList<String> instructionSteps) {
            this.instructionSteps = instructionSteps;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDescriptionTags(List<String> descriptionTags) {
            this.descriptionTags = descriptionTags;
            return this;
        }

        public Builder withTimeEstimate(int timeEstimate) {
            this.timeEstimate = timeEstimate;
            return this;
        }

        public Builder withFoodCategory(String foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public Builder withFoodItem(String foodItem) {
            this.foodItem = foodItem;
            return this;
        }

        public Builder withAllergies(List<String> allergies) {
            this.allergies = allergies;
            return this;
        }

        public Builder withRatings(Map<Integer, Integer> ratings) {
            this.ratings = ratings;
            return this;
        }

        public CreateFoodRecipeRequest build() {
            return new CreateFoodRecipeRequest(creator, recipeTitle, ingredients, instructionSteps, description,
                    descriptionTags, timeEstimate, foodCategory, foodItem, allergies, ratings);
        }
    }
}
