package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;

public interface BookService {
	public List<Book> getAllBooks();
	public boolean saveBook(Book book);
	public void deleteBook(Integer bookId);
	public Book getBookById(Integer BookId);

}
