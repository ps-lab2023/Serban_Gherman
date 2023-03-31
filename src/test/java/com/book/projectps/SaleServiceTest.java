package com.book.projectps;

import com.book.projectps.model.Post;
import com.book.projectps.model.Sale;
import com.book.projectps.repository.SaleRepository;
import com.book.projectps.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindSaleById() {
        // Prepare data
        Post post = new Post(); // Fill in the Post object with necessary data
        Sale expectedSale = new Sale(100, true, post, 1L);

        // Mock the repository
        when(saleRepository.findById(1L)).thenReturn(Optional.of(expectedSale));

        // Call the service
        Sale actualSale = saleService.findById(1L);

        // Assert the results
        assertEquals(expectedSale, actualSale);
    }
}
