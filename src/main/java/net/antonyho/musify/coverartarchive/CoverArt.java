package net.antonyho.musify.coverartarchive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CoverArt {

    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    static class Image {
        @JsonProperty("image")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
