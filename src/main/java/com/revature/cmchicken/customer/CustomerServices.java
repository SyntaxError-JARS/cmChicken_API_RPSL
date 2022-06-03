package com.revature.cmchicken.customer;

import com.revature.cmchicken.credit_card.CreditCardDao;
import com.revature.cmchicken.util.exceptions.AuthenticationException;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;
import com.revature.cmchicken.util.interfaces.Serviceable;
import com.revature.cmchicken.util.logging.Logger;

import java.util.List;


public class CustomerServices implements Serviceable<Customer> {

    private CustomerDao customerDao;

    private Logger logger = Logger.getLogger();

    public CustomerServices(CustomerDao customerDao) {this.customerDao = customerDao; }




    public boolean validateUserNameNotUsed(String username){
        System.out.println("CustomerServices::create() - Customer trying to be registered: " + username);
        return customerDao.checkUserName(username);
    }

    @Override
    public Customer create(Customer newCustomer) {
        logger.info("CustomerServices::create() - Customer trying to be registered: " + newCustomer);
        System.out.println("CustomerServices::create() - Customer trying to be registered: " + newCustomer.getUsername());
        if(!validateInput(newCustomer)) {
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateUserNameNotUsed(newCustomer.getUsername())) {
            throw new InvalidRequestException("User username is already in use. Please try again with another username or login into previous made account.");
        }

        Customer persistedCustomer = customerDao.create(newCustomer);
        if(persistedCustomer == null){
            throw new ResourcePersistenceException("Customer was not persisted to the database upon registration");
        }
        logger.info("Customer has been peristed: " + newCustomer);
        return persistedCustomer;
    }

    @Override
    public List<Customer> readAll() {
        List<Customer> customerList = customerDao.findAll();
        return customerList;
    }

    @Override
    public Customer readById(String username) {
        Customer customer = customerDao.findById(username);
        return customer;
    }

    @Override
    public Customer update(Customer updateCustomer) {
        if (! customerDao.update(updateCustomer)){
            return null;
        }
        return updateCustomer;
    }

    @Override
    public boolean delete(String username) {
        return customerDao.delete(username);
    }

    @Override
    public boolean validateInput(Customer object) {
        if(object == null) return false;
        if(object.getFname() == null || object.getFname().trim().equals("")) return false;
        if(object.getLname() == null || object.getLname().trim().equals("")) return false;
        if(object.getUsername() == null || object.getUsername().trim().equals("")) return false;
        if(object.getCpassword() == null || object.getLname().trim().equals("")) return false;
        return true;
    }

    public Customer authenticateCustomer(String username, String password){
        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry.");
        }
        Customer autheticatedCustomer = customerDao.authenticateCustomer(username, password);

        if (autheticatedCustomer == null) {
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return autheticatedCustomer;
    }
}
