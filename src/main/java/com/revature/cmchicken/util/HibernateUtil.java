package com.revature.cmchicken.util;

<<<<<<< HEAD
import com.revature.cmchicken.credit_card.CreditCard;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.order.Order;

=======
import com.revature.cmchicken.customer.Customer;
>>>>>>> b35ed4996b44af32572dd94d720335f38997b9fe
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

<<<<<<< HEAD
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
=======
>>>>>>> b35ed4996b44af32572dd94d720335f38997b9fe
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            Properties prop = new Properties();
<<<<<<< HEAD


=======
>>>>>>> b35ed4996b44af32572dd94d720335f38997b9fe
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("hibernate.properties"));

            configuration.setProperties(prop);

<<<<<<< HEAD

            /* TODO: SET up AZURE */
            /* Azure doen't like Threads and loading resources via a ClassLoader
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("hibernate.properties"));


            configuration.setProperties(prop);

            String url = System.getenv("SQLAZURECONNSTR_cmchikenDB");
            String username = System.getenv("DBUSER");
            String password = System.getenv("DBPASS");

            configuration.setProperties("hibernate.connection.url", url);
            configuration.setProperties("hibernate.connection.username", username );
            configuration.setProperties("hibernate.connection.password", password);
            configuration.setProperties("hibernate.direct", "org.hibernate.dialect.SQLServerDialect");
            configuration.setProperties("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            configuration.setProperties("hibernate.show_sql",  "true");
            configuration.setProperties("hibernate.hbm2ddl.auto", "update");
            */


            /* hibernate.properties
            hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
            hibernate.connection.driver_class=org.postgresql.Driver
            hibernate.connection.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=cmchicken
            hibernate.connection.username=postgres
            hibernate.connection.password=password
            show_sql=true
            hbm2ddl.auto=update
             */


            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(CreditCard.class);
=======
            configuration.addAnnotatedClass(Customer.class);
            //configuration.addAnnotatedClass(Menu.class);
            //configuration.addAnnotatedClass(Order.class);
            //configuration.addAnnotatedClass(CreditCard.class);
>>>>>>> b35ed4996b44af32572dd94d720335f38997b9fe

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
