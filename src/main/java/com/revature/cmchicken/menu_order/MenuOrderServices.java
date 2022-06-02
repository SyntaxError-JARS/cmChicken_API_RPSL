package com.revature.cmchicken.menu_order;

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

public class MenuOrderServices implements Serviceable<MenuOrder> {

    private final MenuOrderDao menuOrderDao;
    private final CustomerDao customerDao;
    private final MenuDao menuDao;

    public MenuOrderServices(MenuOrderDao menuOrderDao, CustomerDao customerDao, MenuDao menuDao) {
        this.menuOrderDao = menuOrderDao;
        this.customerDao = customerDao;
        this.menuDao = menuDao;
    }




    @Override
    public MenuOrder create(MenuOrder newMenuOrder) {
        return menuOrderDao.create(newMenuOrder);
    }

    @Override
    public List<MenuOrder> readAll() {
        return menuOrderDao.findAll();
    }

    public List<MenuOrder> readAll(String order_date){
        return menuOrderDao.findAll(order_date);
    }

    @Override
    public MenuOrder readById(String id) {
        return menuOrderDao.findById(id);
    }

    @Override
    public MenuOrder update(MenuOrder updateMenuOrder) {
        if(!menuOrderDao.update(updateMenuOrder)) {
            return null;
        }
        return updateMenuOrder;
    }

    @Override
    public boolean delete(String id) {
        return menuOrderDao.delete(id);
    }

    @Override
    public boolean validateInput(MenuOrder object) {
        return false;
    }
}
