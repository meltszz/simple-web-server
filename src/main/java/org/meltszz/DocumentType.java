package org.meltszz;

public enum DocumentType {
    HTML("text/html"),
    JS("text/javascript"),
    CSS("text/css");

    private final String documentType;

    DocumentType(String documentType) {
        this.documentType = documentType;
    }
}
