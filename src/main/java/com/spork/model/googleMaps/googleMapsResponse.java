package com.spork.model.googleMaps;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class googleMapsResponse {
    private List<googleMapRows> rows;
}
