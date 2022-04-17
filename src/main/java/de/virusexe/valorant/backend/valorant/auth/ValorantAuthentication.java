package de.virusexe.valorant.backend.valorant.auth;

public record ValorantAuthentication(String uniqueId, String accessToken,
                                     String entertainmentToken) {

    public String getUniqueId() {
        return uniqueId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEntertainmentToken() {
        return entertainmentToken;
    }
}
