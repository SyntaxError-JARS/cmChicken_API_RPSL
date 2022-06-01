package com.revature.cmchicken.util;


import com.revature.cmchicken.credit_card.CreditCard;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.order.Order;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;


/*
    Singleton Design Pattern
    - Creational Pattern
    - Restricts that only a signle instance of this class can be made within the application
    - Constructor cannot be invoked outside of this class
    - Eager or Lazy singleton

    Factory Design Pattern
    - Creational PAttern
    - used to abstract away the creation/instantiation of the class

 */

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null){
            Configuration configuration = new Configuration();


            /* Azure doen't like Threads and loading resources via a ClassLoader

            Properties prop = new Properties();

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("hibernate.properties"));

            configuration.setProperties(prop);
             */
            /* TODO: SET up AZURE App Service */

            String url = System.getenv("SQLAZURECONNSTR_CmProjectDB");
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");

            configuration.setProperty("hibernate.connection.url", url);
            configuration.setProperty("hibernate.connection.username", username);
            configuration.setProperty("hibernate.connection.password", password);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");


            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(Order.class);
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
