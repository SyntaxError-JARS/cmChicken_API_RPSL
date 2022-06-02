package com.revature.cmchicken.util;

import com.revature.cmchicken.credit_card.CreditCard;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.menu_order.MenuOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            Properties prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("hibernate.properties"));

            configuration.setProperties(prop);

            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(MenuOrder.class);
            configuration.addAnnotatedClass(CreditCard.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }

        if (session == null)
            session = sessionFactory.openSession();

        return session;
    }

    public static void closeSession(){
        session.close();
        session = null;
    }



}
