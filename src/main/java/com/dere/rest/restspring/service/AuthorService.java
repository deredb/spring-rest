package com.dere.rest.restspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dere.rest.restspring.entity.Author;
import com.dere.rest.restspring.entity.Book;
import com.dere.rest.restspring.exceptions.RecordNotFoundException;
import com.dere.rest.restspring.repo.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private BookService bookService;
	
	public AuthorService(AuthorRepository authorRepository, 
				BookService bookService, EntityManager entityManager) {
		this.authorRepository = authorRepository;
		this.bookService = bookService;
		this.entityManager = entityManager;
	}
	

	public Author findById(Integer id) {
		
		return this.authorRepository.findById(id).orElseThrow(() ->{
			return new RecordNotFoundException("User is not found");
		});
	}

	
	public Author createAuthor(Author theAuthor) {
	
			return this.authorRepository.save(theAuthor);
	}
	
	public Iterable<Author> getAllAuthors() {
		return this.authorRepository.findAll();
	}
	
	
	public void updateWithPatch(Integer id, Author theAuthor) {
		Author dbAuthor = this.findById(id);
		dbAuthor.setFirstName(theAuthor.getFirstName()!=null? theAuthor.getFirstName() : dbAuthor.getFirstName());
		dbAuthor.setLastName(theAuthor.getLastName()!=null? theAuthor.getLastName() : dbAuthor.getLastName());
		
		this.authorRepository.save(dbAuthor);
	}
	
	public void updateWithPut(Integer id, Author theAuthor) {
		Author dbAuthor = this.findById(id);
		dbAuthor.setFirstName(theAuthor.getFirstName());
		dbAuthor.setLastName(theAuthor.getLastName());
		this.authorRepository.save(dbAuthor);
	}
	
	public void deleteAuthorById(Integer id) {
		Author dbAuthor = this.findById(id);
		this.authorRepository.delete(dbAuthor);
	}
	
	public void createBookByAuthorId(Integer id, Book theBook) {
		Author dbAuthor = this.findById(id);
		theBook.setAuthor(dbAuthor);
		this.bookService.createBook(theBook);
		
	}
	
	public List<Book> getBooksByAuthorId(Integer id) {
		List<Book> booksByAuthorId = new ArrayList<Book>();
		this.bookService.getAllBooks().forEach(book -> {
			if(book.getAuthor().getId().equals(id)) {
				booksByAuthorId.add(book);
			}
			
		});
		return booksByAuthorId;
	}
}
