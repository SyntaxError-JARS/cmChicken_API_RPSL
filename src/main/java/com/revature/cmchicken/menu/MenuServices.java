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

    }


    @Override
    public Menu create(Menu newMenu) {
        logger.info("Customer trying to be registered: " + newMenu);
        if(!validatedMenu(newMenu)) {
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
    }
        Menu persistedMenu = menuDao.create(newMenu);
        if(persistedMenu == null){
            throw new ResourcePersistenceException("Menu was not persisted to the database upon registration");
        }
        logger.info("Menu has been peristed: " + newMenu);
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
        if(newMenu.getCost() == 0 ) return false;
        if(newMenu.getProtein() == null || newMenu.getProtein().trim().equals((""))) return false;
        if(newMenu.getIs_substitutable() == false ) return false;
        return true;
    }


    private boolean validatedMenu(Menu newMenu) {
        if(newMenu == null) return false;
        if(newMenu.getItem_name() == null || newMenu.getItem_name().trim().equals(("")))return false;
        if(newMenu.getCost() == 0 ) return false;
        if(newMenu.getProtein() == null || newMenu.getProtein().trim().equals((""))) return false;
        if(newMenu.getIs_substitutable() == false ) return false;
        return true;
    }

    public Menu readByID(String menu) {
        return null;
    }

    public Menu Createitem_name(Menu newMenu) {
        return newMenu;
    }
}



