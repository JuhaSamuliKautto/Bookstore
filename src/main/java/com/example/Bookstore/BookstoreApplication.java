package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			
			log.info("save a couple of books");
	        crepository.save(new Category("Romaanit"));
	        crepository.save(new Category("Novellit"));
			
			brepository.save(new Book("Jesse James", "Aikamoista!", 1975, "123456-8", 20, crepository.findByName("Romaanit").get(0)));
			brepository.save(new Book("Meikä Manne", "Olut maistuisi nyt", 2020, "12345-12", 15, crepository.findByName("Novellit").get(0)));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
			
	};
	}
}
