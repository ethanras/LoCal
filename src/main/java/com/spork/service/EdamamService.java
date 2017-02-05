package com.spork.service;

import com.spork.model.EdamamHits;
import com.spork.model.EdamamResponse;
import com.spork.model.Ingredient;
import com.spork.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EdamamService {

    private static String API_ID = "7d211b84";
    private static String APP_KEY = "dd9decfaabc64ebf6dde73ac7533e1fd";
    // Example Query: https://api.edamam.co m/search?q=chicken&app_id=7d211b84&app_key=dd9decfaabc64ebf6dde73ac7533e1fd
    private static String RECIPE_URL = "https://api.edamam.com/search?";

    private RestTemplate restTemplate;

    @Autowired
    public EdamamService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String createIngredientQueryString(List<Ingredient> ingredients) {
        List<String> ingredAsString = ingredients.stream().map(Ingredient::getText).collect(Collectors.toList());
        return String.join(",", ingredAsString);
    }

    private List<Recipe> getRecipesForIngredients(List<Ingredient> ingredients){
        String ingredientString = createIngredientQueryString(ingredients);
        String searchURL = RECIPE_URL + "q=" + ingredientString + "&app_id=" + API_ID + "&app_key=" + APP_KEY;
        try {
            URI searchURI = new URI(searchURL);
            ResponseEntity<EdamamResponse> response = restTemplate.getForEntity(searchURI, EdamamResponse.class);
            return response.getBody().getHits().stream().map(EdamamHits::getRecipe).collect(Collectors.toList());
        } catch (Exception e) {
        }
        return Collections.emptyList();
    }

    public List<Recipe> getRecipesForAllIngredients(Ingredient mainIngredient, List<Ingredient> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.addAll(getRecipesForIngredients(Arrays.asList(mainIngredient)));
        recipes.addAll(getRecipesForIngredients(ingredients));
        return recipes;
    }
}
