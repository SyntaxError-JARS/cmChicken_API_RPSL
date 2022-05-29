package com.revature.cmchicken.menu;


import com.revature.cmchicken.util.exceptions.InvalidRequestException;

import java.io.IOException;
import java.util.List;

public class MenuServices {

    private MenuDao menuDao;

    public MenuServices(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public Menu Createitem_name(Menu newMenu) {
        if (!validatedMenu(newMenu)) {
            throw new InvalidRequestException("Menu input was not validated, either empty Strings or null values");
        }
        return newMenu;
    }

    public List<Menu> readAll() throws IOException {
        List<Menu> item_name = menuDao.findAll();

        return item_name;
    }

    public Menu readByID(String item_name) {
        Menu singleitem_name = menuDao.findById(item_name);
        return singleitem_name;
    }

    public Menu update(Menu updateitem_name) {
        if(!menuDao.update((updateitem_name))){
            return null;
        }

        return updateitem_name;
    }

    public boolean delete(String item_name) {return menuDao.delete(item_name);}


    private boolean validatedMenu(Menu newMenu) {
        if(newMenu == null) return false;
        if(newMenu.getItem_name() == null || newMenu.getItem_name().trim().equals(("")))return false;
        if(newMenu.getCost() == 0 ) return false;
        if(newMenu.getProtein() == null || newMenu.getProtein().trim().equals((""))) return false;
        if(newMenu.getIs_substitutable() == false ) return false;
        return true;
    }


}
