package com.Library.Library.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String bookName;
	private String author;
	private String edition;
	private String availability;
	private String borrowDate;
	public Book() {
		this.setAvailability("available");
		
	}
	
	

}
