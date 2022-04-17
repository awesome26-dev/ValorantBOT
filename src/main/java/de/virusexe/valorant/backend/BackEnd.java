package de.virusexe.valorant.backend;

import de.virusexe.valorant.backend.valorant.auth.ValorantAuthentication;
import de.virusexe.valorant.backend.valorant.header.ValorantHeader;
import de.virusexe.valorant.backend.valorant.region.ValorantRegion;
import org.apache.http.HttpHeaders;

public class BackEnd {

    private ValorantAuthentication valorantAuthentication;
    private final UnirestSystem unirestSystem;

    public BackEnd() {
        unirestSystem = new UnirestSystem();
    }

    public void auth(String uniqueId, String accessToken, String entertainmentToken) {
        valorantAuthentication = new ValorantAuthentication(uniqueId, accessToken, entertainmentToken);
    }

    public ValorantAuthentication getValorantAuthentication() {
        return valorantAuthentication;
    }

    public Object getStore(ValorantRegion valorantRegion) {
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        System.out.println("enHeader");
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        System.out.println();

        return unirestSystem.get(valorantRegion.getUrl() + "/store/v2/storefront/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }
}
