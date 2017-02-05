package com.spork.service;


import com.spork.model.FarmMock;
import com.spork.model.Ingredient;
import com.spork.model.googleMaps.googleMapsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class FarmProvider {
    //"https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + source + "&destinations=" + destination +"&key=AIzaSyBn9CD8FowWeOi_mf2BfL1eh7_09G9H9bU&callback=?"

    private static final String GMAPS_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=";
    private static final String GMAPS_API_KEY = "AIzaSyBn9CD8FowWeOi_mf2BfL1eh7_09G9H9bU";

    private static List<FarmMock> farms = new ArrayList<FarmMock>();
    private RestTemplate restTemplate;

    @Autowired
    public FarmProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        generateFarms();
    }

    private void generateFarms(){
        farms.add(new FarmMock(Arrays.asList(new Ingredient("Apple Butter"), new Ingredient("Apples"), new Ingredient("Maple syrup")), "1090+Reitzel+Pl+St.+Jacobs+ON", "John B. Martin"));
        farms.add(new FarmMock(Arrays.asList(new Ingredient("Apple Butter"), new Ingredient("Apples"), new Ingredient("Maple syrup"), new Ingredient("Potatoes"), new Ingredient("Sausage"), new Ingredient("Honey") ), "3124+Lobsinger+Line+St.+Clements+ON", "Amon Martin"));
        farms.add(new FarmMock(Arrays.asList(new Ingredient("Apples"), new Ingredient("Cherries"), new Ingredient("Pears"), new Ingredient("Plum"), new Ingredient("Beef"), new Ingredient("Sausage"), new Ingredient("Chicken"), new Ingredient("Maple syrup"), new Ingredient("Eggs"), new Ingredient("Cheese")), "7480+Line+86+Wallenstein+ON", "Emerson Bowman"));
    }

    public List<FarmMock> provide(){
        return farms;
    }

    public FarmMock provideIndex(int index) {
        return farms.get(index);
    }

    public FarmMock getSortedListOfFarmsByDistance(String addr){
        for (FarmMock f: farms) {
            String url = GMAPS_URL + addr + "&destinations=" + f.getLocation() + "&key=" + GMAPS_API_KEY + "&callback=?";
            ResponseEntity<googleMapsResponse> response = restTemplate.getForEntity(url, googleMapsResponse.class);
            f.setDistance(response.getBody().getRows().get(0).getElements().get(0).getDistance().getValue());
        }
        farms.sort(Comparator.comparingInt(FarmMock::getDistance));
        return farms.get(0);
    }
}
