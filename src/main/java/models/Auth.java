package models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {


    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public Auth() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
