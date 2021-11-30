package org.meltszz;

public class ResponseHeaders {
    public static String notFound() {
        return  "HTTP/1.1 404 Not Found";
    }

    public static String ok() {
        return  "HTTP/1.1 200 OK";
    }
}
