package com.revature.cmchicken.order;

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

public class OrderServlet extends HttpServlet implements Authable {
    
    private final OrderServices orderServices;
    private final ObjectMapper mapper;

    private final MenuServices menuServices;
    private final CustomerServices customerServices;

    public OrderServlet(OrderServices orderService, ObjectMapper mapper, MenuServices menuService, CustomerServices customerServices) {
        this.orderServices = orderService;
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
        if(req.getParameter("id") != null){
            Order order = orderServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }

        List<Order> orderList = orderServices.readAll();
        String payload = mapper.writeValueAsString(orderList);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Order newOrder = new Order();
        OrderInitializer initOrder = mapper.readValue(req.getInputStream(), OrderInitializer.class); // from JSON to Java Object (Order)
        try{
            Customer customer = customerServices.readById(String.valueOf(initOrder.getCustomer_username()));
            Menu menu = menuServices.readById(initOrder.getMenu_item());

            newOrder.setMenu_item(initOrder.getMenu_item());
            newOrder.setComment(initOrder.getComment());
            newOrder.setIs_favorite(initOrder.isIs_favorite());
            newOrder.setOrder_date(initOrder.getOrder_date());
            newOrder.setCustomer_username(initOrder.getCustomer_username());
        }catch (Exception e){
            resp.getWriter().write(e.getMessage());
        }

        System.out.println(newOrder);
        Order persistedOrder = orderServices.create(newOrder);

        String payload = mapper.writeValueAsString(persistedOrder); // Mapping from Java Object (Order) to JSON



        resp.getWriter().write("Persisted the provided order as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        if(!checkAuth(req, resp)) return;

        Order orderUpdate = mapper.readValue(req.getInputStream(), Order.class);
        Order updatedOrder = orderServices.update(orderUpdate);


        String payload = mapper.writeValueAsString(updatedOrder);
        resp.getWriter().write(payload);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(!checkAuth(req, resp)) return;

        if(req.getParameter("id") == null){
            resp.getWriter().write("In order to delete, please provide your the order id into the url with ?id=example-12");
            resp.setStatus(401);
        }
        String id = req.getParameter("id");
        try{
            orderServices.delete(id);
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
