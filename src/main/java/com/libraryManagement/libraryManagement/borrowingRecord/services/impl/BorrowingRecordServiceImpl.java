package com.libraryManagement.libraryManagement.borrowingRecord.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.libraryManagement.libraryManagement.books.entities.Books;
import com.libraryManagement.libraryManagement.books.repositories.BooksRepository;
import com.libraryManagement.libraryManagement.borrowingRecord.entites.BorrowingRecord;
import com.libraryManagement.libraryManagement.borrowingRecord.models.mapinterface.BorrowingRecordMapper;
import com.libraryManagement.libraryManagement.borrowingRecord.models.response.BorrowingRecordResModel;
import com.libraryManagement.libraryManagement.borrowingRecord.repositories.BorrowingRecordRepository;
import com.libraryManagement.libraryManagement.borrowingRecord.services.BorrowingRecordService;
import com.libraryManagement.libraryManagement.exceptions.ApiErrorMessageKeyEnum;
import com.libraryManagement.libraryManagement.exceptions.BusinessLogicViolationException;
import com.libraryManagement.libraryManagement.patron.entities.Patron;
import com.libraryManagement.libraryManagement.patron.repositories.PatronRepository;
import com.libraryManagement.libraryManagement.tasks.services.TaskService;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
	
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    
    @Autowired
    private BorrowingRecordMapper borrowingRecordMapper;
    
    @Autowired
    private BooksRepository booksRepository;
    
    @Autowired
    private PatronRepository patronRepository;
    
    @Autowired
    private TaskService taskService;

	@Override
	public BorrowingRecordResModel borrowBook(Long bookId, Long patronId) {
		BorrowingRecordResModel borrowingRecordResModel = new BorrowingRecordResModel();		
		Optional <Books> optionalBook = booksRepository.findById(bookId);
	    if (optionalBook.isPresent()) {
	        Books book = optionalBook.get();
	        Patron patron = patronRepository.findById(patronId).orElseThrow(() ->
	                new BusinessLogicViolationException(ApiErrorMessageKeyEnum.BCV_PATRON_NOT_FOUND.name()));

	        if (book.getAvailable()) {
	            BorrowingRecord borrowingRecord = new BorrowingRecord();
	            borrowingRecord.setBook(book);
	            borrowingRecord.setPatron(patron);
	            borrowingRecord.setBorrowingDate(LocalDateTime.now());
	            borrowingRecord.setDueReturnDate(borrowingRecord.getBorrowingDate().plusWeeks(2));;

	            borrowingRecordResModel = borrowingRecordMapper.mapToBorrowingRecordResModel(borrowingRecordRepository.save(borrowingRecord));
	            book.setAvailable(false);
	            booksRepository.save(book);
	        } else {
	            throw new BusinessLogicViolationException(ApiErrorMessageKeyEnum.BCV_BOOK_IS_NOT_AVAILABLE.name());
	        }
	    } else {
	        throw new BusinessLogicViolationException(ApiErrorMessageKeyEnum.BCV_BOOK_NOT_FOUND.name());
	    }
	    return borrowingRecordResModel;
	}

	@Override
	public BorrowingRecordResModel returnBook(Long bookId, Long patronId) {
		 BorrowingRecordResModel borrowingRecordResModel = new BorrowingRecordResModel();
		 BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndActualReturnDateIsNull(bookId, patronId);

	        if (borrowingRecord != null) {
	            borrowingRecord.setActualReturnDate(LocalDateTime.now());
	            borrowingRecordResModel = borrowingRecordMapper.mapToBorrowingRecordResModel(borrowingRecordRepository.save(borrowingRecord));
	            Books book = borrowingRecord.getBook();
	            book.setAvailable(true);
	            booksRepository.save(book);
	        }
	        else {
	        	throw new BusinessLogicViolationException(ApiErrorMessageKeyEnum.BCV_BOOK_HAS_ALREADY_BEEN_RETURNED.name());
	        }
		return borrowingRecordResModel;
	}
	
	@Transactional
	@Scheduled(cron = "0 35 19 * * *")
	public void scheduledTask() {
		List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findAllByActualReturnDateIsNullAndLateReturnIsFalse();
		LocalDateTime now = LocalDateTime.now();
//		int batchSize = 100;
//      int offset = 0;
		borrowingRecords.stream()
		.filter(b -> b.getDueReturnDate().isBefore(now))
        .forEach(b -> b.setLateReturn(true));

		borrowingRecordRepository.saveAll(borrowingRecords);
		
		String taskName = taskService.generateTaskName("Update Late Return Status");
        taskService.saveTask(taskName);
	}

}
