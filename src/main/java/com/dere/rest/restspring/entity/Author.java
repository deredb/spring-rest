package com.dere.rest.restspring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="authors")
public class Author {

	@Id
	@Column(name="author_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull(message="Provide valid first name")
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="Provide valid first name")
	private String lastName;
	
	@JsonManagedReference
	@OneToMany(mappedBy  = "author", 
			cascade=CascadeType.ALL, 
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	private List<Book> books = new ArrayList<Book>();
	
	public Author() {
		
	}

	public Author(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
		book.setAuthor(null);
	}
	
	
}
