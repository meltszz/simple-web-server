package org.meltszz.content;

public enum DocumentType {
    HTML("text/html"),
    JS("text/javascript"),
    CSS("text/css");

    private final String documentType;

    DocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getValue() {
        return this.documentType;
    }
}
