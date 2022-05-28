package com.revature.cmChicken.util;

import com.revature.cmChicken.customer.Customer;
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

            /* TODO: SET up AZURE */
            /* Azure doen't like Threads and loading resources via a ClassLoader
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("hibernate.properties"));

            configuration.setProperties(prop);
            */

            String url = System.getenv("SQLAZURECONNSTR_cmchikenDB");
            String dbuser = System.getenv("DBUSER") ;
            String dbpass = System.getenv("DBPASS") ;

            configuration.setProperties("hibernate.connection.url", url);
            configuration.setProperties("hibernate.connection.username", dbuser);
            configuration.setProperties("hibernate.connection.password", dbpass);
            configuration.setProperties("hibernate.direct", );
            configuration.setProperties("hibernate.connection.driver_class", url);
            configuration.setProperties("hibernate.show_sql", url);
            configuration.setProperties("hibernate.hbm2ddl.auto", url);


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
            //configuration.addAnnotatedClass(Menu.class);
            //configuration.addAnnotatedClass(Order.class);
            //configuration.addAnnotatedClass(CreditCard.class);

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
