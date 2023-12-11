package net.antonyho.musify.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReleaseGroup {
    private String id;

    private String title;

    @JsonProperty("primary-type")
    private String primaryType;


    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
