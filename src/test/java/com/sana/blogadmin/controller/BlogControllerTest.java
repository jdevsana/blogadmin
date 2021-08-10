package com.sana.blogadmin.controller;

import com.sana.blogadmin.model.Post;
import com.sana.blogadmin.model.User;
import com.sana.blogadmin.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @MockBean
    private BlogService blogService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("User Test")
    void getBlogUsers() throws Exception {
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

        Flux<User> userFlux = Flux.just(userMock);

        when(blogService.getAllBlogUsers()).thenReturn(userFlux);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8085/api/v1/blog/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(userMock.getName()));
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(userMock.getEmail()));
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(userMock.getWebsite()));
    }

    @Test
    @DisplayName("Post Test")
    void getBlogPosts() throws Exception {
        Post postMock = new Post();
        postMock.setUserId(1);
        postMock.setId(1);
        postMock.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        postMock.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit " +
                "molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        Flux<Post> postFlux = Flux.just(postMock);

        when(blogService.getAllBlogPosts()).thenReturn(postFlux);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("http://localhost:8085/api/v1/blog/posts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(postMock.getTitle()));
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(String.valueOf(postMock.getId())));

    }

}