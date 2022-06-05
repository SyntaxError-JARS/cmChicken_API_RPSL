package com.revature.cmchicken.credit_card;

import com.revature.cmchicken.menu_order.MenuOrder;
import com.revature.cmchicken.util.HibernateUtil;
import com.revature.cmchicken.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao implements Crudable<CreditCard> {

    @Override
    public CreditCard create(CreditCard newCreditCard) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCreditCard);
            transaction.commit();
            return newCreditCard;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<CreditCard> findAll() throws IOException {


        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            ArrayList<CreditCard> creditCard = (ArrayList<CreditCard>) session.createQuery("FROM CreditCard").list();
            return creditCard;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
        return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<CreditCard> findAllByUsername(String customer_username)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM CreditCard WHERE customer_username = :customer_username";
            Query query = session.createQuery(sql);
            query.setParameter("customer_username", customer_username);
            List<CreditCard> creditCardList = query.list();

            transaction.commit();
            return creditCardList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<CreditCard> findAllByDate(String order_date)  {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            String sql = " FROM CreditCard WHERE order_date = :order_date";
            Query query = session.createQuery(sql);
            query.setParameter("order_date", order_date);
            List<CreditCard> creditCardList = query.list();

            transaction.commit();
            return creditCardList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public CreditCard findById(String user_name) {


        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            CreditCard credit = session.get(CreditCard.class,user_name );
            return credit;
        }catch (HibernateException | IOException e) {
            e.printStackTrace();
        return null;
        }finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(CreditCard updatedCreditCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCreditCard);
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
    public boolean delete(String user_name) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            CreditCard creditCard = session.load(CreditCard.class,user_name);
            session.remove(creditCard);
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
