package com.book.projectps;

import com.book.projectps.model.Artist;
import com.book.projectps.model.User;
import com.book.projectps.repository.ArtistRepository;
import com.book.projectps.service.impl.ArtistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ArtistServiceTest {

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindArtistById() {
        // Prepare data
        User user = new User(1L, "username", "password", "user@example.com", "displayName", User.Role.ARTIST);
        Artist expectedArtist = new Artist(1L, user,"biography");

        // Mock the repository
        when(artistRepository.findById(1L)).thenReturn(Optional.of(expectedArtist));

        // Call the service
        Artist actualArtist = artistService.findById(1L);

        // Assert the results
        assertEquals(expectedArtist, actualArtist);
    }
}
