package com.revature.cmchicken.util.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.customer.CustomerServices;
import com.revature.cmchicken.util.exceptions.AuthenticationException;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final CustomerServices customerServices;
    private final ObjectMapper mapper;

    public AuthServlet(CustomerServices customerServices, ObjectMapper mapper) {
        this.customerServices = customerServices;
        this.mapper = mapper;
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        try {

            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Customer authCustomer = customerServices.authenticateCustomer(loginCreds.getUsername(), loginCreds.getCpassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authCustomer", authCustomer);

            String payload = mapper.writeValueAsString(authCustomer);

            resp.getWriter().write(payload);
            resp.setStatus(200);

        } catch (AuthenticationException | InvalidRequestException e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        req.getSession().invalidate();
        resp.getWriter().write("Customer has logged out!");
    }
}