package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();

		// sending empty obj for formm binding
		mav.addObject("book", new Book());
		mav.setViewName("index");
		return mav;
	}

	// adding books to the book table
	@PostMapping("/book")
	public ModelAndView save(Book book) {

		ModelAndView mav = new ModelAndView();
		boolean status = service.saveBook(book);
		if (status) {
			mav.addObject("sucMsg", "book saved");
		} else {
			mav.addObject("errorMsg", "failed to save");
		}
		mav.setViewName("index");
		return mav;

	}

	// retrieving all the books from book table
	@GetMapping("/books")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("BookView");
		return mav;
	}
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId")Integer bookId) {
		service.deleteBook(bookId);
		ModelAndView mav=new ModelAndView();
		List<Book> allBooks=service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("BookView");
		return mav;
		
	}
	
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId) {
		Book bookObj=service.getBookById(bookId);
		ModelAndView mav=new ModelAndView();
		mav.addObject("book", bookObj);
		mav.setViewName("index");
		return mav;
				}

}
