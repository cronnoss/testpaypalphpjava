package de.appsmart;

import org.junit.jupiter.api.*;

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
        HelpersAPI.setEndPoint(HelpersAPI.createPayment);
        HelpersAPI.createPaymentFromFrontToPhpIntegrationThenJavaServerRespondsUrlSandboxWithPayPalOneToken();
        Assertions.assertEquals(201, HelpersAPI.statusCode);
        Assertions.assertNotNull(HelpersAPI.transactionID);
        Assertions.assertNotNull(HelpersAPI.redirectURL);
    }

    @Test
    @Order(2)
    void CanCheckStatusOfTransactionIdInPHPIntegration() {
        HelpersAPI.setEndPoint(HelpersAPI.endPointStatus);
        HelpersAPI.checkStatusOfTransactionIdInPHPIntegration();
        Assertions.assertEquals(200, HelpersAPI.statusCode);
        Assertions.assertEquals("CREATED", HelpersAPI.status);
    }

    @AfterAll
    public static void clearConfig() {
        HelpersAPI.resetBaseURI();
        HelpersAPI.resetEndPoint();
    }
}