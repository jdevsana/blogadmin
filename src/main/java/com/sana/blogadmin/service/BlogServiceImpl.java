package com.sana.blogadmin.service;

import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogServiceImpl implements BlogService{
    private final WebClient webClient;

    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    @Autowired
    public BlogServiceImpl(WebClient.Builder builder){
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    /**
     * Retrive all the user details from the url: https://jsonplaceholder.typicode.com/users
     * @return list of user details
     */
    public List<User> getAllBlogUsers() {

        List<User> users = this.webClient.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();

        return users;
    }

    /**
     * Retrive all the blog posts from the url: https://jsonplaceholder.typicode.com/posts
     * @return list of posts
     */
    public List<Post> getAllBlogPosts(){
        List<Post> posts = this.webClient.get().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList()
                .block();
        return posts;
    }

    /**
     * Map all the blog post with the user
     * @return list of users with his posts
     */
    public HashMap<User, List<Post>> getAllPostsByUsers(){
        List<User> users = getAllBlogUsers();
        List<Post> posts = getAllBlogPosts();

        HashMap<User, List<Post>> userPostsMap = new HashMap<User, List<Post>>();
        users.forEach(user ->
                userPostsMap.put(user, posts.stream()
                        .filter(post->user.getId() == post.getUserId())
                        .collect(Collectors.toList())));
        return userPostsMap;
    }
}
