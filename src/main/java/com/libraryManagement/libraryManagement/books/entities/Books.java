package com.libraryManagement.libraryManagement.books.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.libraryManagement.libraryManagement.auditing.entities.BaseEntity;

//import com.libraryManagement.libraryManagement.auditing.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode(callSuper=false)
public class Books extends BaseEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3335829666377057360L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(name = "publication_year")
    private LocalDate publicationYear;

    @Column(name = "isbn",nullable = false, unique = true)
    private String ISBN;
    
    @Column(name = "available")
    private Boolean available = true;

}
