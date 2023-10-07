package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.AppUser;
import com.example.Bookstore.domain.AppUserRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Määrittelee pääsovelluksen BookstoreApplication Spring Bootin @SpringBootApplication -annotaation avulla.
// Tämä annotaatio merkitsee pääsovelluksen ja auttaa Springiä hallitsemaan sovelluksen konfiguraatiota
@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    // Käynnistää Spring Boot sovellukset
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    // Toteuttaa bookDemo-metodin
    public CommandLineRunner bookDemo(BookstoreRepository brepository, CategoryRepository crepository, AppUserRepository urepository) {
        return (args) -> {

          
            log.info("save a couple of books");
        
            // Luo 2 kategoriaa
            crepository.save(new Category("Romaanit"));
            crepository.save(new Category("Novellit"));

            // Luo 2 kirjaa ja tallentaa ne 
            brepository.save(new Book("Aleksis Kivi", "Seitsemän veljestä", 1870, "123456-8", 20, crepository.findByName("Romaanit").get(0)));
            brepository.save(new Book("Frans Emil Sillanpää", "Ihmiselon ihanuus ja kurjuus", 1945, "12345-12", 15, crepository.findByName("Romaanit").get(0)));
            
            // Create users: admin/admin user/user
            log.info("Create users: admin/admin user/user");
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
            urepository.save(user1);
            urepository.save(user2);
     
        	log.info("fetch all books");
        	// Näyttää kaikki tallennetut kirjat käyttämällä brepository.findAll() -metodia
        	for (Book book : brepository.findAll()) {
            // tulostaa kirjat lokitiedostoon (log.info(book.toString())).
        		log.info(book.toString());
        }
        
    };
        
    } 
}



