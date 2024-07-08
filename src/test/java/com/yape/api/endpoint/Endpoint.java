package com.yape.api.endpoint;

/**
 * This class provides utility methods for common operations in Java.
 * It contains methods for loading properties, retrieving templates, queries, and variables,
 * generating random numbers and codes, converting lists to strings, and parsing data tables.
 * The methods in this class are static and can be accessed without creating an instance of the class.
 *
 * @author Joham Romucho
 */
public enum Endpoint {

    API_AUTH("/auth"),
    API_PING("/ping"),
    API_BOOKING("/booking");


    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
