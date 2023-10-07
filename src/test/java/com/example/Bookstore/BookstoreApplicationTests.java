package com.example.Bookstore;


import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.Bookstore.web.BookController;

@SpringBootTest
@AutoConfigureWebClient
class BookstoreApplicationTests {

    @Autowired
    private BookController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testGetAllBooks() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8800/api/books", String.class);

        int statusCode = response.getStatusCode().value();
        assertEquals(200, statusCode);

        assertEquals(200, response.getStatusCodeValue());
    }
}

