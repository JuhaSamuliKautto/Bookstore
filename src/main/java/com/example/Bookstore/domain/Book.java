package com.example.Bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String author;
	private String title;
    private int publicationYear;
    private String isbn;
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "categoryid")
    public Category category;

    public Book() {}
    
	

	public Book(String author, String title, int publicationYear, String isbn, double price,
			Category category) {
		super();
		this.author = author;
		this.title = title;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Book [id=" + id + ", author=" + author + ", title=" + title + ", publicationYear=" + publicationYear
					+ ", isbn=" + isbn + ", price=" + price + ", category=" + this.getCategory() + "]";
		else
			return "Book [id=" + id + ", author=" + author + ", title=" + title + ", publicationYear=" + publicationYear
					+ ", isbn=" + isbn + ", price=" + price + "]";
	}
}
    

	