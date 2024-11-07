package org.example.service;

import org.example.model.Post;
import org.example.model.Stream;
import org.example.model.User;
import org.example.model.dto.PostDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostService {

    @Inject
    DataStore dataStore;

    private static final Logger logger = Logger.getLogger(PostService.class.getName());

    public List<PostDTO> getAllPosts() {
        return dataStore.getAllPosts().stream()
                .map(post -> new PostDTO(
                        post.getContent(),
                        post.getUser().getUsername(),
                        post.getStream().getTitle()))
                .collect(Collectors.toList());
    }

    public PostDTO createPost(String username, String content, String streamTitle) {
        logger.info("Creating post for user: " + username + ", content: " + content + ", stream: " + streamTitle);

        User user = dataStore.getUser(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        Stream stream = dataStore.getStream(streamTitle);
        if (stream == null) {
            throw new IllegalArgumentException("Stream not found: " + streamTitle);
        }

        Post post = new Post(content, user, stream);
        dataStore.addPost(post);

        logger.info("Post created successfully for user: " + username);
        return new PostDTO(content, username, streamTitle);
    }
}
