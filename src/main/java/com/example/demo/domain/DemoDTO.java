package com.example.demo.domain;

import java.util.UUID;

public class DemoDTO {
    
    private UUID id;
    private String markdownContent;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }
}
