package com.sana.blogadmin.service;

import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface BlogService {

    List<User> getAllBlogUsers();
    List<Post> getAllBlogPosts();
    HashMap<User, List<Post>> getAllPostsByUsers();
}
