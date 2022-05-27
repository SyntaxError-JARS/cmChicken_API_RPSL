package com.revature.cmChicken.util.web.dto;

public class LoginCreds {

    private String username;
    private String password;

    private boolean is_admin;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
