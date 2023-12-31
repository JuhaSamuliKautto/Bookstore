package com.example.Bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String cname;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
	
	public Category() {}
	
	

	public Category(String cname) {
		super();
		this.cname = cname;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCname() {
		return cname;
	}



	public void setName(String cname) {
		this.cname = cname;
	}



	public List<Book> getBooks() {
		return books;
	}



	public void setBooks(List<Book> books) {
		this.books = books;
	}



	@Override
	public String toString() {
		return "Category [id=" + id + ", cname=" + cname + "]";
	}	
	
}


	


