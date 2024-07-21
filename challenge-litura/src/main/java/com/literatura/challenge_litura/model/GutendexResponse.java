package com.literatura.challenge_litura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponse(
        @JsonAlias("previous")
        String previousPage,
        @JsonAlias("next")
        String nextPage,
        @JsonAlias("results")
        List<Book> books
) {
}
