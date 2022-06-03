package com.revature.cmchicken.util.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.cmchicken.credit_card.CreditCardServices;
import com.revature.cmchicken.customer.CustomerServices;
import com.revature.cmchicken.menu_order.MenuOrderServices;
import com.revature.cmchicken.menu.MenuServices;

import com.revature.cmchicken.credit_card.CreditCardDao;
import com.revature.cmchicken.menu.MenuDao;
import com.revature.cmchicken.menu_order.MenuOrderDao;
import com.revature.cmchicken.customer.CustomerDao;

import com.revature.cmchicken.customer.CustomerServlet;
import com.revature.cmchicken.menu_order.MenuOrderServlet;
import com.revature.cmchicken.credit_card.CreditCardServlets;
import com.revature.cmchicken.util.web.servlets.AuthServlet;
import com.revature.cmchicken.menu.MenuServlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        CustomerDao customerDao = new CustomerDao();
        MenuDao menuDao = new MenuDao();
        CreditCardDao creditCardDao = new CreditCardDao();
        MenuOrderDao menuOrderDao = new MenuOrderDao();

        // Instantiate and intialize the services with their dao dependency
        CustomerServices customerServices = new CustomerServices(customerDao);
        MenuServices menuServices = new MenuServices(menuDao);
        CreditCardServices creditCardServices = new CreditCardServices(creditCardDao);
        MenuOrderServices menuOrderServices = new MenuOrderServices(menuOrderDao,customerDao, menuDao);


        AuthServlet authServlet = new AuthServlet(customerServices, mapper);
        CustomerServlet customerServlet = new CustomerServlet(customerServices, mapper);
        MenuServlets menuServlet = new MenuServlets(menuServices, mapper);
        CreditCardServlets creditCardServlet = new CreditCardServlets(creditCardServices, mapper);
        MenuOrderServlet menuOrderServlet = new MenuOrderServlet(menuOrderServices, mapper, menuServices, customerServices);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("CustomerServlet", customerServlet).addMapping("/customer/*");
        context.addServlet("MenuServlet", menuServlet).addMapping("/menu/*");
        context.addServlet("CreditCardServlet", creditCardServlet).addMapping("/creditcard/*");
        context.addServlet("MenuOrderServlet", menuOrderServlet).addMapping("/menuorder/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}

