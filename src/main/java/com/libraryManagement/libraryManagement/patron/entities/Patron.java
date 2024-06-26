package com.libraryManagement.libraryManagement.patron.entities;

import java.io.Serializable;
import java.util.Date;

import com.libraryManagement.libraryManagement.auditing.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "patrons")
@Data
@EqualsAndHashCode(callSuper=false)
public class Patron extends BaseEntity implements Serializable {

    /**
	 * 
	 */ 
	private static final long serialVersionUID = -7340450282352863186L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "mobile")
    private String mobile;
	
    @Column(name = "email")
    private String email;
    
	@Column(name = "creation_date")
	private Date creationDate;
    
//    @PrePersist
//    public void prePersist() {
//        this.creationDate = new Date();
//    }
}
