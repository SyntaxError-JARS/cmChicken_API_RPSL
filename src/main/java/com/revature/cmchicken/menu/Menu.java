package com.revature.cmchicken.menu;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "item_name")
    private String item_name;

    private double  cost;

    private String protein;

    private boolean is_substitutable;

    public Menu(){

    }

    @Override
    public String toString() {
        return "Menu{" +
                "item_name='" + item_name + '\'' +
                ", cost=" + cost +
                ", protein='" + protein + '\'' +
                ", is_substitutable=" + is_substitutable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(menu.getCost(), getCost()) == 0 && isIs_substitutable() == menu.isIs_substitutable() && Objects.equals(getItem_name(), menu.getItem_name()) && Objects.equals(getProtein(), menu.getProtein());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem_name(), getCost(), getProtein(), isIs_substitutable());
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
