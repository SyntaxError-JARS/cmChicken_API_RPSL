package com.revature.cmchicken.menu_order;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cmchicken.customer.Customer;
import com.revature.cmchicken.customer.CustomerServices;
import com.revature.cmchicken.menu.Menu;
import com.revature.cmchicken.menu.MenuServices;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;
import com.revature.cmchicken.util.interfaces.Authable;
import com.revature.cmchicken.util.web.dto.OrderInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.cmchicken.util.interfaces.Authable.checkAuth;

public class MenuOrderServlet extends HttpServlet implements Authable {

    private final MenuOrderServices menuOrderServices;
    private final ObjectMapper mapper;

    private final MenuServices menuServices;
    private final CustomerServices customerServices;

    public MenuOrderServlet(MenuOrderServices orderService, ObjectMapper mapper, MenuServices menuService, CustomerServices customerServices) {
        this.menuOrderServices = orderService;
        this.mapper = mapper;
        this.menuServices = menuService;
        this.customerServices = customerServices;
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


        System.out.println("MenuOrderServlet::doGet()----let do get");

        System.out.println("----------------------------------------1");
        if(req.getParameter("id") != null){
            MenuOrder menuOrder = menuOrderServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(menuOrder);
            resp.getWriter().write(payload);
            return;
        }
        System.out.println("----------------------------------------2");
        if(req.getParameter("order_date") != null){
            List<MenuOrder> menuOrderList = menuOrderServices.readAll(req.getParameter("order_date"));
            String payload = mapper.writeValueAsString(menuOrderList);
            resp.getWriter().write(payload);
            return;
        }
        System.out.println("----------------------------------------3");
        if(req.getParameter("customer_username") != null){
            List<MenuOrder> menuOrderList = menuOrderServices.readAllByCustomer(req.getParameter("customer_username"));
            String payload = mapper.writeValueAsString(menuOrderList);
            resp.getWriter().write(payload);
            return;
        }

        List<MenuOrder> menuOrderList = menuOrderServices.readAll();
        String payload = mapper.writeValueAsString(menuOrderList);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        MenuOrder newMenuOrder = new MenuOrder();
        OrderInitializer initOrder = mapper.readValue(req.getInputStream(), OrderInitializer.class); // from JSON to Java Object (Order)
        System.out.println("--------doPost():: initOrder" + initOrder);
        Customer customer = new Customer();
        try{
            customer = customerServices.readById(String.valueOf(initOrder.getCustomer_username()));
            Menu menu = menuServices.readById(initOrder.getMenu_item());
            customer.setBalance(customer.getBalance() + menu.getPrice());

            newMenuOrder.setMenu_item(menu);
            newMenuOrder.setM_comment(initOrder.getM_comment());
            newMenuOrder.setIs_favorite(initOrder.isIs_favorite());
            newMenuOrder.setOrder_date(initOrder.getOrder_date());
            newMenuOrder.setCustomer_username(customer);
        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }

        System.out.println(newMenuOrder);
        MenuOrder persistedMenuOrder = menuOrderServices.create(newMenuOrder);
        Customer persistedCustomer = customerServices.update(customer);


        String payload = mapper.writeValueAsString(persistedMenuOrder); // Mapping from Java Object (Order) to JSON



        resp.getWriter().write("Persisted the provided order as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

//       if(!checkAuth(req, resp)) return;

        OrderInitializer initOrder = mapper.readValue(req.getInputStream(), OrderInitializer.class);

        MenuOrder menuOrderUpdate = mapper.readValue(req.getInputStream(), MenuOrder.class);
        MenuOrder updatedMenuOrder = menuOrderServices.update(menuOrderUpdate);


        String payload = mapper.writeValueAsString(updatedMenuOrder);
        resp.getWriter().write(payload);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

    //   if(!checkAuth(req, resp)) return;

        if(req.getParameter("id") == null){
            resp.getWriter().write("In order to delete, please provide your the order id into the url with ?id=example-12");
            resp.setStatus(401);
        }
        String id = req.getParameter("id");
        try{
            menuOrderServices.delete(id);
            resp.getWriter().write("Delete order from the database");
        } catch (ResourcePersistenceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

}
