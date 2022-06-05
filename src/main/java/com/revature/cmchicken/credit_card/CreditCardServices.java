package com.revature.cmchicken.credit_card;


import com.revature.cmchicken.customer.CustomerDao;
import com.revature.cmchicken.menu_order.MenuOrder;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;
import com.revature.cmchicken.util.interfaces.Serviceable;
import com.revature.cmchicken.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class CreditCardServices implements Serviceable<CreditCard> {

    private CreditCardDao creditCardDao;

    private CustomerDao customerDao;

    public CreditCardServices(CreditCardDao creditCardDao, CustomerDao customerDao) {
        this.creditCardDao = creditCardDao;
        this.customerDao = customerDao;
    }

    @Override
    public CreditCard create(CreditCard newCreditCard) {
        System.out.println("CreditCard trying to be registered: " + newCreditCard);


        System.out.println("before issue?");
        CreditCard persistedCreditCard = creditCardDao.create(newCreditCard);
        System.out.println("before issue????");

        if (persistedCreditCard == null) {
           return null;
        }
//        logger.info("CreditCard has been persisted: " + newCustomer);
        return persistedCreditCard;
    }


    @Override
    public List<CreditCard> readAll() throws IOException {
        List<CreditCard> creditCard = creditCardDao.findAll();
        return creditCard;
    }


    public List<CreditCard> readAll(String customer_username ) throws IOException {
        List<CreditCard> creditCard = creditCardDao.findAllByUsername(customer_username);
        return creditCard;
    }


    @Override
    public CreditCard readById(String user_name) {
        CreditCard creditCard = creditCardDao.findById(user_name);
        return creditCard;
    }

    public CreditCard readByCustomerUsername(String customer_username) {
        CreditCard creditCard = creditCardDao.findById(customer_username);
        return creditCard;
    }

    @Override
    public CreditCard update(CreditCard updatedCreditCard) {
        if(!creditCardDao.update((updatedCreditCard))){
            return null;
        }
        return updatedCreditCard;

    }

    @Override
    public boolean delete(String creditCard ) {
        return creditCardDao.delete(creditCard);
    }

    @Override
    public boolean validateInput(CreditCard object) {
        return false;
    }

    private boolean validateCreditCard(CreditCard newCreditCard) {
        if(newCreditCard == null ) return false;
        if(newCreditCard.getCc_number() == null || newCreditCard.getCc_number().trim().equals((""))) return false;
        if (newCreditCard.getCc_name() == null || newCreditCard.getCc_name().trim().equals(("")))return false;
        if (newCreditCard.getCvv() == 0) return false;
        if (newCreditCard.getExp_date() == null || newCreditCard.getExp_date().trim().equals(("")))return false;
        if (newCreditCard.getZip() == 0) return false;
        if (newCreditCard.getCc_limit() == 0) return false;
        if (newCreditCard.getCustomer_username() == null)return false;

        return true;
    }

    public CreditCard CreateCreditCard(CreditCard newCreditCard) {
        return newCreditCard;
    }

    public CreditCard readByID(String creditCard) {
        return null;
    }
}
