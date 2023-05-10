package com.book.projectps;

import com.book.projectps.model.Client;
import com.book.projectps.model.User;
import com.book.projectps.repository.ClientRepository;
import com.book.projectps.service.ClientService;
import com.book.projectps.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService = new ClientServiceImpl();

    private Client client;
    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("user@example.com")
                .displayName("User")
                .role("Client")
                .build();

        client = new Client(1L, "Arts", user);
    }

    @Test
    public void testFindClientById() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client foundClient = clientService.findById(1L);

        assertEquals(client, foundClient);
    }

}
