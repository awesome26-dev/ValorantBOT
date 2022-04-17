package de.virusexe.valorant.backend;

import de.virusexe.valorant.backend.valorant.auth.ValorantAuthentication;
import de.virusexe.valorant.backend.valorant.header.ValorantHeader;
import de.virusexe.valorant.backend.valorant.region.ValorantRegion;
import org.apache.http.HttpHeaders;

public class BackEnd {

    // This is the BackEnd class. It is used to get the data from the Riot API.
    private ValorantAuthentication valorantAuthentication;
    private final UnirestSystem unirestSystem;

    // The BackEnd class. It is used to get the data from the Riot API.
    public BackEnd() {
        unirestSystem = new UnirestSystem();
    }

    /**
     * It creates a new ValorantAuthentication object with the given parameters.
     *
     * @param uniqueId This is the unique id of the user.
     * @param accessToken This is the access token that you get from the Riot Games API.
     * @param entertainmentToken This is the token that you get from the Riot Games API.
     */
    public void auth(String uniqueId, String accessToken, String entertainmentToken) {
        valorantAuthentication = new ValorantAuthentication(uniqueId, accessToken, entertainmentToken);
    }

    /**
     * This function returns the ValorantAuthentication object that was created in the constructor
     *
     * @return The ValorantAuthentication object.
     */
    public ValorantAuthentication getValorantAuthentication() {
        return valorantAuthentication;
    }

    /**
     * It gets the store data for the specified region
     *
     * @param valorantRegion The region you want to get the store for.
     * @return A JSON object containing the store information.
     */
    public Object getStore(ValorantRegion valorantRegion) {
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/store/v2/storefront/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }

    /**
     * It gets the loadout of the player
     *
     * @param valorantRegion The region you want to get the loadout for.
     * @return A JSON object containing the player's loadout.
     */
    public Object getLoadOut(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/personalization/v2/players/" + getValorantAuthentication().getUniqueId() + "/playerloadout", authHeader, enHeader).getBody();
    }

    /**
     * Get the balance of the user's account
     *
     * @param valorantRegion The region you want to get the balance from.
     * @return A JSON object containing the balance of the user.
     */
    public Object getBalance(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "/store/v1/wallet/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }

    /**
     * It gets the user's information from the Valorant API
     *
     * @param valorantRegion The region you want to get the user from.
     * @return A JSON object containing the user's information.
     */
    public Object getUserFromId(ValorantRegion valorantRegion){
        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getValorantAuthentication().getAccessToken());
        ValorantHeader enHeader = new ValorantHeader("X-Riot-Entitlements-JWT", getValorantAuthentication().getEntertainmentToken());

        return unirestSystem.getHeader(valorantRegion.getUrl() + "name-service/v2/players/" + getValorantAuthentication().getUniqueId(), authHeader, enHeader).getBody();
    }
}
