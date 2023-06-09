package com.book.projectps.service;


import com.book.projectps.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> findAll();
    Artist findById(Long id);
    Artist save(Artist artist);
    void deleteById(Long id);
}
