package com.dere.rest.restspring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dere.rest.restspring.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

}
