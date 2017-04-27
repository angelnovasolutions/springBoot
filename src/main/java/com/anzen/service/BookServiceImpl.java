package com.anzen.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.anzen.dao.BookCrudRepository;
import com.anzen.dao.BookOwnJPARepository;
import com.anzen.dao.BookOwnHibernateRepository;
import com.anzen.bean.Libro;
import com.anzen.bean.Book;
import com.anzen.bean.ParametrosSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.anzen.dao.BookMyBatisMapper;


@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookCrudRepository bookRepository;
	
	@Autowired
	private BookOwnJPARepository bookOwnJPARepository; 
	
	@Autowired
	private BookOwnHibernateRepository bookOwnHibernateRepository;
	
	@Autowired
	private BookMyBatisMapper bookMyBatisMapper;
	
	// CrudRepository
	public Book findOne(long id) {
		return bookRepository.findOne(id);
	}

	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}


	// BookOwnJPARepository
	public Book getById(long id) {
		return bookOwnJPARepository.getById(id);
	}
	
	public List<Book> getAll() {
		return bookOwnJPARepository.getAll();
	}

	@Override
	public String getCallSP() {
		return bookOwnJPARepository.getCallSP();
	}
	
	@Override
	public List<Libro> getQueryBookSP(int id) {
		
		// Nombre SP
		String nombreSP = "spQueryBook";
		
		// Parametros SP
		List<ParametrosSP> lstParametrosSP = new ArrayList<ParametrosSP>();
		ParametrosSP pId = new ParametrosSP(1,"id",id);
		lstParametrosSP.add(pId);
		
		// Invocar SP
		@SuppressWarnings("unchecked")
		List<Libro> lstBook = (List<Libro>)bookOwnJPARepository.getQuerySP(nombreSP, lstParametrosSP);
		return lstBook;
	}

	@Override
	public String getCreateBookSP(int id, String autor, String name) {
		
		// Nombre SP
		String nombreSP = "spInsertBook";
		
		// Parametros SP
		List<ParametrosSP> lstParametrosSP = new ArrayList<ParametrosSP>();
		ParametrosSP pId = new ParametrosSP(1,"id",id);
		ParametrosSP pAuthor = new ParametrosSP(3,"author",autor);
		ParametrosSP pName = new ParametrosSP(3,"name",name);
		lstParametrosSP.add(pId);
		lstParametrosSP.add(pAuthor);
		lstParametrosSP.add(pName);
		
		// Invocar SP
		@SuppressWarnings("unchecked")
		Vector<Object> response = (Vector<Object>)bookOwnJPARepository.getCrUpDeSP(nombreSP, lstParametrosSP);
		
		// Regresar respuesta
		if((Integer)response.get(0)==0){
			return "Exitoso";
		} else {
			return (String)response.get(1);
		}
				
		
	}
	
	
	// BookOwnHibernateRepository
	@Override
	public List<Book> getAllBooks() {
		return bookOwnHibernateRepository.getAllBooks();
	}

	@Override
	public List<Book> getBook(long id) {
		return bookOwnHibernateRepository.getBook(id);
	}

	
	// BookMyBatisMapper
	@Override
	public Book findBookById(long id) {
		return bookMyBatisMapper.findBookById(id);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookMyBatisMapper.findAllBooks();
	}

	@Override
	public String getCallSPMyBatis(int param1, int param2, String texto) {
		
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("param1", param1);
	    params.put("param2", param2);
	    
		bookMyBatisMapper.getCallSPMyBatis(params);
		
		return "La suma es: " + params.get("suma").toString();
	}

}