package com.dere.rest.restspring.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dere.rest.restspring.entity.Book;
import com.dere.rest.restspring.service.BookService;

@RestController
@RequestMapping(value="/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping()
	public void addBook(@Valid @RequestBody Book book) {
		this.bookService.createBook(book);
	}
	@GetMapping("/{id}")
	public Book getAllBooks(@PathVariable Integer id) {
		return this.bookService.findById(id);
	}
	
	@GetMapping()
	public Iterable<Book> getAllBooks() {
		return this.bookService.getAllBooks();
	}
	
	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable Integer id) {
		this.bookService.deleteBookById(id);
	}
}
