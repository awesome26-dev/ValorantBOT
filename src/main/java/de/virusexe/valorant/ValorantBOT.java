package de.virusexe.valorant;

import de.virusexe.valorant.backend.BackEnd;
import de.virusexe.valorant.backend.UnirestSystem;
import de.virusexe.valorant.backend.valorant.header.ValorantHeader;
import de.virusexe.valorant.backend.valorant.region.ValorantRegion;
import de.virusexe.valorant.event.MemberJoin;
import de.virusexe.valorant.event.VerifyViaDMListener;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import org.apache.http.HttpHeaders;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

import java.net.MalformedURLException;

public class ValorantBOT {

    private BackEnd backEnd;
    private UnirestSystem responseSystem;
    private DiscordApi discordApi;

    public void start() throws MalformedURLException {
        String token = "token";
        backEnd = new BackEnd();

       discordApi = new DiscordApiBuilder()
                .setWaitForServersOnStartup(false)
                .setAllIntents()
                .setToken(token).login().join();
        discordApi.updateActivity(ActivityType.STREAMING, "Valorant");
        discordApi.updateStatus(UserStatus.IDLE);

        discordApi.addListener(new MemberJoin());
        discordApi.addListener(new VerifyViaDMListener());

        t();
    }

    public void t(){
        responseSystem = new UnirestSystem();
        responseSystem.post("https://auth.riotgames.com/api/v1/authorization").getBody();

        JsonNode string = responseSystem.put("https://auth.riotgames.com/api/v1/authorization").getBody();
        JSONObject responseObject = string.getObject().getJSONObject("response");
        JSONObject parametersObject = (JSONObject) responseObject.get("parameters");

        String uri = String.valueOf(parametersObject.get("uri"));
        String[] parts = uri.replace("https://playvalorant.com/opt_in#", "").split("&");
        String accessToken = parts[0].replace("access_token=", "").replace("\"", "");

        ValorantHeader authHeader = new ValorantHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        JsonNode entitlementResponse = responseSystem.postHeader("https://entitlements.auth.riotgames.com/api/token/v1", authHeader).getBody();
        String entityToken = entitlementResponse.getObject().getString("entitlements_token");

        JsonNode userResponse = responseSystem.postHeader("https://auth.riotgames.com/userinfo", authHeader).getBody();
        String userId = userResponse.getObject().getString("sub");

        System.out.println("Entity: " + entityToken);

        backEnd.auth(userId, accessToken, entityToken);
        System.out.println(backEnd.getStore(ValorantRegion.EU));
    }

    public BackEnd getBackEnd() {
        return backEnd;
    }

    public DiscordApi getDiscordApi() {
        return discordApi;
    }

    public UnirestSystem getResponseSystem() {
        return responseSystem;
    }
}
