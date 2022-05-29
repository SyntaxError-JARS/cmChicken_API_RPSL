package com.revature.cmchicken.credit_card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreditCardServlets extends HttpServlet {

    private final CreditCardServices creditCardServices;
    private final ObjectMapper mapper;

    public CreditCardServlets(CreditCardServices creditCardServices, ObjectMapper mapper) {
        this.creditCardServices = creditCardServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if (req.getParameter("pokemonName") != null) {
            CreditCard creditCard = creditCardServices.readByID(req.getParameter("creditCard"));
            String payload = mapper.writeValueAsString(creditCard);
            resp.getWriter().write(payload);
            return;
        }

        List<CreditCard> creditCardData = creditCardServices.readAll();
        String payload = mapper.writeValueAsString(creditCardData);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        CreditCard newCreditCard = mapper.readValue(req.getInputStream(), CreditCard.class); // from JSON to Java Object (Pokemon)
        CreditCard persistedCreditCard = creditCardServices.CreateCreditCard(newCreditCard);

        String payload = mapper.writeValueAsString(persistedCreditCard); // Mapping from Java Object (Pokemon) to JSON



        resp.getWriter().write("Sample output \n");
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
            creditCardServices.delete("username");
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
