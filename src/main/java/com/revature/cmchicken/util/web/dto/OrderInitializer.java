package com.revature.cmchicken.util.web.dto;


public class OrderInitializer {

    private String menu_item;
    private String m_comment;
    private boolean is_favorite;
    private String order_date;
    private String customer_username;

    public OrderInitializer() {}

    public OrderInitializer(String menu_item, String m_comment, boolean is_favorite, String order_date, String customer_username) {
        this.menu_item = menu_item;
        this.m_comment = m_comment;
        this.is_favorite = is_favorite;
        this.order_date = order_date;
        this.customer_username = customer_username;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setMenu_item(String menu_item) {
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

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }
}
