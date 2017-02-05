package com.spork.model;

public class RecipeRequest {
    private String ingredientList;
    private String healthLabels;

    public RecipeRequest() {
    }

    public RecipeRequest(String ingredientList, String healthLabels) {
        this.ingredientList = ingredientList;
        this.healthLabels = healthLabels;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(String ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(String healthLabels) {
        this.healthLabels = healthLabels;
    }
}
