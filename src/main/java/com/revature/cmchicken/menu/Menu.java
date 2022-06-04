package com.revature.cmchicken.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    private String item_name;
    private double price;
    private String protein;
    private boolean is_substitutable;


    public Menu() {

    }

    public Menu(String item_name, double price, String protein, boolean is_substitutable) {
        this.item_name = item_name;
        this.price = price;
        this.protein = protein;
        this.is_substitutable = is_substitutable;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "item_name='" + item_name + '\'' +
                ", price=" + price +
                ", protein=" + protein +
                ", is_substitutable=" + is_substitutable +
                '}';
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public boolean isIs_substitutable() {
        return is_substitutable;
    }

    public void setIs_substitutable(boolean is_substitutable) {
        this.is_substitutable = is_substitutable;
    }
}
