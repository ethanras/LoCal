package com.spork.model.googleMaps;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class googleMapRows {
    private List<googleMapsElements> elements;
}
