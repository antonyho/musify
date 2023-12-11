package net.antonyho.musify;

import java.util.ArrayList;
import java.util.List;

class ArtistDetails {
    private String mbid; // MusicBrainz ID
    private String name;
    private String gender;
    private String country;
    private String disambiguation;
    private String description;
    private List<Album> albums;

    public ArtistDetails(String mbid, String name, String gender, String country, String disambiguation) {
        this.mbid = mbid;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.disambiguation = disambiguation;
        this.description = "";
        this.albums = new ArrayList<>();
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }
}