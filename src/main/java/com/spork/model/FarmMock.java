package com.spork.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmMock {
    List<Ingredient> inventory;
    String location;
    String name;
    Integer distance;

    public FarmMock(List<Ingredient> inventory, String location, String name) {
        this.inventory = inventory;
        this.location = location;
        this.name = name;
    }
}
