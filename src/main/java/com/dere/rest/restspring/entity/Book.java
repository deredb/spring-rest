package com.dere.rest.restspring.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="book_id")
	private int id;
	

	@ManyToOne()
	@NotNull(message="Provide valid author")
	@JoinColumn(name="author_id")
	@JsonBackReference
	private Author author;
	
	@NotNull(message="Book title cannot be null")
	@Column(name="book_title")
	private String title;
	
	@NotNull(message="Publication year cannot be null")
	@Column(name="publication_year")
	private LocalDate pubYear;
	
	protected Book() {
		
	}

	public Book(int id, Author author, String title, LocalDate pubYear) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.pubYear = pubYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
		author.addBook(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPubYear() {
		return pubYear;
	}

	public void setPubYear(LocalDate pubYear) {
		this.pubYear = pubYear;
	}

	
}
