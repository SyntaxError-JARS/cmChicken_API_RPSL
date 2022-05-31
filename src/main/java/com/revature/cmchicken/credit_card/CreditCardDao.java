package com.revature.cmchicken.credit_card;

import com.revature.cmchicken.util.interfaces.Crudable;

import java.io.IOException;
import java.util.List;

public class CreditCardDao implements Crudable<CreditCard> {

    @Override
    public CreditCard create(CreditCard newObject) {
        return null;
    }

    @Override
    public List<CreditCard> findAll() throws IOException {
        return null;
    }

    @Override
    public CreditCard findById(String id) {
        return null;
    }

    @Override
    public boolean update(CreditCard updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
