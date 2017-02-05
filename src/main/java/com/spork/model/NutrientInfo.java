package com.spork.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutrientInfo {
    private String label;
    private float quantity;
    private String unit;
}
