package com.book.projectps;

import com.book.projectps.model.Artist;
import com.book.projectps.model.Post;
import com.book.projectps.model.User;
import com.book.projectps.repository.PostRepository;
import com.book.projectps.service.PostService;
import com.book.projectps.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService = new PostServiceImpl();

    private Post post1;
    private Post post2;
    private String artist;
    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .email("user@example.com")
                .displayName("User")
                .role("ARTIST")
                .build();

        artist = "String";

        post1 = Post.builder()
                .id(1L)
                .artist("Gigel")
                .title("Title1")
                .description("Description1")
                .createdAt(new Date())
                .artType("Painting")
                .build();

        post2 = Post.builder()
                .id(2L)
                .artist(artist)
                .title("Title2")
                .description("Description2")
                .createdAt(new Date())
                .artType("Sculpture")
                .build();
    }

    @Test
    public void testFindPostsByArtType() {
        when(postRepository.findByArtType("Painting")).thenReturn(Arrays.asList(post1));

        List<Post> foundPosts = postService.findByArtType("Painting");

        assertEquals(1, foundPosts.size());
        assertEquals(post1, foundPosts.get(0));
    }
}
