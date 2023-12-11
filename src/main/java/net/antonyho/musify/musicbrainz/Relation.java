package net.antonyho.musify.musicbrainz;

public class Relation {
    private String type;
    private Url url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    static class Url {
        private String resource;

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }
    }
}
