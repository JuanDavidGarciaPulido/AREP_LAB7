package org.example.service;

import org.example.model.Post;
import org.example.model.Stream;
import org.example.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class DataStore {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Stream> streams = new HashMap<>();
    private final List<Post> posts = new ArrayList<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addStream(Stream stream) {
        streams.put(stream.getTitle(), stream);
    }

    public Stream getStream(String title) {
        return streams.get(title);
    }

    public Collection<Stream> getAllStreams() {
        return streams.values();
    }

    public void addPost(Post post) {
        posts.add(post);
        post.getStream().addPost(post);
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }
}
