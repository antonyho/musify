package net.antonyho.musify.wikipedia;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Summary {

    @JsonProperty("extract_html")
    private String extractHtml;

    public String getExtractHtml() {
        return extractHtml;
    }

    public void setExtractHtml(String extractHtml) {
        this.extractHtml = extractHtml;
    }
}
