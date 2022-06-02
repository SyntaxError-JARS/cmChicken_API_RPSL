package com.revature.cmchicken.menu;


import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;
import com.revature.cmchicken.util.interfaces.Serviceable;
import com.revature.cmchicken.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class MenuServices implements Serviceable<Menu> {

    private MenuDao menuDao;
    private Logger logger = Logger.getLogger();

    public MenuServices(MenuDao menuDao) {
        this.menuDao = menuDao;

    }


    @Override
    public Menu create(Menu newMenu) {
        System.out.println("Menu trying to be registered: " + newMenu);
        logger.info("Menu trying to be registered: ");

        System.out.println("before issue?");
        Menu persistedMenu = menuDao.create(newMenu);
        System.out.println("before issue????");

        if (persistedMenu == null) {
            System.out.println("FAILED TO CREATE USER");
            return null;
        }
//        logger.info("Menu has been persisted: " + newCustomer);
        return persistedMenu;
    }


    @Override
    public List<Menu> readAll() throws IOException {
        List<Menu> menuList = menuDao.findAll();

        return menuList;
    }



    @Override
    public Menu readById(String item_name) {
        Menu menu = menuDao.findById(item_name);
        return menu;
    }

    @Override
    public Menu update(Menu updatedMenu) {
        if (!menuDao.update(updatedMenu)){
            return null;
        }
        return updatedMenu;
    }

    @Override
    public boolean delete(String item_name) {
        return menuDao.delete(item_name);
    }

    @Override
    public boolean validateInput(Menu newMenu) {
        if(newMenu == null) return false;
        if(newMenu.getItem_name() == null || newMenu.getItem_name().trim().equals(("")))return false;
        if(newMenu.getPrice() == 0 ) return false;
        if(newMenu.getProtein() == null || newMenu.getProtein().trim().equals((""))) return false;
        if(newMenu.isIs_substitutable() == false ) return false;
        return true;
    }


    private boolean validatedMenu(Menu newMenu) {
        if(newMenu == null) return false;
        if(newMenu.getItem_name() == null || newMenu.getItem_name().trim().equals(("")))return false;
        if(newMenu.getPrice() == 0 ) return false;
        if(newMenu.getProtein() == null || newMenu.getProtein().trim().equals((""))) return false;
        if(newMenu.isIs_substitutable() == false ) return false;
        return true;
    }

    public Menu readByID(String menu) {
        return null;
    }

    public Menu Createitem_name(Menu newMenu) {
        return newMenu;
    }
}



