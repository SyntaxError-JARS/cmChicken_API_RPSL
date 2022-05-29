package com.revature.cmchicken.util.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.customer.CustomerService;
import com.revature.cmchicken.util.exceptions.AuthenticationException;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.logging.dto.LoginCreds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlets extends HttpServlet {
private final CustomerService customerService;
private final ObjectMapper mapper;

    public AuthServlets(CustomerService customerService, ObjectMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Customer authCustomer = customerService.authenticateCustomer(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authCustomer);

            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

}
