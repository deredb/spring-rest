package com.dere.rest.restspring.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dere.rest.restspring.entity.Author;
import com.dere.rest.restspring.entity.Book;
import com.dere.rest.restspring.repo.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Book findById(Integer id){
		return this.bookRepository.findById(id).orElseThrow(() -> {
			return new NoSuchElementException("Book does not exist " + id );
		});
		
	}
	
	public Book createBook(Book theBook) {
		if(theBook == null) {
			 throw new RuntimeException("Must provide book");
		}
		
		return this.bookRepository.save(theBook);
	}
	
	public Iterable<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}
	
	public void updateWithPatch(Integer id, Book theBook) {
		Book dbBook = this.findById(id);
		dbBook.setTitle(theBook.getTitle()!=null? theBook.getTitle() : dbBook.getTitle());
		dbBook.setPubYear(theBook.getPubYear()!=null ? theBook.getPubYear() : dbBook.getPubYear());
		this.bookRepository.save(dbBook);
	}
	
	public void updateWithPut(Integer id, Book theBook) {
		Book dbBook = this.findById(id);
		dbBook.setTitle(theBook.getTitle());
		dbBook.setPubYear(theBook.getPubYear());
		this.bookRepository.save(dbBook);
	}
	public void deleteBookById(Integer id) {
		Book book = findById(id);
		this.bookRepository.deleteById(id);
	}
}
