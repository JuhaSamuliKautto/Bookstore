package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;


	@Controller
	public class BookController {
	
	@Autowired
	private BookstoreRepository repository;
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@GetMapping("/addbook")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book());
    	return "addbook";
	}

	@PostMapping("/addbook")
	public String addBook(@ModelAttribute Book newBook) {
	    repository.save(newBook);
	    return "redirect:/booklist";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	     return "redirect:../booklist";
	 }
	@GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book editedBook) {
        // Find the existing book by ID
        Book existingBook = repository.findById(id).orElse(null);

        if (existingBook != null) {
            // Update the existing book with the edited book details
            existingBook.setAuthor(editedBook.getAuthor());
            existingBook.setTitle(editedBook.getTitle());
            existingBook.setPublicationYear(editedBook.getPublicationYear());
            existingBook.setIsbn(editedBook.getIsbn());
            existingBook.setPrice(editedBook.getPrice());

            // Save the updated book
            repository.save(existingBook);
        }

        return "redirect:/booklist";
    }

}

