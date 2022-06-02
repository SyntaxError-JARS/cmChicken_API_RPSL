package com.revature.cmchicken.customer;

import com.revature.cmchicken.credit_card.CreditCardDao;
import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.util.HibernateUtil;
import com.revature.cmchicken.util.interfaces.Crudable;

import com.revature.cmchicken.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class CustomerDao implements Crudable <Customer> {


    @Override
    public Customer create(Customer newCustomer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCustomer);
            transaction.commit();
            return newCustomer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Customer> findAll() {

        try {
            Session session = HibernateUtil.getSession();

            Transaction transaction = session.beginTransaction();
            List<Customer> customerList = session.createQuery("FROM Customer").list();
            transaction.commit();
            return customerList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Customer findById(String username) {

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, username);
            transaction.commit();
            return customer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Customer updateCustomer) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updateCustomer);
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
            Customer customer = session.load(Customer.class, username);
            session.remove(customer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return  false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public Customer authenticateCustomer(String username, String password){
        try{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

//            Query query = session.createQuery("from customer where username= :username and password= :password");
//            query.setParameter("username", username);
//            query.setParameter("password", password);
            Customer customer = session.get(Customer.class, username);
            transaction.commit();
            if (! customer.getPassword().equals(password) )
                return null;

            return customer;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }

    }


    public boolean checkUserName(String username) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where username= :username");
            query.setParameter("username", username);
            Customer customer = (Customer) query.uniqueResult();
            transaction.commit();
            if(customer == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
