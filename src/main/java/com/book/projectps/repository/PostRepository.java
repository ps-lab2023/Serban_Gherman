package com.book.projectps.repository;

import com.book.projectps.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByArtist(String Artist);
    List<Post> findAll();
    List<Post> findByArtType(String artType);

    Post findByTitle(String title);
}

