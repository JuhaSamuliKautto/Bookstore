package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;

@RestController
	public class RestBookController {

@Autowired
	private BookstoreRepository brepository;
		
		@GetMapping("/books")
		public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) brepository.findAll();
		}
		@GetMapping("/book{id}")
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return brepository.findById(bookId);
		} 
		@PostMapping("/save")
	    public String save(Book book){
	        brepository.save(book);
	        return "redirect:booklist";
		}	
}
