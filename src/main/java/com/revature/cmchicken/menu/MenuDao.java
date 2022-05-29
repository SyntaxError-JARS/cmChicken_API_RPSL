package com.revature.cmchicken.menu;

import com.revature.cmchicken.util.HibernateUtil;
import com.revature.cmchicken.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class MenuDao implements Crudable<Menu> {

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
    public List<Menu> findAll() throws IOException {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Menu> menu = (List<Menu>) session.createQuery("FROM Menu").list();
            return menu;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
        return null;

        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Menu findById(String item_name) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Menu menu = session.get(Menu.class, item_name);
            transaction.commit();
            return menu;
        }catch (HibernateException | IOException e) {
            e.printStackTrace();
        return null;

        }finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Menu updatedMenu) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedMenu);
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
    public boolean delete(String item_name) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.remove(item_name);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
        return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
