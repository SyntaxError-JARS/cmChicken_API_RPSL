package com.revature.cmchicken.services;

import com.revature.cmchicken.customer.CustomerDao;
import com.revature.cmchicken.customer.CustomerServices;
import com.revature.cmchicken.util.exceptions.AuthenticationException;
import com.revature.cmchicken.util.exceptions.InvalidRequestException;
import com.revature.cmchicken.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CustomerServiceTestSuite {

    CustomerServices sut;
    CustomerDao mockCustomerDao;

    @BeforeEach
    public void testPrep(){
        // in order to specify and mock a dao
        mockCustomerDao = mock(CustomerDao.class);
        sut = new CustomerServices(mockCustomerDao);
    }

    @Test
    public void test_validateInput_givenValidCustomer_returnTrue(){

        // Arrange
        Customer customer = new Customer("valid", "valid", "valid","valid",1000.00, 1);

        // Act
        boolean actualResult = sut.validateInput(customer);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsCustomer(){
        // Arrange
        Customer customer = new Customer("pie", "", "pie","pie",0.00, 1);
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockCustomerDao.create(customer)).thenReturn(customer);

        // Act
        Customer actualCustomer = sut.create(customer);

        // Assert
        Assertions.assertEquals("pie", actualCustomer.getUsername());
        Assertions.assertEquals("pie", actualCustomer.getFname());
        Assertions.assertEquals("pie", actualCustomer.getLname());
        Assertions.assertEquals("pie", actualCustomer.getPassword());
        Assertions.assertEquals("pie", actualCustomer.getBalance());
        Assertions.assertEquals("pie", actualCustomer.getIs_admin());
        // Mockito is verifying that the creation method was execute only once!
        verify(mockCustomerDao, times(1)).create(customer);
    }
    @Test
    public void test_create_givenInvalidUser_throwsInvalidRequestException(){
        // Arrange
        Customer customer = new Customer("pie", "", "pie","pie",0.00, 1);
        when(mockCustomerDao.create(customer)).thenReturn(customer);


        // Assert
        Assertions.assertThrows(InvalidRequestException.class, () -> { sut.create(customer); });
        verify(mockCustomerDao, times(0)).create(customer);
    }

    @Test
    public void test_create_givenRepeatedUserInformation_throwsInvalidRequestException(){
        Customer customer = new Customer("pie", "", "pie","pie",0.00, 1);
        when(mockCustomerDao.checkUserName(customer.getUsername())).thenReturn(true);


        // Assert
        Assertions.assertThrows(InvalidRequestException.class, () -> { sut.create(customer);});
        verify(mockCustomerDao, times(0)).create(customer);
    }

    @Test
    public void test_authenticateCustomer_givenInvalidInformation_throwsAuthenticationException(){
        Customer customer = new Customer("pie", "", "pie","pie",0.00, 0);
        when(mockCustomerDao.authenticateCustomer(customer.getUsername(), customer.getPassword())).thenReturn(null);


        Assertions.assertThrows(AuthenticationException.class, () -> { sut.authenticateCustomer(customer.getUsername(), customer.getPassword());});
        verify(mockCustomerDao, times(1)).authenticateCustomer(customer.getUsername(), customer.getPassword());
    }

}
