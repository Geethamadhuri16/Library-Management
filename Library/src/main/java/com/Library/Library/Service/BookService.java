package com.Library.Library.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Library.Library.Model.Book;
import com.Library.Library.Model.User;
import com.Library.Library.Repo.BookRepo;
import com.Library.Library.Repo.UserRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo brepo;
	
	@Autowired
	private UserRepo repo;
	public ResponseEntity<String> addBook(Book b){
		try {
			brepo.save(b);
			return new ResponseEntity<>("added",HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<>("Could not proces the request",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	}
	
	public ResponseEntity<Book> bookDetails(Long id){
		try {
			if(brepo.findById(id)!=null) {
				Book b=brepo.findById(id).orElse(null);
				return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> deleteBook(Long id){
		try {
			if(brepo.findById(id)!=null) {
				brepo.delete(brepo.getById(id));
				return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<String> allotBook(Long uid, List<Long> bid) {
	    User u = repo.findById(uid).orElse(null);

	    if (u.getFine() == null && u.getQuantity() < 5) {
	        try {
	            for (int i = 0; i < bid.size(); i++) {
	                Book b = brepo.getById(bid.get(i));
	                if (b.getAvailability().equals("available")) {
	                    b.setAvailability("notavailable");
	                    List<Long> bookIds = u.getBooks();
	                    bookIds.addAll(bid);
	                    u.setBooks(bookIds);
	                    u.setQuantity(u.getQuantity() + 1);
	                    b.setBorrowDate(getDate());
	                    repo.save(u);
	                    brepo.save(b);
	                } else {
	                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	                }
	            }
	            // Move the return statement outside the loop
	            return new ResponseEntity<>("allotted", HttpStatus.ACCEPTED);

	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}
	
	public ResponseEntity<String> submitBook(List<Long> bIds, Long uid) {
        User u = repo.getById(uid);
        try {
            if (u != null) {
                List<Long> books = u.getBooks();

                for (int i = 0; i < bIds.size(); i++) {
                    long bookId = bIds.get(i);
                    int n = books.indexOf(bookId);
                    
                    books.remove(n);
                    Book b = brepo.getById(bookId);

                    // Calculate fine for the book being submitted
                    calculateFineForBook(b, u);
                    u.setQuantity(u.getQuantity()-1);
          

                    b.setBorrowDate(null);
                    b.setAvailability("available");
                    brepo.save(b);
                    
                }

                

                u.setBooks(books);
                repo.save(u);
                return new ResponseEntity<>("submitted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void calculateFineForBook(Book book, User user) {
        if (book != null && user != null && book.getBorrowDate() != null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime borrowedDateTime = LocalDateTime.parse(book.getBorrowDate(), formatter);

            long daysOverdue = currentDateTime.toLocalDate().toEpochDay() - borrowedDateTime.toLocalDate().toEpochDay();

            // Calculate fine for each overdue day (assuming 1 rupee per day)
            long fineForBook = daysOverdue > 15 ? (daysOverdue - 15) : 0;

            // Add fine to the user's total fine
            if (user.getFine() == null) {
                user.setFine(fineForBook);
            } else {
                user.setFine(user.getFine() + fineForBook);
            }
        }
    }


	
	private String getDate() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		return formattedDateTime;
	}
	
	
	
	

}
