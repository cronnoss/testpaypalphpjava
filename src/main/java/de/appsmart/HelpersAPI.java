package de.appsmart;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HelpersAPI {

    //Global Setup Variables
    public static String path;
    public static String jsonPathTerm;

    //Endpoints
    public static String endPointStatus = "/status?transaction_id=";
    public static String endPointAuth = "login?authClient=loki";


    //Response items
    public static String transactionID;
    public static String redirectURL;
    public static Integer statusCode = null; //Int
    public static String status;

    //Sets Base URI
    public static void setBaseURI() {
        RestAssured.baseURI = "https://php-integration.devteam.win/api/v1/payments";
    }

    //Sets endpoint
    public static void setEndPoint(String endPoint) {
        RestAssured.basePath = endPoint;
        System.out.println(endPoint);
    }

    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    //Reset endpoint
    public static void resetEndPoint() {
        RestAssured.basePath = null;
    }

    static Response createPaymentFromFrontToPhpIntegrationThenJavaServerRespondsUrlSandboxWithPayPalOneToken() {
        String basketData = "{\n  " +
                "\"data\": {\n    " +
                "\"payment_method\": 5,\n    " +
                "\"branch_id\": \"1765\",\n    " +
                "\"pickup\": true,\n    " +
                "\"delivery_type\": \"selfcollect\",\n    " +
                "\"discount\": 20,\n    " +
                "\"lang\": {\n      " +
                "\"name\": \"Deutsch\",\n      " +
                "\"icon\": \"/assets/images/de.svg\",\n      " +
                "\"parent\": \"de\",\n      " +
                "\"id\": \"de\",\n      " +
                "\"active\": true\n    },\n    " +
                "\"displayedPrice\": 16,\n    " +
                "\"payment\": 16,\n    " +
                "\"b\": [\n      " +
                "{\n        " +
                "\"p\": 252875,\n        " +
                "\"q\": 4,\n        " +
                "\"sid\": 16768,\n        " +
                "\"bm\": [\n          " +
                "{\n            " +
                "\"gi\": 18456,\n            " +
                "\"id\": \"40\"\n          " +
                "}\n        " +
                "],\n        " +
                "\"xm\": [],\n        " +
                "\"nxm\": [],\n        " +
                "\"b\": [\n          " +
                "\"40\"\n        " +
                "],\n        " +
                "\"x\": [],\n        " +
                "\"nx\": [],\n        " +
                "\"comment\": \"\"\n      " +
                "}\n    " +
                "],\n    " +
                "\"web_version\": \"3.12.0\",\n   " +
                "\"n2\": 0,\n    " +
                "\"delivery_time\": null,\n    " +
                "\"selfcollect_time\": null,\n    " +
                "\"first_name\": \"Eric\",\n    " +
                "\"last_name\": \"Cartman\",\n    " +
                "\"zip\": \"65189\",\n   " +
                "\"city\": \"Wiesbaden\",\n    " +
                "\"company\": \"\",\n    " +
                "\"email\": \"eric@park.com\",\n    " +
                "\"phone\": \"3333333333\",\n    " +
                "\"street\": \"Abraham-Lincoln-Straße\",\n    " +
                "\"street_no\": \"7\",\n    " +
                "\"floor\": \"\",\n    " +
                "\"comment\": \"\\nrespect my authorita\",\n   " +
                "\"hunger_de\": 0,\n    " +
                "\"cpn\": null,\n    " +
                "\"allowContactMe\": false,\n    " +
                "\"platform\": \"web\",\n    " +
                "\"front_transaction_uuid\": \"3be6c742-9b8a-470c-8ebb-1b7fa03c2afa\",\n    " +
                "\"apartment_num\": \"\",\n    " +
                "\"address_id\": \"EjFBYnJhaGFtLUxpbmNvbG4tU3RyYcOfZSA3LCBXaWVzYmFkZW4sIERldXRzY2hsYW5kIjASLgoUChIJ2QP0fZO9vUcRv10McRu2OjgQByoUChIJk6UPI5G9vUcRMzXeJqxhP-4\",\n" +
                "\"staircase\": \"\"\n  " +
                "},\n  " +
                "\"appversion\": \"2.3.96\",\n  " +
                "\"returnUrl\": \"http://hermes-dev.devteam.win/-wiesbaden-5/1765/checkout?success=true\",\n  " +
                "\"cancelUrl\": \"http://hermes-dev.devteam.win/-wiesbaden-5/1765/checkout?success=false\",\n  " +
                "\"paymentSystem\": \"paypal\"\n}";

        Response response =
                given().header("Content-Type", "application/json")
                        .body(basketData)
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .extract().response();

        statusCode = response.getStatusCode();
        transactionID = response.jsonPath().get("transactionID");
        redirectURL = response.jsonPath().get("redirectURL");
        System.out.println("statusCode" + statusCode);
        System.out.println("transactionID = " + transactionID);
        System.out.println("redirectURL = " + redirectURL);

        return response;
    }

    static void checkStatusOfTransactionIdInPHPIntegration() {
        System.out.println(RestAssured.baseURI + endPointStatus + transactionID);
        Response response =
                given().header("Content-Type", "application/json")
                        .when()
                        .get(endPointStatus + transactionID)
                        .then()
                        .log().all()
                        .extract().response();
        status = response.jsonPath().get("status");
        System.out.println("transactionID = " + transactionID);
        System.out.println("status = " + status);

    }
}
