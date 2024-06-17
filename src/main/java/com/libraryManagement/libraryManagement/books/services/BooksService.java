package com.libraryManagement.libraryManagement.books.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libraryManagement.libraryManagement.books.models.request.BookReqModel;
import com.libraryManagement.libraryManagement.books.models.response.BookResModel;
import com.libraryManagement.libraryManagement.books.projections.BookProjection;

@Service
public interface BooksService {

	BookProjection createBook(BookReqModel bookReqModel);

	BookResModel getBookById(Long bookId);

	BookResModel updateBookById(BookReqModel bookReqModel, Long bookId);

	void deleteBookById(Long bookId);

	List<BookResModel> getAllBooks(Integer pageSize, Integer pageIndex, String sortField, String sortOrder);

}
