package com.one.domain;

public class User {
    private String username;
    private String IP;

    public User(String username, String IP) {
        this.username = username;
        this.IP = IP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
