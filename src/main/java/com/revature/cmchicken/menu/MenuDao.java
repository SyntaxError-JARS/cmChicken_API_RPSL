package com.revature.cmchicken.menu;

import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.util.HibernateUtil;
import com.revature.cmchicken.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class MenuDao implements Crudable <Menu> {


    @Override
    public Menu create(Menu newMenu) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMenu);
            transaction.commit();
            return newMenu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Menu> findAll() {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Menu> menuList = session.createQuery("FROM Menu").list();
            transaction.commit();
            return menuList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Menu findById(String item_name) {

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.get(Menu.class, item_name);
            transaction.commit();
            return menu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Menu updateMenu) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updateMenu);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String username) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.load(Menu.class, username);
            session.remove(menu);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return  false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    
}
