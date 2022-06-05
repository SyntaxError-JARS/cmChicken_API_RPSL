package com.revature.cmchicken.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.cmchicken.util.interfaces.Authable.checkAuth;

public class MenuServlets extends HttpServlet {
    private final MenuServices menuServices;
    private final ObjectMapper mapper;


    public MenuServlets(MenuServices menuServices, ObjectMapper mapper) {
        this.menuServices = menuServices;
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

//        if(req.getParameter("username") != null && req.getParameter("password") != null){
//            resp.getWriter().write("Hey you have the follow user name and password " + req.getParameter("username") + " " + req.getParameter("password") );
//            return;
//        }

        if(req.getParameter("item_name") != null){
            Menu menu;
            try {
                menu = menuServices.readById(req.getParameter("item_name")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String payload = mapper.writeValueAsString(menu);
            resp.getWriter().write(payload);
            return;
        }
        if(req.getParameter("id") != null){
            Menu menu;
            try {
                menu = menuServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String payload = mapper.writeValueAsString(menu);
            resp.getWriter().write(payload);
            return;
        }

        List<Menu> menus = menuServices.readAll();
        String payload = mapper.writeValueAsString(menus);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

//        if(!checkAuth(req, resp)) return;
//        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");
//        System.out.println("is admin: " + authCustomer.isIs_admin() );
//        if(!authCustomer.isIs_admin()) {
//            resp.getWriter().write("In order to insert a menu, you supposed to be admin");
//            resp.setStatus(401);
//            return;
//        }

        Menu persistedMenu;
        try {
            Menu menu = mapper.readValue(req.getInputStream(), Menu.class);
            persistedMenu = menuServices.create(menu);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedMenu);

        resp.getWriter().write("Persisted the provided Menu as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");
        System.out.println("is admin: " + authCustomer.isIs_admin() );
//        if(!authCustomer.isIs_admin()) {
//            resp.getWriter().write("In order to delete a menu, you supposed to be admin");
//            resp.setStatus(401);
//            return;
//        }


        Menu menuUpdate = mapper.readValue(req.getInputStream(), Menu.class);
        Menu updatedMenuItem = menuServices.update(menuUpdate);


        String payload = mapper.writeValueAsString(updatedMenuItem);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req,resp)) return;
        Customer authCustomer = (Customer) req.getSession().getAttribute("authCustomer");

        if (req.getParameter("item_name") == null && req.getParameter("id") == null) {
            resp.getWriter().write("In order to delete, please provide your item_name as a verification into the url with ?item_name=put here item name");
            resp.setStatus(401);
            return;
        }
System.out.println("item name: " + req.getParameter("item_name"));
        System.out.println("is admin: " + authCustomer.isIs_admin() );
//        if(!authCustomer.isIs_admin()) {
//            resp.getWriter().write("In order to delete a menu, you supposed to be admin");
//            resp.setStatus(401);
//            return;
//        }

        String item_name = req.getParameter("item_name");
        try {
            menuServices.delete(item_name);
            resp.getWriter().write("Delete a menu from the database");
        } catch (ResourcePersistenceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }


       // Menu authMenu = (Menu) req.getSession().getAttribute("authMenu");


//        if(!authMenuCard.getUsername().equals(username)){
//            resp.getWriter().write("username provided does not match the user logged in, double check for confirmation of deletion");
//            return;
    }
}
