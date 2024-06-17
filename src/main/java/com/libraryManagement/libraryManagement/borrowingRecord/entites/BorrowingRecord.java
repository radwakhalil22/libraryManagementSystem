package com.libraryManagement.libraryManagement.borrowingRecord.entites;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.libraryManagement.libraryManagement.auditing.entities.BaseEntity;
import com.libraryManagement.libraryManagement.books.entities.Books;
import com.libraryManagement.libraryManagement.patron.entities.Patron;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "borrowing_records")
@Data
@EqualsAndHashCode(callSuper=false)
public class BorrowingRecord extends BaseEntity implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5114877211647636125L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_BorrowingRecords_BookId"), nullable = false)
	    private Books book;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "patron_id", foreignKey = @ForeignKey(name = "FK_BorrowingRecords_PatronId"), nullable = false)
	    private Patron patron;

	    @Column(name = "borrowing_date")
	    private LocalDateTime borrowingDate;
	    
	    @Column(name = "due_return_date")
	    private LocalDateTime dueReturnDate;
	    
	    @Column(name = "actual_return_date")
	    private LocalDateTime actualReturnDate;
	    
	    @Column(name = "is_late_return")
	    private Boolean lateReturn = false;

//	    @Column(name = "return_date")
//	    private LocalDateTime returnDate;

}
