package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Stream {
    private String title;
    private List<Post> posts;

    public Stream(String title) {
        this.title = title;
        this.posts = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}