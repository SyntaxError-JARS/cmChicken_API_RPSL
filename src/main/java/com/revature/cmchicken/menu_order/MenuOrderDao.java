package com.revature.cmchicken.menu_order;


import com.revature.cmchicken.util.HibernateUtil;
import com.revature.cmchicken.util.interfaces.Crudable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.util.List;


public class MenuOrderDao implements Crudable<MenuOrder> {

    @Override
    public MenuOrder create(MenuOrder newMenuOrder) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMenuOrder);
            transaction.commit();
            return newMenuOrder;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<MenuOrder> findAll()  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<MenuOrder> menuOrderList = session.createQuery("FROM MenuOrder").list();
            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<MenuOrder> findAll(String order_date)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM MenuOrder WHERE order_date = :order_date";
            Query query = session.createQuery(sql);
            query.setParameter("order_date", order_date);
            List<MenuOrder> menuOrderList = query.list();

            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<MenuOrder> findAllByCustomer(String customer_username)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM MenuOrder WHERE customer_username = :customer_username";
            Query query = session.createQuery(sql);
            query.setParameter("customer_username", customer_username);
            List<MenuOrder> menuOrderList = query.list();

            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


    public List<MenuOrder> findAllByDate(String order_date)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM MenuOrder WHERE order_date = :order_date";
            Query query = session.createQuery(sql);
            query.setParameter("order_date", order_date);
            List<MenuOrder> menuOrderList = query.list();

            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


    public List<MenuOrder> findAllByMenu(String menu_item)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM MenuOrder WHERE menu_item = :menu_item";
            Query query = session.createQuery(sql);
            query.setParameter("menu_item", menu_item);
            List<MenuOrder> menuOrderList = query.list();

            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }


    public List<MenuOrder> findAll(String customer_username, String order_date)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM MenuOrder WHERE customer_username = :customer_username and order_date = :order_date";
            Query query = session.createQuery(sql);
            query.setParameter("customer_username", customer_username);
            query.setParameter("order_date", order_date);
            List<MenuOrder> menuOrderList = query.list();

            transaction.commit();
            return menuOrderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public MenuOrder findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            MenuOrder menuOrder = session.get(MenuOrder.class, Integer.parseInt(id));
            transaction.commit();
            return menuOrder;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(MenuOrder updateMenuOrder) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updateMenuOrder);
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
    public boolean delete(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            MenuOrder menuOrder = session.load(MenuOrder.class, id);
            session.remove(menuOrder);
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
