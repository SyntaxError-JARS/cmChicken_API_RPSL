package com.revature.cmChicken.customer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.cmChicken.util.interfaces.Authable;
import com.revature.cmChicken.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.cmChicken.util.interfaces.Authable.checkAuth;

public class CustomerServlet extends HttpServlet implements Authable {

    private final CustomerService customerService;
    private final ObjectMapper mapper;
    private final Logger logger = logger.getLogger();

    public CustomerServlet(CustomerService customerService, ObjectMapper mapper){
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req,resp)) return;

        if(req.getParameter("id") != null && req.getParameter("password") != null) {


        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
