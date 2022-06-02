package com.revature.cmchicken.customer;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="username")
    private String username;
    @Column(name="fname")
    private String fname;
    private String lname;

    //@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String cpassword;
    private double balance;
    private boolean is_admin;



    // JACKSON REQUIRES A NO ARG CONSTRUCTOR
    public Customer(){
    }

    // TODO: check for this constructor
    public Customer(String cpassword) {
        this.cpassword = cpassword;
    }

    public Customer(String username, String fname, String lname, String cpassword, double balance, boolean is_admin) {
        super();
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.cpassword = cpassword;
        this.balance = balance;
        this.is_admin = is_admin;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", cpassword='" + cpassword + '\'' +
                ", balance=" + balance +
                ", is_admin=" + is_admin +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
