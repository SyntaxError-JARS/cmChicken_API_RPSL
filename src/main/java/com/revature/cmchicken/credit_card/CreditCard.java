package com.revature.cmchicken.credit_card;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    private String cc_number;
    private String cc_name;
    private int cvv;
    private String exp_date;
    private  int zip;
    private int cc_limit;
    private String user_name;

    public CreditCard() {
    }

    public CreditCard(String cc_number, String cc_name, int cvv, String exp_date, int zip, int cc_limit, String user_name) {
        super();
        this.cc_number = cc_number;
        this.cc_name = cc_name;
        this.cvv = cvv;
        this.exp_date = exp_date;
        this.zip = zip;
        this.cc_limit = cc_limit;
        this.user_name = user_name;
    }



    public String toString() {
        return "CreditCard{" +
                "cc_number='" + cc_number + '\'' +
                ", cc_name='" + cc_name + '\'' +
                ", cvv='" + cvv + '\'' +
                ", exp_date='" + exp_date + '\'' +
                ", zip='" + zip + '\'' +
                ", cc_limit='" + cc_limit + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
