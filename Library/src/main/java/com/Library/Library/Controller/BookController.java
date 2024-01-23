package com.Library.Library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.Library.Model.Book;
import com.Library.Library.Service.BookService;

@RestController

public class BookController {
	@Autowired
	private BookService service;
	
	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody Book b){
		return service.addBook(b);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookDetails(@PathVariable("id")Long id){
		return service.bookDetails(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delBook(@PathVariable("id")Long id){
		return service.deleteBook(id);
	}
	
	@PostMapping("/allotBook/{uid}")
    public ResponseEntity<String> allotBook(
            @PathVariable Long uid,
            @RequestBody List<Long> bid
    ) {
       
        return service.allotBook(uid, bid);
    }
	
	@PostMapping("/submit/{uid}")
	public ResponseEntity<String> submitBook(@RequestBody List<Long> bids,@PathVariable("uid")Long uid){
		return service.submitBook(bids, uid);
		
	}
	
	
	

}
