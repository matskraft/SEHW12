package de.uni.koeln.se.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.service.BookService;

@RequestMapping("/bookStore")
@RestController
public class BookController {

	@Autowired
	BookService bookSer;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllbooks(){
		
		List<Book>books= new ArrayList<Book>();
		books=bookSer.findBooks();
		
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/{id}" )
	public ResponseEntity<Book> getBookById(@PathVariable int id){
		return new ResponseEntity<>(bookSer.fetchBook(id).get(),HttpStatus.OK);
	}
	
	@GetMapping("/oldest")
	public ResponseEntity<List<Book>> getOldestBook(){
		List<Book> oldest = bookSer.findOldestBook();
		return new ResponseEntity<>(oldest,HttpStatus.OK);
	}
	
	@GetMapping("/latest")
	public ResponseEntity<List<Book>> getLatestBook(){
		List<Book> latest = bookSer.findLatestBook();
		return new ResponseEntity<>(latest,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		bookSer.addBook(book);
		return new ResponseEntity<>(book,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}" )
	public ResponseEntity<Book> removeBookById(@PathVariable int id){
		Book book=bookSer.fetchBook(id).get();
		if(bookSer.deleteBook(id)) {
			return new ResponseEntity<>(book,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(book,HttpStatus.BAD_REQUEST);
		}
	}

}
