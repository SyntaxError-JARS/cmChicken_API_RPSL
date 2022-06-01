package com.revature.cmchicken.order;

import com.revature.cmchicken.customer.CustomerDao;
import com.revature.cmchicken.menu.MenuDao;

import com.revature.cmchicken.util.interfaces.Serviceable;

import java.util.List;

// TODO: Update later for
//          Make an order for a specific menu item
//          add a comment to the order to request a change,
//              if it is substitutable
//          favorite an order
//          view past orders by date
//          pay off your balance with your credit card

public class OrderServices implements Serviceable<Order> {

    private final OrderDao orderDao;
    private final CustomerDao customerDao;
    private final MenuDao menuDao;

    public OrderServices(OrderDao orderDao, CustomerDao customerDao, MenuDao menuDao) {
        this.orderDao = orderDao;
        this.customerDao = customerDao;
        this.menuDao = menuDao;
    }




    @Override
    public Order create(Order newOrder) {
        return orderDao.create(newOrder);
    }

    @Override
    public List<Order> readAll() {
        return orderDao.findAll();
    }

    public List<Order> readAll(String order_date){
        return orderDao.findAll(order_date);
    }

    @Override
    public Order readById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public Order update(Order updateOrder) {
        if(!orderDao.update(updateOrder)) {
            return null;
        }
        return updateOrder;
    }

    @Override
    public boolean delete(String id) {
        return orderDao.delete(id);
    }

    @Override
    public boolean validateInput(Order object) {
        return false;
    }
}
