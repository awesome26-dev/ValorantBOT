package de.virusexe.valorant.backend;

import com.google.gson.JsonObject;
import de.virusexe.valorant.backend.valorant.header.ValorantHeader;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.Arrays;

public class UnirestSystem {

    public HttpResponse<JsonNode> post(String url) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("client_id", "play-valorant-web-prod");
        jsonObject.addProperty("nonce", 1);
        jsonObject.addProperty("redirect_uri", "https://playvalorant.com/opt_in");
        jsonObject.addProperty("response_type", "token id_token");

        return Unirest.post(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonObject).asJson();
    }

    public HttpResponse<JsonNode> postHeader(String url, ValorantHeader... valorantHeader) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("client_id", "play-valorant-web-prod");
        jsonObject.addProperty("nonce", 1);
        jsonObject.addProperty("redirect_uri", "https://playvalorant.com/opt_in");
        jsonObject.addProperty("response_type", "token id_token");

        for(ValorantHeader header : valorantHeader){
            return Unirest.post(url)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header(header.getKey(), header.getValue())
                    .body(jsonObject).asJson();
        }

       return null;
    }

    public HttpResponse get(String url, ValorantHeader... valorantHeader) {
        for(ValorantHeader header : valorantHeader){

            System.out.println(1);
            return Unirest.get(url)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header(header.getKey(), header.getValue()).asJson();
        }

        System.out.println(2);
        return null;
    }

    public HttpResponse<JsonNode> put(String url) {
        JsonObject authObject = new JsonObject();
        authObject.addProperty("type", "auth");
        authObject.addProperty("username", "r");
        authObject.addProperty("password", "r");

        return Unirest.put(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(authObject).asJson();
    }
}
