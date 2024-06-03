package br.com.bookaholic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id") Integer idBook,
        String title,
        List<AuthorData> authors,
        List<String> subjects,
        Boolean copyright,
        @JsonAlias("download_count") Integer downloadCount
) {
}
