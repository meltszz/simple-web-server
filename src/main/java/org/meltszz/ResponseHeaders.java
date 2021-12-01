package org.meltszz;

public class ResponseHeaders {
    private static ResponseHeaders instance;

    public final String NOT_FOUND = "HTTP/1.1 404 Not Found";

    private ResponseHeaders() {
    }

    public static ResponseHeaders getInstance() {
        if (instance == null) {
            instance = new ResponseHeaders();
        }

        return instance;
    }

    public void hello() {

    }

}
