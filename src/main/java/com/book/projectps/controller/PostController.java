package com.book.projectps.controller;

import com.book.projectps.exceptions.UserNotFoundException;
import com.book.projectps.model.ApiResponse;
import com.book.projectps.model.Post;
import com.book.projectps.model.PostRequest;
import com.book.projectps.model.User;
import com.book.projectps.service.impl.PostServiceImpl;
import com.book.projectps.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    // ... other methods

  /*  @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }
    */

    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAll();
       // System.out.println(posts.toString());
        return !posts.isEmpty() ? ResponseEntity.ok(posts) : ResponseEntity.noContent().build();

    }
    @GetMapping("/likedPosts/{id}")
    public ResponseEntity<List<Post>> getLikedPostsByUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user != null){
            Set<Post> likedPostsSet = user.getLikedPosts();
            List<Post> likedPostsList = new ArrayList<>(likedPostsSet);
            return !likedPostsList.isEmpty() ? ResponseEntity.ok(likedPostsList) : ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createpost")
    public ResponseEntity<Post> save(@RequestBody PostRequest postRequest) {
        Post post = new Post();
        // Set the properties
        post.setArtist(postRequest.getArtist());
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setArtType(postRequest.getArtType());
        post.setType(postRequest.getType()); // Convert string to enum

        // Here, handle the creation time, this could also be handled in your service layer
        post.setCreatedAt(new Date());

        // Save the post
        Post savedPost = postService.save(post);

        // Check if the post was saved successfully
        if (savedPost != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } else {
            // Handle the error appropriately, this is just a basic response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/likePost/{postid}/{userid}")
    public ResponseEntity<?> likePost(@PathVariable Long postid, @PathVariable Long userid) {
        Post post = postService.findById(postid);
        User user = userService.findById(userid);
        System.out.println(postid);
        System.out.println(userid);
        if (user.getLikedPosts().contains(post)) {
            user.getLikedPosts().remove(post);
            post.getLikes().remove(user);
            userService.save1(user);
            postService.save(post);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Post removed from likes.");
            return ResponseEntity.ok().body(apiResponse);
        } else {
            user.getLikedPosts().add(post);
            post.getLikes().add(user);
            userService.save1(user);
            postService.save(post);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Post liked successfully");
            return ResponseEntity.ok().body(apiResponse);

        }



    }
    @GetMapping("/exportUserPosts/{userId}")
    public ResponseEntity<String> exportUserPosts(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Post> userPosts = postService.findByArtist(user.getUsername());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Post.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            for (Post post : userPosts) {
                marshaller.marshal(post, sw);
            }

            String xmlString = sw.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=userPosts.xml");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xmlString);

        } catch (JAXBException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        if (!id.equals(post.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postService.save(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
