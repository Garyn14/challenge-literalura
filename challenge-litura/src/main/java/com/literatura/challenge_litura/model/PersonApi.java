package com.literatura.challenge_litura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonApi(
        @JsonAlias("birth_year")
        Integer birthYear,

        @JsonAlias("death_year")
        Integer deathYear,

        String name
) {
        @Override
        public String toString() {
                return "{ " +
                        "name= " + name +
                        ", birthYear= " + birthYear +
                        ", deathYear= " + deathYear +
                        " }";
        }
}
