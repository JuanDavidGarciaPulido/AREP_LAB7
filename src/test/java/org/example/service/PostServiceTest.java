package org.example.service;

import org.example.model.User;
import org.example.model.Stream;
import org.example.model.dto.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

    private DataStore dataStore;
    private PostService postService;

    @BeforeEach
    public void setup() {
        dataStore = new DataStore();
        postService = new PostService();
        postService.dataStore = dataStore;

        dataStore.addUser(new User("samuel", "samuel@example.com"));
        dataStore.addStream(new Stream("General"));
    }

    @Test
    public void testCreatePostSuccess() {
        PostDTO postDTO = postService.createPost("samuel", "Hello World", "General");
        assertNotNull(postDTO);
        assertEquals("samuel", postDTO.username);
        assertEquals("Hello World", postDTO.content);
        assertEquals("General", postDTO.streamTitle);
    }

    @Test
    public void testCreatePostUserNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                postService.createPost("nonexistentUser", "Hello World", "General"));
        assertEquals("User not found: nonexistentUser", exception.getMessage());
    }

    @Test
    public void testCreatePostStreamNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                postService.createPost("samuel", "Hello World", "NonexistentStream"));
        assertEquals("Stream not found: NonexistentStream", exception.getMessage());
    }
}
