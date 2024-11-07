package org.example;

import org.example.model.Stream;
import org.example.model.User;
import org.example.service.DataStore;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class Main {
    @Inject
    DataStore dataStore;

    void onStart(@Observes StartupEvent ev) {
        // Add some initial data
        dataStore.addUser(new User("samuel", "samuel@example.com"));
        dataStore.addUser(new User("david", "david@example.com"));

        dataStore.addStream(new Stream("General"));
        dataStore.addStream(new Stream("Tech"));
        dataStore.addStream(new Stream("Random"));
    }
}