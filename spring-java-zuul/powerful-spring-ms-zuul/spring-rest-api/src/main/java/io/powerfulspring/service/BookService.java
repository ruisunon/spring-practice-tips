package io.powerfulspring.service;

import io.powerfulspring.domain.Book;
import java.util.Collection;

public interface BookService {
	Collection<Book> findAll();
	
	Book findById(Long id);
	
	Book save(Book book);
	
	Book update(Book book);
	
	Book deleteById(Long id);
}
