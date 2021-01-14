package de.appsmart;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class API {

    @BeforeAll
    public static void setConfig() {
        //Test Setup
        HelpersAPI.setBaseURI(); //Setup Base URI
        //	HelpersAPI.setBasePath("/{id}"); //Setup Base Path
    }

    @Test
    @Order(1)
    void CanCreatePaymentFromFrontToPhpIntegration() {
        HelpersAPI.setEndPoint("/create");
        HelpersAPI.createPaymentFromFrontToPhpIntegrationThenJavaServerRespondsUrlSandboxWithPayPalOneToken();
        Assertions.assertEquals(201, HelpersAPI.statusCode);
        Assertions.assertNotNull(HelpersAPI.transactionID);
        Assertions.assertNotNull(HelpersAPI.redirectURL);

//    }
//
//    @Test
//    @Order(2)
//    void CanCheckStatusOfTransactionIdInPHPIntegration() {
        HelpersAPI.checkStatusOfTransactionIdInPHPIntegration();
        Assertions.assertEquals(200, HelpersAPI.statusCode);
        Assertions.assertEquals(HelpersAPI.transactionID, HelpersAPI.transactionID);
        Assertions.assertEquals("CREATED", HelpersAPI.status);
    }
}
