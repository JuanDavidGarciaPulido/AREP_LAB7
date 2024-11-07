package org.example.resource;

import org.example.model.dto.CreatePostRequest;
import org.example.model.dto.PostDTO;
import org.example.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostResourceTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostResource postResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPostsSuccess() {
        when(postService.getAllPosts()).thenReturn(List.of(
                new PostDTO("content", "user", "stream")));

        Response response = postResource.getAllPosts();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCreatePostBadRequest() {
        CreatePostRequest request = new CreatePostRequest();
        request.username = "samuel";
        request.content = "";
        request.streamTitle = "General";

        Response response = postResource.createPost(request);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Content cannot be empty", response.getEntity());
    }
}

