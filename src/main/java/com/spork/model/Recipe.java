package com.spork.model;

import lombok.*;

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
    public List<healthLabels> healthLabels = null;
    public List<String> ingredientLines = null;
    public List<Ingredient> ingredients = null;
    public Float calories;
    public Float totalWeight;
    public List<NutrientInfo> totalNutrients;
    public List<NutrientInfo> totalDaily;

    public enum healthLabels {
        VEGAN("vegan"),
        VEGETARIAN("vegetarian"),
        PALEO("paleo"),
        DAIRYFREE("diary-free"),
        GLUTENFREE("gluten-free"),
        PEANUTFREE("peanut-free");

        private final String text;
        private healthLabels(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
