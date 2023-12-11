package net.antonyho.musify;

class Album {
    private String id;

    private String title;

    // We can consider to use java.net.URL for this property.
    // With better support on parsing a valid URL and further processing.
    // But this service is only taking garbage in and out from 3rd party services.
    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}