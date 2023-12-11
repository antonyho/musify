package net.antonyho.musify.wikidata;

import java.util.List;

public class Wikidata {
    private List<Entity> entities;

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    static class Entity {
        private List<Sitelink> sitelinks;

        public List<Sitelink> getSitelinks() {
            return sitelinks;
        }

        public void setSitelinks(List<Sitelink> sitelinks) {
            this.sitelinks = sitelinks;
        }
    }

    static class Sitelink {
        private String site;
        private String url;

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
