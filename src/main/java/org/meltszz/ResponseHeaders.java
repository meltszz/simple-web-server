package org.meltszz;

public class ResponseHeaders {
    private static ResponseHeaders instance;

    public final String NOT_FOUND = "HTTP/1.1 404 Not Found";
    public final String OK = "HTTP/1.1 200 OK";
    public final String NOT_IMPLEMENTED = "HTTP/1.1 501 Not Implemented";

    private ResponseHeaders() {
    }

    public static ResponseHeaders getInstance() {
        if (instance == null) {
            instance = new ResponseHeaders();
        }

        return instance;
    }

    public String documentHeader(DocumentType documentType) {
        String header = "";
        if (documentType == DocumentType.HTML) {
            header = "";
        }

        if (documentType == DocumentType.CSS) {
            header = "";
        }

        if (documentType == DocumentType.JS) {
            header = "";
        }

        return header;
    }
}
