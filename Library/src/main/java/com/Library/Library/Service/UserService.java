package com.Library.Library.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Library.Library.Model.User;
import com.Library.Library.Repo.UserRepo;

@Service

public class UserService {
	@Autowired
	private UserRepo repo;
	
	public ResponseEntity<String> addUser(User u){
		try {
			repo.save(u);
			return new ResponseEntity<>("added",HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	public ResponseEntity<String> delUser(Long id){
		User u=repo.getById(id);
		try {
			if(u!=null) {
				repo.delete(u);
				return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
				
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<User> getUser(Long id){
		User u=repo.findById(id).orElse(null);
		try {
			if(u!=null) {
				
				return new ResponseEntity<>(u,HttpStatus.OK);
			}else {
				
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
				
			}
		}catch(Exception e) {
			
			System.out.println(e);
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Long>> getBooks(Long id){
		User u=repo.findById(id).orElse(null);
		try {
			if(u!=null) {
				
				return new ResponseEntity<>(u.getBooks(),HttpStatus.OK);
			}else {
				
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
				
			}
		}catch(Exception e) {
			
			System.out.println(e);
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}
