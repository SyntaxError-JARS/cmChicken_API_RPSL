package com.revature.cmChicken.customer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.cmChicken.util.exceptions.ResourcePersistanceException;
import com.revature.cmChicken.util.interfaces.Authable;
import com.revature.cmChicken.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.cmChicken.util.interfaces.Authable.checkAuth;

public class CustomerServlet extends HttpServlet implements Authable {

    private final CustomerService customerService;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public CustomerServlet(CustomerService customerService, ObjectMapper mapper){
        this.customerService = customerService;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        // The below code allows to split information from the endpoint after the /customers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the /customers?id=x&email=y
        if(req.getParameter("username") != null && req.getParameter("password") != null){
            resp.getWriter().write("Hey you have the follow username and password " + req.getParameter("username") + " " + req.getParameter("password") );
            return;
        }

        // Handling the query params in the endpoint /customers?id=x
        if(req.getParameter("username") != null){
            Customer customer;
            try {
                customer = customerService.readById(req.getParameter("username")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(customer);
            resp.getWriter().write(payload);
            return;
        }

        List<Customer> customers = customerService.readAll();
        String payload = mapper.writeValueAsString(customers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        Customer reqCustomer = mapper.readValue(req.getInputStream(), Customer.class);

        if(authCustomer.getUsername().equals(reqCustomer.getUsername())) {

            Customer updatedCustomer = customerService.update(reqCustomer);

            String payload = mapper.writeValueAsString(updatedCustomer);

            resp.getWriter().write(payload);
        } else {
            resp.getWriter().write("password provided does not match the user currently logged in");
            resp.setStatus(403);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Customer customer = mapper.readValue(req.getInputStream(), Customer.class); // from JSON to Java Object (Pokemon)
        Customer persistedCustomer = customerService.create(customer);

        String payload = mapper.writeValueAsString(persistedCustomer); // Mapping from Java Object (Pokemon) to JSON

        resp.getWriter().write("Persisted the provided customer as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req,resp)) return;
        if(req.getParameter("password") == null){
            resp.getWriter().write("In order to delete, please provide your user password as a verification into the url with ?password=your@email.here");
            resp.setStatus(401);
            return;
        }

        String email = req.getParameter("password");
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        if(!authCustomer.getUsername().equals(email)){
            resp.getWriter().write("password provided does not match the user name logged in, double check for confirmation of deletion");
            return;
        }

        try {
            customerService.delete(email);
            resp.getWriter().write("Delete user from the database");
            req.getSession().invalidate();
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }
}