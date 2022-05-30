package com.revature.cmchicken.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.credit_card.CreditCardDao;
import com.revature.cmchicken.credit_card.CreditCardServices;
import com.revature.cmchicken.customer.CustomerDao;
import com.revature.cmchicken.customer.CustomerService;
import com.revature.cmchicken.customer.CustomerServlet;
import com.revature.cmchicken.menu.MenuDao;
import com.revature.cmchicken.menu.MenuServices;
import com.revature.cmchicken.util.servlets.AuthServlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();


        CustomerDao customerDao = new CustomerDao();
        CustomerService customerServices = new CustomerService(customerDao);
        //OrderDao orderDao = new OrderDao();
        //OrderServices orderServices = new OrderServices(orderDao);
        MenuDao menuDao = new MenuDao();
        MenuServices menuServices = new MenuServices(menuDao);
        CreditCardDao creditCardDao = new CreditCardDao();
        CreditCardServices creditCardServices = new CreditCardServices(creditCardDao);

        AuthServlets authServlet = new AuthServlets(customerServices , mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices , mapper);



        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        //context.addServlet("CustomerServlet", customerServlet).addMapping("/customers/*");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customers/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}

