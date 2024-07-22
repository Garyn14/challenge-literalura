package com.literatura.challenge_litura.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class StatisticsByLanguage {
    private String language;
    private Long count;

    @Override
    public String toString() {
        return "{ " +
                "language= " + language +
                ", count= " + count +
                " }";
    }
}
