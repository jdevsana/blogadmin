package com.sana.blogadmin.service;

import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

@Service
public interface BlogService {

    Flux<User> getAllBlogUsers();
    Flux<Post> getAllBlogPosts();
    HashMap<User, List<Post>> getAllPostsByUsers();
}
