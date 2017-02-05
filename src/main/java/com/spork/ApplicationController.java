package com.spork;

import com.spork.model.Ingredient;
import com.spork.service.EdamamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApplicationController {

    private EdamamService edamamService;
    private RestTemplate restTemplate;

    @Autowired
    public ApplicationController(EdamamService edamamService, RestTemplate restTemplate) {
        this.edamamService = edamamService;
        this.restTemplate = restTemplate;
    }

    @CrossOrigin
    @GetMapping("/")
    public @ResponseBody String index() {
        return "Hello World";
    }


    @CrossOrigin
    @GetMapping("/ethansshit")
    public @ResponseBody String ethan() {
        ResponseEntity<String> response = restTemplate.exchange("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=256+Philip+Street+Waterloo+ON&destinations=18+Foreht+Crescent+Aurora+ON&key=AIzaSyDontXg9FANm3AbQIxs7ysmgIvjKw3yLJM&callback=?",
                HttpMethod.GET, HttpEntity.EMPTY, String.class );
        return response.getBody();
    }
}