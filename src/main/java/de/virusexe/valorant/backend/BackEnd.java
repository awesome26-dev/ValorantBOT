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
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/store/v2/storefront/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }

    public Object getLoadOut(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/personalization/v2/players/" + getValorantAuthentication().getUniqueId() + "/playerloadout", authHeader, enHeader).getBody();
    }

    public Object getBalance(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/store/v1/wallet/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }

    public Object getUserFromId(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "name-service/v2/players/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();

    }
}
