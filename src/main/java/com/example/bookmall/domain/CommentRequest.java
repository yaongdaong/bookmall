package com.example.bookmall.domain;

public class CommentRequest {
    private Long id;
    private String content;

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}