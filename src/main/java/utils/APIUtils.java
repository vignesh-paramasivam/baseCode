package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;


public class APIUtils {

    private static String baseUrl = "https://infer.slanglabs.in";


    public static Response get(String endPoint, HashMap<String, String> headers) {
        return (RestAssured.given().headers(headers).baseUri(baseUrl)).get(endPoint);
    }

    public static Response post(String endPoint, String jsonBody, HashMap<String, String> headers) {
        return (RestAssured.given().headers(headers).body(jsonBody).baseUri(baseUrl)).post(endPoint);
    }
}
