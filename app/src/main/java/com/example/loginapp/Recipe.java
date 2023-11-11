package com.example.loginapp;

import java.util.List;

public class Recipe {
    private String recipeName;
    private List<String> ingredients;
    private List<String> steps;

    public Recipe(String recipeName, List<String> ingredients, List<String> steps){
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }


}
