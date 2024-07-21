package com.literatura.challenge_litura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookApi(
        String title,
        List<PersonApi> authors,
        List<String> languages,
        @JsonAlias("download_count")
        Long downloadCount
) {
        public PersonApi getFirstAuthor(){
                if (!this.authors.isEmpty()){
                        return this.authors.get(0);
                }
                return null;
        }

        public String getFirstLanguage(){
                if(!this.languages.isEmpty()){
                        return languages.get(0);
                }
                return null;
        }

        @Override
        public String toString() {
                return "\n{ " +
                        "\ntitle= " + title +
                        "\nauthors= " + authors +
                        "\nlanguages= " + languages +
                        "\ndownloadCount= " + downloadCount +
                        "\n}";
        }
}
