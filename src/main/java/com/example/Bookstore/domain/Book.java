package com.example.Bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// @Entity-annotaatio: Tämä annotaatio merkitsee luokan JPA-entiteetiksi, 
// mikä tarkoittaa, että tätä luokkaa voidaan käyttää tietokannassa tallennettavien tietojen edustamiseen.
@Entity
public class Book {
	//@NotNull
	
	// @Id-annotaatio: Tämä annotaatio merkitsee id-kenttää tietokannan pääavaimena, mikä tarkoittaa, että jokainen kirja saa yksilöllisen tunnisteen.
	@Id
	
	// @GeneratedValue-annotaatio: Tämä annotaatio määrittelee, että id-kenttä generoidaan automaattisesti tietokannassa.
	// GenerationType.AUTO tarkoittaa, että tietokantajärjestelmä valitsee automaattisesti, miten tunnisteet generoidaan.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	
    private Long id;
	
	@NotBlank(message = "Author is required")
	@Size(min=2,max=30, message = "Author must be longer or equal than 2")
	private String author;
	
	@NotBlank(message = "Title is required")
	@Size(min=2,max=50, message = "Title must be longer or equal than 2 and shorter or equal than 50")
	private String title;
	
	@Min(value = 1500, message = "Year must be greater than or equal to 1500")
    private int publicationYear;
    
	@Size(min=5,max=30, message = "ISBN must be longer or equal than 5")
    private String isbn;
    

	@Min(value = 1, message = "Price must be greater than or equal to 1")
    private double price;
    
    // @ManyToOne-annotaatio: Tämä annotaatio merkitsee, että Book-luokka on monen suhde Category-luokkaan. Yksi kirja voi kuulua yhteen kategoriaan.
    @ManyToOne
    
    // @JoinColumn-annotaatio: Tämä annotaatio määrittelee liittämiskentän (category) tietokantataulussa.
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
    

	