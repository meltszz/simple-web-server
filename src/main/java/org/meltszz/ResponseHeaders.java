package org.meltszz;

import java.io.FileReader;
import java.io.IOException;

public class ResponseHeaders {
    private static ResponseHeaders instance;

    public final String CRLF = "\n\r";
    private static final int DOCUMENT_MAX_SIZE = 1024;

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

    public String documentHeader(DocumentType documentType, String documentPath) throws IOException {
        String header = "";
        if (documentType == DocumentType.HTML) {
            char[] fileBuffer = new char[DOCUMENT_MAX_SIZE];
            FileReader documentReader = new FileReader(documentPath + "/" + "index.html");
            int fileLength = documentReader.read(fileBuffer);

            String documentContent = new String(fileBuffer);
            header = "HTTP/1.1 200 OK" + CRLF
                    + "Content-Type: " + documentType.getValue() + CRLF
                    + "Content-length: " + fileLength + CRLF
                    + CRLF + documentContent;
        }

        if (documentType == DocumentType.CSS) {
            char[] fileBuffer = new char[DOCUMENT_MAX_SIZE];
            FileReader documentReader = new FileReader(documentPath + "/" + "style.css");
            int fileLength = documentReader.read(fileBuffer);

            String documentContent = new String(fileBuffer);
            header = "HTTP/1.1 200 OK" + CRLF
                    + "Content-Type: " + documentType.getValue() + CRLF
                    + "Content-length: " + fileLength + CRLF
                    + CRLF + documentContent;
        }

        if (documentType == DocumentType.JS) {
            char[] fileBuffer = new char[DOCUMENT_MAX_SIZE];
            FileReader documentReader = new FileReader(documentPath + "/" + "script.js");
            int fileLength = documentReader.read(fileBuffer);

            String documentContent = new String(fileBuffer);
            header = "HTTP/1.1 200 OK" + CRLF
                    + "Content-Type: " + documentType.getValue() + CRLF
                    + "Content-length: " + fileLength + CRLF
                    + CRLF + documentContent;
        }

        return header;
    }
}
