package com.revature.cmchicken.credit_card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.customer.CustomerServices;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.revature.cmchicken.util.web.dto.CreditCardInitializer;


public class CreditCardServlets extends HttpServlet {

    private final CreditCardServices creditCardServices;

    private final CustomerServices customerServices;
    private final ObjectMapper mapper;


    public CreditCardServlets(CreditCardServices creditCardServices, CustomerServices customerServices, ObjectMapper mapper) {
        this.creditCardServices = creditCardServices;
        this.customerServices = customerServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(req.getParameter("id") != null){
            CreditCard creditCard;
            try {
                creditCard = creditCardServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String payload = mapper.writeValueAsString(creditCard);
            resp.getWriter().write(payload);
            return;
        }

        List<CreditCard> creditCards = creditCardServices.readAll();
        String payload = mapper.writeValueAsString(creditCards);

        resp.getWriter().write(payload);
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

        CreditCard newCreditCard = new CreditCard();

        CreditCardInitializer initCreditCard = mapper.readValue(req.getInputStream(), CreditCardInitializer.class);
        try {
            Customer customer = customerServices.readById(String.valueOf(initCreditCard.getCustomer_username()));

            newCreditCard.setCc_number(initCreditCard.getCc_number());
            newCreditCard.setCc_name(initCreditCard.getCc_name());
            newCreditCard.setCvv(initCreditCard.getCvv());
            newCreditCard.setExp_date(initCreditCard.getExp_date());
            newCreditCard.setZip(initCreditCard.getZip());
            newCreditCard.setCc_limit(initCreditCard.getCc_limit());
            newCreditCard.setCustomer_username(customer);

        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }

        System.out.println(newCreditCard);
        CreditCard persistedCreditCard = creditCardServices.create(newCreditCard);
        // Mapping from Java Object (CreditCard) to JSON
        String payload = mapper.writeValueAsString(persistedCreditCard);

        resp.getWriter().write("Persisted the provided Credit Card as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        //if(!checkAuth(req, resp)) return;

        CreditCard creditCardUpdate = mapper.readValue(req.getInputStream(), CreditCard.class);
        CreditCard updatedCreditCard = creditCardServices.update(creditCardUpdate);


        String payload = mapper.writeValueAsString(updatedCreditCard);
        resp.getWriter().write(payload);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//TODO: implement checkAuth
        //if(!checkAuth(req, resp)) return;
        if(req.getParameter("creditCard") == null){

            resp.getWriter().write("Sample output");
            resp.setStatus(401);
            return;
        }
        String menuItem = req.getParameter("creditCard");

        try {
            creditCardServices.delete(menuItem);
            resp.getWriter().write("Delete credit card from the database");
        } catch (ResourcePersistenceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }
}
