package com.literatura.challenge_litura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
        Long id,
        String title,
        List<String> subjects,
        List<Person> authors,
        List<Person> translators,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        @JsonAlias("media_type")
        String mediaType,
        @JsonAlias("download_count")
        Long downloadCount
) {
}
