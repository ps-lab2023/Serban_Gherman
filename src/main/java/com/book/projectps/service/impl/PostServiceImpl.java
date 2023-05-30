package com.book.projectps.service.impl;


import com.book.projectps.model.Post;
import com.book.projectps.repository.PostRepository;
import com.book.projectps.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public Post findByTitle(String title) {return postRepository.findByTitle(title);}

    public PostRepository getPostRepository() {
        return postRepository;
    }

    @Override
    public List<Post> findByArtType(String type) {
        return postRepository.findByArtType(type);
    }

    @Override
    public List<Post> findByArtist(String username) {
        return postRepository.findByArtist(username);
    }
}
