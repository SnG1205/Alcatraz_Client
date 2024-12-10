package com.example.alcatraz_client.rest;

import at.falb.games.alcatraz.api.Player;
import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;

public class Caller {
    private final String BASE_URL = "http://127.0.0.1:";
    static final MediaType JSON = MediaType.get("application/json");
    private final OkHttpClient httpClient = new OkHttpClient();
    private final JsonConverter jsonConverter = new JsonConverter();

    public void createLobby(Client client, int port, int amountOfPlayers) throws JsonProcessingException {
        String json = jsonConverter.toJson(client);
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("127.0.0.1")
                .port(port)
                .addQueryParameter("amount", String.valueOf(amountOfPlayers))
                .build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url(httpUrl)
                .post(body)
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void joinLobby(Client client, int port) throws JsonProcessingException {
        String json = jsonConverter.toJson(client);
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("127.0.0.1")
                .port(port)
                .addPathSegment("lobby")
                .build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url(httpUrl)
                .post(body)
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void startGame(Client client, int port) throws JsonProcessingException { //Todo refactor
        String json = jsonConverter.toJson(client);
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("127.0.0.1")
                .port(port)
                .addPathSegment("start")
                .addQueryParameter("username", client.getUsername())
                .build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url(httpUrl)
                .post(body)
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void leaveLobby(Client client, int port) throws JsonProcessingException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("127.0.0.1")
                .port(port)
                .addPathSegment("lobby")
                .addQueryParameter("username", client.getUsername())
                .build();
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url(httpUrl)
                .delete()
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendMove(RestMove move, int port) throws JsonProcessingException {
        String json = jsonConverter.toJson(move);
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("127.0.0.1")
                .port(port)
                .addPathSegment("move")
                .build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url(httpUrl)
                .post(body)
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
