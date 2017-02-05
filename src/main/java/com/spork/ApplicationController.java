package com.spork;

import com.spork.model.FarmMock;
import com.spork.model.Ingredient;
import com.spork.model.Recipe;
import com.spork.service.EdamamService;
import com.spork.service.FarmProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ApplicationController {

    private EdamamService edamamService;
    private FarmProvider farmProvider;
    private RestTemplate restTemplate;

    @Autowired
    public ApplicationController(EdamamService edamamService, FarmProvider farmProvider, RestTemplate restTemplate) {
        this.edamamService = edamamService;
        this.farmProvider = farmProvider;
        this.restTemplate = restTemplate;
    }

    @CrossOrigin
    @GetMapping("/")
    public @ResponseBody String index() {
        return "Hello World";
    }

    @CrossOrigin
    @GetMapping("/recipes")
    public @ResponseBody List<Recipe> test(@RequestParam(value="ingredients", required=false, defaultValue ="-1")String ingredients) {
        return edamamService.getRecipesForAllIngredients(ingredients);
    }

    @CrossOrigin
    @PostMapping("/find")
    public @ResponseBody
    FarmMock returnString(@RequestParam(value="index", required=false, defaultValue ="-1") Integer index,
                          @RequestParam(value="addr", required=false) String addr) {
        if (index >= 0) {
            return farmProvider.provideIndex(index);
        }
        return farmProvider.getSortedListOfFarmsByDistance(addr);
    }

//
//    @CrossOrigin
//    @PutMapping("/submit")
//    public @ResponseBody List<Recipe> getRecipes(List<String> ingredients) {
//        ArrayList<Ingredient> ingredientsListFromString = new ArrayList<Ingredient>();
//        for (String s : ingredients) {
//            ingredientsListFromString.add(new Ingredient(s));
//        }
//        return edamamService.getRecipesForAllIngredients((List_) ingredientsListFromString);
//    }
}