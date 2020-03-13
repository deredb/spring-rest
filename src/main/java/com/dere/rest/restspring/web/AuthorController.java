package com.dere.rest.restspring.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dere.rest.restspring.entity.Author;
import com.dere.rest.restspring.entity.Book;
import com.dere.rest.restspring.service.AuthorService;
@Validated
@RestController
@RequestMapping(value="/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping
	public Author createAuthor(@Valid @RequestBody Author theAuthor, BindingResult bindingResults) {

		return this.authorService.createAuthor(theAuthor);
	}
	
	@GetMapping("/{id}")
	public Author getAuthor(@PathVariable(value="id") Integer id) {
		return this.authorService.findById(id);
	}
	
	@PatchMapping("/{id}")
	public void updatePartial(@PathVariable(value="id") Integer id, @RequestBody Author theAuthor) {
		this.authorService.updateWithPatch(id, theAuthor);
	}
	
	@GetMapping()
	public Iterable<Author> getAllAuthors() {
		return this.authorService.getAllAuthors();
	}
	
	@DeleteMapping("/{id}")
	public void removeAuthor(@PathVariable(name="id") Integer id) {
		this.authorService.deleteAuthorById(id);
	}
	
	@PostMapping("/{id}/books")
	public void createBookByAuthorId(@PathVariable(name="id") Integer id, 
					@RequestBody Book theBook) {
		this.authorService.createBookByAuthorId(id, theBook);
	}
	
	@GetMapping("{id}/books")
	public List<Book> getBookByAuthorId(@PathVariable(name="id") Integer id) {
		return this.authorService.getBooksByAuthorId(id);
	}
}
