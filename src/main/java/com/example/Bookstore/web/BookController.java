package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;
import com.example.Bookstore.domain.CategoryRepository;


	@Controller
	public class BookController {
	
	@Autowired
	private BookstoreRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", brepository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "booklist";
	}
	
	@GetMapping("/addbook")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
    	return "addbook";
	}

	@PostMapping("/addbook")
	public String addBook(@ModelAttribute Book newBook) {
	    brepository.save(newBook);
	    return "redirect:/booklist";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		brepository.deleteById(id);
	     return "redirect:../booklist";
	 }
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
	@PostMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, @ModelAttribute Book editedBook) {
	   
	    Book existingBook = brepository.findById(id).orElse(null);

	    try {
	        if (existingBook != null) {
	         
	            existingBook.setAuthor(editedBook.getAuthor());
	            existingBook.setTitle(editedBook.getTitle());
	            existingBook.setPublicationYear(editedBook.getPublicationYear());
	            existingBook.setIsbn(editedBook.getIsbn());
	            existingBook.setPrice(editedBook.getPrice());

	            
	            brepository.save(existingBook);

	            return "redirect:/booklist";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "redirect:/error";
	}
	}



