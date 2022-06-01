package com.revature.cmchicken.util.interfaces;

import com.revature.cmchicken.credit_card.CreditCard;

import java.io.IOException;
import java.util.List;

public interface Serviceable<T> {

    // Create
    T create(T newObject);


    // Read
    List<T> readAll() throws IOException;
    T readById(String id);

    // Update
    T update(T updatedObject);

    // Delete
    boolean delete(String id);

    boolean validateInput(T object);


}
