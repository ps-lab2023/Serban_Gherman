package com.book.projectps.service;


import com.book.projectps.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(Long id);
    Post save(Post post);
    void deleteById(Long id);
    List<Post> findByArtType(String type);
}
