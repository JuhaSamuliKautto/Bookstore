package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
public class BookRepositoryTest {
    
    @Autowired
    private BookstoreRepository brepository;
    
    @Autowired
    private CategoryRepository crepository;
    
    
    @Test    
    public void testCreateBook() {    
        Category category = new Category("Sarjakuvat");
        crepository.save(category);
        Book book = new Book("Nummisuutarit", "Aleksis Kivi", 1975, "164554564", 12.0, category);
        brepository.save(book);

        // Hae tallennettu kirja ja tarkista, että se on tallennettu oikein.
        List<Book> savedBooks = brepository.findByTitle("Nummisuutarit");
        assertThat(savedBooks).isNotEmpty();
        assertThat(savedBooks.get(0).getTitle()).isEqualTo("Nummisuutarit");
    }
     
    @Test
    public void testDeleteBook() {
        Category category = new Category("Sarjakuvat");
        crepository.save(category);

        Book book = new Book("Nummisuutarit", "Aleksis Kivi", 1975, "164554564", 12.0, category);
        brepository.save(book);

        // Poista tallennettu kirja ja tarkista, että se on poistettu onnistuneesti.
        brepository.delete(book);

        List<Book> deletedBooks = brepository.findByTitle("Nummisuutarit");
        assertThat(deletedBooks).isEmpty();
    }
    
    @Test
    public void testSearchBooksByAuthor() {
        // Luo kirjoja ja tallenna ne tietokantaan BookRepositoryn avulla
        Category category = new Category("Tieteiskirjat");
        crepository.save(category);
        Book book1 = new Book("Kirjailija 1", "Kirja 1", 2023, "ISBN123", 25.0, category);
        Book book2 = new Book("Kirjailija 2", "Kirja 2", 2023, "ISBN456", 30.0, category);
        brepository.save(book1);
        brepository.save(book2);

        // Hae kirjat kirjailijan nimen perusteella
        List<Book> savedBooks = brepository.findByAuthor("Kirjailija 1");

        // Tarkista, että haetut kirjat vastaavat odotuksia
        assertEquals(1, savedBooks.size()); // Odotetaan yhden kirjan vastaavan hakukriteeriä
        assertEquals("Kirjailija 1", savedBooks.get(0).getAuthor()); // Tarkista, että kirjailija on oikea
    }

}
