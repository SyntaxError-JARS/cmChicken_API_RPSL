package com.revature.cmchicken.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    private String item_name;
    private int cost;
    private String protein;
    private boolean is_substitutable;


    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "item_name='" + item_name + '\'' +
                ", cost=" + cost +
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public boolean getIs_substitutable() {
        return is_substitutable;
    }

    public void setIs_substitutable(boolean is_substitutable) {
        this.is_substitutable = is_substitutable;
    }
}
