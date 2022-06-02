package com.revature.cmchicken.util.web.dto;

public class LoginCreds {

    private String username;
    private String cpassword;

    private boolean is_admin;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR
    public LoginCreds(){

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
