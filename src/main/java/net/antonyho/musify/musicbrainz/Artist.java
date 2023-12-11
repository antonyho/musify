package net.antonyho.musify.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Artist {
    private String id;

    private String name;

    private String gender;

    private String country;

    private String disambiguation;

    private List<Relation> relations;

    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public String getWikidataUrl() {
        Relation relation = this.relations.stream().
                filter(r -> r.getType().equals("wikidata")).findFirst().orElseThrow();
        return relation.getUrl().getResource();
    }

    public List<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }

    public void setReleaseGroups(List<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    public List<ReleaseGroup> getAlbums() {
        return this.releaseGroups;
    }

    public List<String> getAlbumIds() {
        return getAlbums().stream()
                .map(ReleaseGroup::getId)
                .toList();
    }
}
