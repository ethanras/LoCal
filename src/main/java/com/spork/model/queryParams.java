package com.spork.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class queryParams {
    private List<Object> sane = null;
    private List<String> q = null;
    private List<String> appId = null;
    private List<String> appKey = null;
}
