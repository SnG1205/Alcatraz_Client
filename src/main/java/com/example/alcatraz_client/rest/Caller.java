package com.example.alcatraz_client.rest;

import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;

import java.util.List;

public class Caller {
    static final MediaType JSON = MediaType.get("application/json");
    private final OkHttpClient httpClient = new OkHttpClient();
    private final JsonConverter jsonConverter = new JsonConverter();

    public void testConnection() {
        Request request = new Request.Builder()
                //.url(BASE_URL + port)
                .url("https://jsonplaceholder.typicode.com/posts")
                .get()
                .build();
        try{
            Response response = httpClient.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public String createLobby(Client client, List<Integer> ports, int amountOfPlayers) throws JsonProcessingException {
        String json = jsonConverter.toJson(client);
        for (Integer port : ports) {
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
                String s = response.body().string();
                System.out.println(s);
                return s;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        return "";
    }

    public String joinLobby(Client client, List<Integer> ports) throws JsonProcessingException {
        String json = jsonConverter.toJson(client);
        for (Integer port : ports) {
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
                String  s = response.body().string();
                System.out.println(s);
                return s;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        return "";
    }

    public String startGame(Client client, List<Integer> ports) throws JsonProcessingException { //Todo refactor
        String json = jsonConverter.toJson(client);
        for(Integer port : ports) {
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
                String s = response.body().string();
                return s;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        return "";
    }

    public String leaveLobby(Client client, List<Integer> ports) throws JsonProcessingException {
        for (Integer port : ports) {
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
                String s = response.body().string();
                System.out.println(s);
                return s;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        return "";
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
