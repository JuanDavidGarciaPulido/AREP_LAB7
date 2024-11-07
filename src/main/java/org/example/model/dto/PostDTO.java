package org.example.model.dto;

public class PostDTO {
    public String content;
    public String username;
    public String streamTitle;

    public PostDTO(String content, String username, String streamTitle) {
        this.content = content;
        this.username = username;
        this.streamTitle = streamTitle;
    }
}