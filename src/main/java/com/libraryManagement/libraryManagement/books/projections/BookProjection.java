package com.libraryManagement.libraryManagement.books.projections;

import java.time.LocalDate;

public interface BookProjection {
	
    Long getId();
    String getTitle();
    String getAuthor();
    LocalDate getPublicationYear();
    String getISBN();
    Boolean getAvailable();

}
