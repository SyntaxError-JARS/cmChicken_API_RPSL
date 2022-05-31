package com.revature.cmchicken.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

pulic Class ConnectionFactoryTestSuite {

        @Test
        public void test_getConnection_givenProvidedCredentials_returnValidConnection(){
            // Arrange & Acting
            try(Connection conn = ConnectionFactory.getInstance().getConnection();){
                System.out.println(conn);

                // Assert
                Assertions.assertNotNull(conn);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}