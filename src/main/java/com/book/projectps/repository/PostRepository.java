package com.book.projectps.repository;

import com.book.projectps.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findByArtistId(Long artistId);
    List<Post> findByArtType(String artType);
}

