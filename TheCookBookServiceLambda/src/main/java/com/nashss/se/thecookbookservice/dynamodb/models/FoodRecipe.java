package com.nashss.se.thecookbookservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@DynamoDBTable(tableName = "food_recipes")
public class FoodRecipe {

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

    @DynamoDBHashKey(attributeName = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @DynamoDBRangeKey(attributeName = "recipe_title")
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    @DynamoDBAttribute(attributeName = "ingredients")
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @DynamoDBAttribute(attributeName = "instruction_steps")
    public LinkedList<String> getInstructionSteps() {
        return instructionSteps;
    }

    public void setInstructionSteps(LinkedList<String> instructionSteps) {
        this.instructionSteps = instructionSteps;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "description_tags")
    public List<String> getDescriptionTags() {
        return descriptionTags;
    }

    public void setDescriptionTags(List<String> descriptionTags) {
        this.descriptionTags = descriptionTags;
    }

    @DynamoDBAttribute(attributeName = "time_estimate")
    public int getTimeEstimate(){
        return timeEstimate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    @DynamoDBAttribute(attributeName = "food_category")
    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }
    @DynamoDBAttribute(attributeName = "food_item")
    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }
    @DynamoDBAttribute(attributeName = "allergies")
    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @DynamoDBAttribute(attributeName = "ratings")
    public Map<Integer, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Integer, Integer> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodRecipe that = (FoodRecipe) o;
        return timeEstimate == that.timeEstimate &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(recipeTitle, that.recipeTitle) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(instructionSteps, that.instructionSteps) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionTags, that.descriptionTags) &&
                Objects.equals(foodCategory, that.foodCategory) &&
                Objects.equals(foodItem, that.foodItem) &&
                Objects.equals(allergies, that.allergies) &&
                Objects.equals(ratings, that.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, recipeTitle, ingredients, instructionSteps, description, descriptionTags,
                timeEstimate, foodCategory, foodItem, allergies, ratings);
    }

    @Override
    public String toString() {
        return "FoodRecipe{" +
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
}
