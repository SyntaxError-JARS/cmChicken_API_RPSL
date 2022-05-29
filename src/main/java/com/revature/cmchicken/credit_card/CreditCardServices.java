package com.revature.cmchicken.credit_card;

import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.util.exceptions.ResourcePersistenceException;

import java.io.IOException;
import java.util.List;

public class CreditCardServices {

    private CreditCardDao creditCardDao;
    public CreditCardServices(CreditCardDao creditCardDao) {this.creditCardDao = creditCardDao; }

    public CreditCard CreateCreditCard(CreditCard newCreditCard){

        if (!validateCreditCard (newCreditCard)){
            throw new InvalidRequestException("Credit card input was not validated, either empty Strings or null values");
        }
        CreditCard persistedCreditCard = creditCardDao.create(newCreditCard);
        if(persistedCreditCard == null){
            throw new ResourcePersistenceException("Credit card was not persisted to the database");
        }
        return newCreditCard;
    }

    public List<CreditCard> readAll() throws IOException {
        List<CreditCard> creditCard = creditCardDao.findAll();
        return creditCard;
    }

    public CreditCard readByID(String user_name){
        CreditCard creditCard = creditCardDao.findById(user_name);
        return creditCard;
    }

    public CreditCard update(CreditCard updatedCreditCard){
        if(!creditCardDao.update((updatedCreditCard))){
            return null;
        }
        return updatedCreditCard;
    }

    public boolean delete(String creditCard){
        return creditCardDao.delete(creditCard);
    }

    public boolean validateCreditCard(CreditCard newCreditCard){
        if(newCreditCard == null ) return false;
        if(newCreditCard.getCc_number() == null || newCreditCard.getCc_number().trim().equals((""))) return false;
        if (newCreditCard.getCc_name() == null || newCreditCard.getCc_name().trim().equals(("")))return false;
        if (newCreditCard.getCvv() == 0) return false;
        if (newCreditCard.getExp_date() == null || newCreditCard.getExp_date().trim().equals(("")))return false;
        if (newCreditCard.getZip() == 0) return false;
        if (newCreditCard.getLimit() == 0) return false;
        if (newCreditCard.getUser_name() == null || newCreditCard.getUser_name().trim().equals(("")))return false;

        return true;
    }


}
