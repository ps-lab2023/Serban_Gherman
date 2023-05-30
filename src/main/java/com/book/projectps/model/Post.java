package com.book.projectps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column(nullable = false)
    @XmlElement
    private String artist;

    @Column(nullable = false)
    @XmlElement
    private String title;

    @Column(nullable = false)
    @XmlElement
    private String description;

    @Column(nullable = false)
    @XmlElement
    private Date createdAt;


    @Column(nullable = false)
    @XmlElement
    private String artType;
    @Column(nullable = false)
    @XmlElement
    private String type;
    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> likes = new HashSet<>();
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}
