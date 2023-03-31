package com.book.projectps.controller;


import com.book.projectps.model.Artist;
import com.book.projectps.service.impl.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistServiceImpl artistService;

    // ... other methods

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable Long id) {
        Artist artist = artistService.findById(id);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Artist> save(@RequestBody Artist artist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.save(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Long id, @RequestBody Artist artist) {
        if (!id.equals(artist.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(artistService.save(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        artistService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}