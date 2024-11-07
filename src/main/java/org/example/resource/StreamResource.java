package org.example.resource;

import org.example.model.Stream;
import org.example.model.dto.StreamDTO;
import org.example.service.DataStore;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/streams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreamResource {
    @Inject
    DataStore dataStore;

    @GET
    public List<StreamDTO> getAllStreams() {
        return dataStore.getAllStreams().stream()
                .map(stream -> new StreamDTO(stream.getTitle()))
                .collect(Collectors.toList());
    }

    @POST
    public void createStream(StreamDTO streamDTO) {
        Stream stream = new Stream(streamDTO.title);
        dataStore.addStream(stream);
    }
}