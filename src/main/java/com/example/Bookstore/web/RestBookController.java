package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;


// Tämä Java-luokka, nimeltään RestBookController, toimii RESTful-palveluna ja tarjoaa HTTP-pohjaisen rajapinnan kirjojen hallintaan Book-entiteetin osalta.
//Se käyttää Spring Frameworkin @RestController-annotaatiota, mikä tarkoittaa, että tämä ohjain käsittelee HTTP-pyyntöjä ja palauttaa JSON-muotoisia vastauksia.
@RestController
	public class RestBookController {

// @Autowired-annotaatio: Tällä annotaatiolla injektoidaan BookstoreRepository-riippuvuus, jota käytetään kirjojen tietokantatoimintoihin.
@Autowired
	private BookstoreRepository brepository;
		
		// @GetMapping("/books"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/books".
		//Metodi hakee kaikki kirjat tietokannasta brepository.findAll() -kutsulla ja palauttaa ne JSON-muodossa.
		// Tämä mahdollistaa kaikkien kirjojen hakemisen REST-rajapinnan kautta.
		@GetMapping("/books")
		public List<Book> bookListRest() {	
	        return (List<Book>) brepository.findAll();
		}
		
		// @GetMapping("/book/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/book/{id}", 
		// missä {id} korvataan kirjan tunnisteella. Metodi hakee kirjan tietokannasta annetun bookId-parametrin perusteella brepository.findById(bookId) -kutsulla 
		// ja palauttaa kirjan tiedot JSON-muodossa. Tämä mahdollistaa yksittäisen kirjan hakemisen REST-rajapinnan kautta.
		@GetMapping("/book/{id}")
	    public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return brepository.findById(bookId);
		} 
		
		// @PostMapping("/book"): Tämä annotaatio määrittää toiminnon, kun käyttäjä lähettää POST-pyynnön osoitteeseen "/book" ja antaa 
		// JSON-muotoisen kirjatiedon @RequestBody-annotaation avulla. Metodi tallentaa kirjan tietokantaan käyttäen brepository.save(book) -kutsua 
		// ja palauttaa tallennetun kirjan tiedot JSON-muodossa. Tämä mahdollistaa uuden kirjan lisäämisen REST-rajapinnan kautta.
		@PostMapping("/book")
	    public Book save(@RequestBody Book book){
	        return brepository.save(book);
	        
		}	
}
