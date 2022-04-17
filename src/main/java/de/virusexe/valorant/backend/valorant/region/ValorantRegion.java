package de.virusexe.valorant.backend.valorant.region;

public enum ValorantRegion {

    EU("https://pd.eu.a.pvp.net");

    private final String url;

    ValorantRegion(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
