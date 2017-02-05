package com.spork.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdamamResponse {
    private String q;
    private Integer from;
    private Integer to;
    private queryParams params;
    private Boolean more;
    private Integer count;
    private List<EdamamHits> hits = null;
}
