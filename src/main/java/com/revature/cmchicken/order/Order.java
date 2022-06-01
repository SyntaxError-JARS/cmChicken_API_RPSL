package com.revature.cmchicken.order;


import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne // ???
    @JoinColumn(name = "menu_item", referencedColumnName = "item_name")
    private String menu_item;

    private String comment;

    private boolean is_favorite;

    private String order_date;

    @ManyToOne // ???
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private String customer_username;


    // JACKSON REQUIRES A NO ARG CONSTRUCTOR
    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", menu_item='" + menu_item + '\'' +
                ", comment='" + comment + '\'' +
                ", is_favorite=" + is_favorite +
                ", order_date='" + order_date + '\'' +
                ", customer_username='" + customer_username + '\'' +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

}
