package com.revature.cmchicken.menu_order;


import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.menu.Menu;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu_order")
public class MenuOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //private String menu_item;
    @ManyToOne // ???
    @JoinColumn(name = "menu_item", referencedColumnName = "item_name")
    private Menu menu_item;

    private String m_comment;

    private boolean is_favorite;

    private String order_date;


    //private String customer_username;
    @ManyToOne
    @JoinColumn(name = "customer_username", referencedColumnName = "username")
    private Customer customer_username;


    // JACKSON REQUIRES A NO ARG CONSTRUCTOR
    public MenuOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(Menu menu_item) {
        this.menu_item = menu_item;
    }

    public String getM_comment() {
        return m_comment;
    }

    public void setM_comment(String m_comment) {
        this.m_comment = m_comment;
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

    public Customer getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(Customer customer_username) {
        this.customer_username = customer_username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuOrder)) return false;
        MenuOrder menuOrder = (MenuOrder) o;
        return getId() == menuOrder.getId() && isIs_favorite() == menuOrder.isIs_favorite() && getMenu_item().equals(menuOrder.getMenu_item()) && getM_comment().equals(menuOrder.getM_comment()) && getOrder_date().equals(menuOrder.getOrder_date()) && getCustomer_username().equals(menuOrder.getCustomer_username());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMenu_item(), getM_comment(), isIs_favorite(), getOrder_date(), getCustomer_username());
    }

    @Override
    public String toString() {
        return "MenuOrder{" +
                "id=" + id +
                ", menu_item=" + menu_item +
                ", m_comment='" + m_comment + '\'' +
                ", is_favorite=" + is_favorite +
                ", order_date='" + order_date + '\'' +
                ", customer_username=" + customer_username +
                '}';
    }
}
