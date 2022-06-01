package com.revature.cmchicken.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        if(req.getParameter("menu") != null){
            Menu menu = menuServices.readByID("menu");
            String payload = mapper.writeValueAsString(menu);
            resp.getWriter().write(payload);
            return;
        }

        List<Menu> menu = menuServices.readAll();
        String payload = mapper.writeValueAsString(menu);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        Menu newMenu = mapper.readValue(req.getInputStream(), Menu.class); // from JSON to Java Object (Pokemon)
        Menu persistedMenuItem = menuServices.Createitem_name(newMenu);

        String payload = mapper.writeValueAsString(persistedMenuItem); // Mapping from Java Object (Pokemon) to JSON



        resp.getWriter().write("Persisted the provided pokemon as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        //if(!checkAuth(req, resp)) return;

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

        //if(!checkAuth(req, resp)) return;
        if(req.getParameter("menu") == null){

            resp.getWriter().write("Sample output");
            resp.setStatus(401);
            return;
        }
        String menuItem = req.getParameter("menu");

        try {
            menuServices.delete(menuItem);
            resp.getWriter().write("Delete menu from the database");
        } catch (ResourcePersistenceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

}
