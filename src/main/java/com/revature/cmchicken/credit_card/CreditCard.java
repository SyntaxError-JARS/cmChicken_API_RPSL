package com.revature.cmchicken.credit_card;

import com.revature.cmchicken.customer.Customer;

import javax.persistence.*;
import java.util.Objects;

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
    @ManyToOne // ???
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private Customer customer_username;
//    private Customer customer_username;
//
    public CreditCard() {
    }

    public CreditCard(String cc_number, String cc_name, int cvv, String exp_date, int zip, int cc_limit, Customer customer_username) {
        this.cc_number = cc_number;
        this.cc_name = cc_name;
        this.cvv = cvv;
        this.exp_date = exp_date;
        this.zip = zip;
        this.cc_limit = cc_limit;
        this.customer_username = customer_username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return getCvv() == that.getCvv() && getZip() == that.getZip() && getCc_limit() == that.getCc_limit() && Objects.equals(getCc_number(), that.getCc_number()) && Objects.equals(getCc_name(), that.getCc_name()) && Objects.equals(getExp_date(), that.getExp_date()) && Objects.equals(getCustomer_username(), that.getCustomer_username());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCc_number(), getCc_name(), getCvv(), getExp_date(), getZip(), getCc_limit(), getCustomer_username());
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

    public Customer getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(Customer customer_username) {
        this.customer_username = customer_username;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cc_number='" + cc_number + '\'' +
                ", cc_name='" + cc_name + '\'' +
                ", cvv=" + cvv +
                ", exp_date='" + exp_date + '\'' +
                ", zip=" + zip +
                ", cc_limit=" + cc_limit +
                ", customer_username=" + customer_username +
                '}';
    }
}
