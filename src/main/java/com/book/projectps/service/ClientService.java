package com.book.projectps.service;


import com.book.projectps.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client save(Client artist);
    void deleteById(Long id);
}