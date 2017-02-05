package com.spork.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    public String uri;
    public String label;
    public String image;
    public String source;
    public String url;
    public String shareAs;
    public Integer yield;
//    public List<healthLabels> healthLabels = null;
    public List<String> ingredientLines = null;
    public List<Ingredient> ingredients = null;
    public Float calories;
    public Float totalWeight;
//  public ArrayList<NutrientInfo> totalNutrients;
//    public ArrayList<NutrientInfo> totalDaily;
}