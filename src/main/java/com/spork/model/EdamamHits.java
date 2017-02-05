package com.spork.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdamamHits {
    private Recipe recipe;
    private Boolean bookmarked;
    private Boolean bought;
}
