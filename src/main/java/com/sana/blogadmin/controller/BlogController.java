package com.sana.blogadmin.controller;

import com.sana.blogadmin.exception.PostByUserProcessException;
import com.sana.blogadmin.exception.PostNotFoundException;
import com.sana.blogadmin.exception.UserNotFoundException;
import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import com.sana.blogadmin.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Get all the blog users with user details
     * @return list of users
     * @throws UserNotFoundException
     */
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getBlogUsers() throws UserNotFoundException {
        List<User> users = blogService.getAllBlogUsers().collectList().block();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /**
     * Get all the blog post without any criteria
     * @return list of posts
     * @throws PostNotFoundException
     */
    @RequestMapping(path = "/posts")
    public ResponseEntity<List<Post>> getBlogPosts() throws PostNotFoundException {
        List<Post> posts = blogService.getAllBlogPosts().collectList().block();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    /**
     * Get all the post and map them to the user
     * @return list of post with user
     * @throws PostByUserProcessException
     */
    @RequestMapping(value = "/postbyuser")
    public ResponseEntity<HashMap<User, List<Post>>> getPostByUser() throws PostByUserProcessException {
        HashMap<User, List<Post>> postbyuser = blogService.getAllPostsByUsers();
        return ResponseEntity.status(HttpStatus.OK).body(postbyuser);
    }

}
