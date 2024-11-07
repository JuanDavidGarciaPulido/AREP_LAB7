package org.example.resource;

import org.example.model.dto.CreatePostRequest;
import org.example.model.dto.PostDTO;
import org.example.service.PostService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/api/posts")  // Actualizado para seguir el estándar /api/*
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    PostService postService;

    private static final Logger logger = Logger.getLogger(PostResource.class.getName());

    @GET
    public Response getAllPosts() {
        try {
            List<PostDTO> posts = postService.getAllPosts();
            return Response.ok(posts).build();
        } catch (Exception e) {
            logger.severe("Error getting posts: " + e.getMessage());
            return Response.serverError().entity("Error retrieving posts").build();
        }
    }

    @POST
    public Response createPost(CreatePostRequest request) {
        logger.info("Request received to create post with username: " + request.username +
                ", content: " + request.content +
                ", streamTitle: " + request.streamTitle);

        try {
            if (request.content == null || request.content.isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Content cannot be empty")
                        .build();
            }

            if (request.content.length() > 140) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Content cannot exceed 140 characters")
                        .build();
            }

            PostDTO post = postService.createPost(
                    request.username,
                    request.content,
                    request.streamTitle
            );

            return Response.status(Response.Status.CREATED)
                    .entity(post)
                    .build();
        } catch (IllegalArgumentException e) {
            logger.severe("Error creating post: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            logger.severe("Unexpected error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error occurred")
                    .build();
        }
    }
}