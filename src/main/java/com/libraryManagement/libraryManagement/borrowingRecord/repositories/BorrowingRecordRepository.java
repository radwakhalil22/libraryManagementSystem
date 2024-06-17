package com.libraryManagement.libraryManagement.borrowingRecord.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.libraryManagement.borrowingRecord.entites.BorrowingRecord;

@Repository
public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecord, Long> {
	
	BorrowingRecord findByBookIdAndPatronIdAndActualReturnDateIsNull(Long bookId, Long patronId);
	
	List<BorrowingRecord> findByBookIdAndActualReturnDateIsNotNull(Long bookId);
	List<BorrowingRecord> findByBookIdAndActualReturnDateIsNull(Long bookId);
	
	List<BorrowingRecord> findByPatronIdAndActualReturnDateIsNotNull(Long patronId);
	List<BorrowingRecord> findByPatronIdAndActualReturnDateIsNull(Long patronId);
	
	List<BorrowingRecord> findAllByActualReturnDateIsNullAndLateReturnIsFalse();

}
