package de.uni.koeln.se.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findBooks(){
		return bookRepo.findAll();
	}
	
	public Optional<Book> fetchBook(int id){
		return bookRepo.findById(id);
	}
	
	public List<Book> findOldestBook(){
		List<Book>books=bookRepo.findAll();
		List<Book>oldest=new ArrayList<Book>();
		int year=books.get(0).getYear();
		for (int i=0;i<books.size();i++) {
			if(books.get(i).getYear()<year) {
				year=books.get(i).getYear();
				oldest.removeAll(oldest);
			}
			if(books.get(i).getYear()==year) {
				oldest.add(books.get(i));
			}
		}
		return oldest;
	}
	
	public List<Book> findLatestBook(){
		List<Book>books=bookRepo.findAll();
		List<Book>latest=new ArrayList<Book>();
		int year=books.get(0).getYear();
		for (int i=0;i<books.size();i++) {
			if(books.get(i).getYear()>year) {
				year=books.get(i).getYear();
				latest.removeAll(latest);
			}
			if(books.get(i).getYear()==year) {
				latest.add(books.get(i));
			}
		}
		return latest;
		
	}
	
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	public boolean deleteBook(int id) {
		boolean status;
		try {
			bookRepo.deleteById(id);
			status=true;
		}catch (Exception e) {
			status=false;
		}
		return status;
	}

}