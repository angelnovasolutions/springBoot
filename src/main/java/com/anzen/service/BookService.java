package com.anzen.service;

import com.anzen.bean.Book;
import com.anzen.bean.Libro;
import java.util.List;


public interface BookService {
	
	// CrudRepository
	public Book findOne(long id);
	public List<Book> findAll();
	
	// OwnJPARepository
	public Book getById(long id);
	public List<Book> getAll();
	public String getCallSP();
	public List<Libro> getQueryBookSP(int id);
	public String getCreateBookSP(int id, String autor, String name);
	
	// BookOwnHibernateRepository
	public List<Book> getAllBooks();
	public List<Book> getBook(long id);
	
	// MyBatis
	public Book findBookById(long id);
	public List<Book> findAllBooks();
	public String getCallSPMyBatis(int param1, int param2, String texto);
	
}
