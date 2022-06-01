package com.revature.cmchicken.customer;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="username")
    private String username;
    private String fname;
    private String lname;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;
    private double balance;
    private int is_admin;


    // JACKSON REQUIRES A NO ARG CONSTRUCTOR
    public Customer(){
    }

    public Customer(String password) {
        this.password = password;
    }

    public Customer(String username, String fname, String lname, String password, double balance, int is_admin) {
        super();
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.balance = balance;
        this.is_admin = is_admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", is_admin=" + is_admin +
                '}';
    }
}
