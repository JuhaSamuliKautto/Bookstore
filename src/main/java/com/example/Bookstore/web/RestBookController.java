package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookstoreRepository;

@RestController
	public class RestBookController {

@Autowired
	private BookstoreRepository brepository;
		
		@GetMapping("/books")
		public List<Book> bookListRest() {	
	        return (List<Book>) brepository.findAll();
		}
		@GetMapping("/book/{id}")
	    public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return brepository.findById(bookId);
		} 
		@PostMapping("/book")
	    public Book save(@RequestBody Book book){
	        return brepository.save(book);
	        
		}	
}
