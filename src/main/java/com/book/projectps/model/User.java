package com.book.projectps.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String displayName;

    @Column()
    private String role = "CLIENT";

    @Column(nullable = false)
    private Boolean logged;
    @ManyToMany(mappedBy = "likes")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Post> likedPosts = new HashSet<>();


    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }
}

