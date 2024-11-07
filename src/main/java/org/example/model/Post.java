package org.example.model;

import java.time.LocalDateTime;

public class Post {
    private String content;
    private User user;
    private Stream stream;
    private LocalDateTime creationDate;

    public Post(String content, User user, Stream stream) {
        this.content = content;
        this.user = user;
        this.stream = stream;
        this.creationDate = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public Stream getStream() {
        return stream;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}