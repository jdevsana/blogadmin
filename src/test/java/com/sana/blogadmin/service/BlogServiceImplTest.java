package com.sana.blogadmin.service;

import com.sana.blogadmin.model.ErrorMsg;
import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest
class BlogServiceImplTest {
    private final String baseUrl = "https://jsonplaceholder.typicode.com";
    private WebClient.RequestHeadersSpec reqHeaderMock;
    private WebClient.RequestHeadersUriSpec reqHeadersUriMock;
    private WebClient.RequestBodyUriSpec reqBodyUriSpecMock;
    private WebClient.RequestBodySpec reqBodySpecMock;
    private WebClient.ResponseSpec resMock;
    private WebClient clientMock;

   BlogServiceImpl blogService;
/*
    @Autowired
    BlogService blogService;
    @Mock
    WebClient client;
    private MockRestServiceServer mockServer;
    User user;
    Post post;
*/

    @BeforeEach
    void setUp() {
        blogService = new BlogServiceImpl(WebClient.create().mutate());
        reqHeaderMock = mock(WebClient.RequestHeadersSpec.class);
        reqHeadersUriMock = mock(WebClient.RequestHeadersUriSpec.class);
        reqBodySpecMock = mock(WebClient.RequestBodySpec.class);
        resMock = mock(WebClient.ResponseSpec.class);
        clientMock = mock(WebClient.class);

    }


    @Test
    @DisplayName("Get user test")
    void getAllBlogUsers() {
        User userMock= new User();
        userMock.setId(1);
        userMock.setName("Leanne Graham");
        userMock.setUsername("Bret");
        userMock.setEmail("Sincere@april.biz");
        userMock.getAddress().setStreet("Kulas Light");
        userMock.getAddress().setSuite("Apt. 556");
        userMock.getAddress().setCity("Gwenborough");
        userMock.getAddress().setZipcode("92998-3874");
        userMock.getGeo().setLat(-37.3159);
        userMock.getGeo().setLng(81.1496);
        userMock.setPhone("1-770-736-8031 x56442");
        userMock.setWebsite("hildegard.org");
        userMock.getCompany().setName("Romaguera-Crona");
        userMock.getCompany().setCatchPhrase("Multi-layered client-server neural-net");
        userMock.getCompany().setBs("harness real-time e-markets");

        when(clientMock.get()).thenReturn(reqHeadersUriMock);
        when(reqHeadersUriMock.uri("/users")).thenReturn(reqHeaderMock);
        when(reqHeaderMock.retrieve()).thenReturn(resMock);
        when(resMock.bodyToFlux(User.class)).thenReturn(Flux.just(userMock));

        Flux<User> userList = blogService.getAllBlogUsers();


        StepVerifier.create(userList).expectNextCount(10).verifyComplete();


    }

    @Test
    void getAllPosts() {
        Post postMock = new Post();
        postMock.setUserId(1);
        postMock.setId(1);
        postMock.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        postMock.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit " +
                "molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        when(clientMock.get()).thenReturn(reqHeadersUriMock);
        when(reqHeadersUriMock.uri("/posts")).thenReturn(reqHeaderMock);
        when(reqHeaderMock.retrieve()).thenReturn(resMock);
        when(resMock.bodyToFlux(Post.class)).thenReturn(Flux.just(postMock));

        Flux<Post> postList = blogService.getAllBlogPosts();

        StepVerifier.create(postList).expectNextCount(100).verifyComplete();


    }
    @AfterEach
    void tearDown() {
    }

}