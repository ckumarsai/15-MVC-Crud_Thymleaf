package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> getAllBooks() {
//		return bookRepo.findAll();
		return bookRepo.findByActiveSW("Y");
	}

	@Override
	public boolean saveBook(Book book) {
		book.setActiveSW("Y");
		Book savedBook = bookRepo.save(book);

		if (savedBook.getBookId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteBook(Integer bookId) {
		// Hard delete
//		bookRepo.deleteById(bookId);
		
		Optional<Book> findById=bookRepo.findById(bookId);
		if(findById.isPresent()) {
			Book book=findById.get();
			book.setActiveSW("N");
			bookRepo.save(book);
		}
	}
	
	@Override
	public Book getBookById(Integer BookId) {
		Optional<Book> findById=bookRepo.findById(BookId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
}	
