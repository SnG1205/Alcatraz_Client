package com.example.alcatraz_client.data;

public class Client {
    private String username;
    private int port;

    public Client(String username, int port) {
        this.username = username;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
