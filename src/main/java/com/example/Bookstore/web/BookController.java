package com.example.Bookstore.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.annotation.Validated;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.CategoryRepository;

import jakarta.validation.Valid;

	// @Controller-annotaatio: Tämä annotaatio merkitsee luokan ohjaimeksi Spring MVC:lle, joka mahdollistaa HTTP-pyyntöjen käsittelyn.
	@Controller
	public class BookController {
		
	// @Autowired-annotaatio: Tällä annotaatiolla injektoidaan riippuvuudet BookstoreRepository- ja CategoryRepository-luokista.
	// Näitä repository-luokkia käytetään kirjojen ja kategorioiden tietokantatoimintoihin.
	@Autowired
	private BookstoreRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	

    @GetMapping("/login")
    public String login() {
        return "login"; // Palauta kirjautumissivun nimi (ilman .html-päätettä)
    }
	
	// @GetMapping("/booklist"): Tämä annotaatio määrittää, että kun käyttäjä tekee GET-pyynnön osoitteeseen "/booklist", tämä metodi suoritetaan.
	// Metodi hakee kaikki kirjat ja kategoriat tietokannasta ja näyttää ne näkymässä (template-tiedostossa) nimeltä "booklist".
	@GetMapping("/booklist")
	public String booklist(Model model) {
		
		// Get the authenticated user's username
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		model.addAttribute("name", username);
		
		model.addAttribute("books", brepository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "booklist";
	}
	// @GetMapping("/addbook"): Tämä annotaatio määrittää toiminnon, kun käyttäjä siirtyy sivulle "/addbook" (GET-pyyntö).
	// Metodi valmistelee lomakkeen uuden kirjan lisäämistä varten ja palauttaa "addbook"-näkymän.
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addbook")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
    	return "addbook";
	}
	// @PostMapping("/addbook"): Tämä annotaatio määrittää toiminnon, kun käyttäjä lähettää lomakkeen uuden kirjan lisäämistä varten (POST-pyyntö).
	// Metodi validoi syötetyt tiedot @Valid-annotaation avulla. Jos validointi onnistuu, uusi kirja tallennetaan tietokantaan brepository.save(newBook) -komennolla
	// ja käyttäjä ohjataan uudelleen "/booklist"-sivulle. Jos validointi epäonnistuu, käyttäjä pysyy "addbook"-sivulla ja näkee virheilmoitukset.
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/addbook")
	//public String addBook(@ModelAttribute Book newBook) { 
	public String addBook(@Valid Book newBook, BindingResult bindingResult, Model model) {
	       if (bindingResult.hasErrors()) {
	        	model.addAttribute("categories", crepository.findAll());
	        	return "addbook";
	       }
	        brepository.save(newBook);
	        return "redirect:/booklist";
	}
	// @GetMapping("/delete/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä siirtyy sivulle "/delete/{id}" (GET-pyyntö).
	// Metodi poistaa kirjan tietokannasta annetun id-parametrin perusteella ja ohjaa käyttäjän uudelleen "/booklist"-sivulle.
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		brepository.deleteById(id);
	     return "redirect:../booklist";
	 }
	// @GetMapping("/edit/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä siirtyy sivulle "/edit/{id}" (GET-pyyntö).
	// Metodi hakee kirjan tietokannasta annetun id-parametrin perusteella ja valmistelee kirjan muokkaamista varten.
	// Käyttäjä ohjataan "editbook"-sivulle, jossa hän voi muokata kirjan tietoja.
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/{id}")
	public String editBookForm(@PathVariable("id") Long id, Model model) {
	    Book book = brepository.findById(id).orElse(null);
	    if (book != null) {
	        model.addAttribute("book", book);
	        model.addAttribute("categories", crepository.findAll());
	        return "editbook";
	    } else {
	        
	        return "redirect:/booklist";
	    }
	}
	// @PostMapping("/edit/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä lähettää muokatun kirjan tiedot lomakkeen kautta (POST-pyyntö).
	// Metodi päivittää kirjan tiedot tietokantaan brepository.save(existingBook) -komennolla ja ohjaa käyttäjän uudelleen "/booklist"-sivulle.
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, @Valid @ModelAttribute Book editedBook, BindingResult bindingResult, Model model) {
	   Book existingBook = brepository.findById(id).orElse(null);

	        if (existingBook != null) {
	        	if (bindingResult.hasErrors()) {
	                model.addAttribute("categories", crepository.findAll());
	                return "editbook";
	            }
	        	
	            existingBook.setAuthor(editedBook.getAuthor());
	            existingBook.setTitle(editedBook.getTitle());
	            existingBook.setPublicationYear(editedBook.getPublicationYear());
	            existingBook.setIsbn(editedBook.getIsbn());
	            existingBook.setPrice(editedBook.getPrice());

	            
	            brepository.save(existingBook);

	            return "redirect:/booklist";
	        }

	        return "redirect:/error";
	}

}




