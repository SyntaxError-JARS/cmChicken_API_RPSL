package com.revature.cmchicken.util.web.dto;



public class CreditCardInitializer {


    private String cc_number;
    private String cc_name;
    private int cvv;
    private String exp_date;
    private  int zip;
    private int cc_limit;
    private String customer_username;

    public CreditCardInitializer() {}

    public CreditCardInitializer(String cc_number, String cc_name, int cvv, String exp_date, int zip, int cc_limit, String customer_username) {
        this.cc_number = cc_number;
        this.cc_name = cc_name;
        this.cvv = cvv;
        this.exp_date = exp_date;
        this.zip = zip;
        this.cc_limit = cc_limit;
        this.customer_username = customer_username;
    }

    public String getCc_number() {
        return cc_number;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public String getCc_name() {
        return cc_name;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getCc_limit() {
        return cc_limit;
    }

    public void setCc_limit(int cc_limit) {
        this.cc_limit = cc_limit;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }
}