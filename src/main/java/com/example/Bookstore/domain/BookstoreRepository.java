package com.example.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource-annotaatio: Tämä annotaatio on Spring Data RESTin käyttämä annotaatio, joka osoittaa, että tätä rajapintaa voidaan käyttää REST-palveluna.
// Se mahdollistaa entiteettien hallinnan HTTP-pyyntöjen avulla.
@RepositoryRestResource

// CrudRepository<Book, Long>: Tämä rajapinta laajentaa CrudRepository-rajapintaa, ja se on geneerinen.
// Se määrittää Book-luokan entiteetin tyypiksi ja Long-tyypiksi pääavaimen (id) tyyppi.
public interface BookstoreRepository extends CrudRepository<Book, Long> {

	// List<Book> findByTitle(@Param("title") String Title): Tämä on mukautettu metodi, joka on määritelty rajapinnassa.
	// Metodi mahdollistaa kirjojen hakemisen tietokannasta kirjan otsikon perusteella.
	// Metodi käyttää @Param-annotaatiota liittääkseen parametrin title tietokantakyselyyn.	
	List<Book> findByTitle(@Param("title") String Title);
	List<Book> findByAuthor(@Param("author") String Author);
 }



