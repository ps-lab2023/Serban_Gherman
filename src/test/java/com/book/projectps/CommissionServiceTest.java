package com.book.projectps;

import com.book.projectps.model.Client;
import com.book.projectps.model.Commission;
import com.book.projectps.model.Post;
import com.book.projectps.repository.CommissionRepository;
import com.book.projectps.service.impl.CommissionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommissionServiceTest {

    @InjectMocks
    private CommissionServiceImpl commissionService;

    @Mock
    private CommissionRepository commissionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCommissionById() {
        // Prepare data
        Client client = new Client(); // Fill in the Client object with necessary data
        Post post = new Post(); // Fill in the Post object with necessary data
        Commission expectedCommission = new Commission(client, 100, "available", post, 1L);

        // Mock the repository
        when(commissionRepository.findById(1L)).thenReturn(Optional.of(expectedCommission));

        // Call the service
        Commission actualCommission = commissionService.findById(1L);

        // Assert the results
        assertEquals(expectedCommission, actualCommission);
    }
}
