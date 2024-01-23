package com.Library.Library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Library.Library.Model.User;
import com.Library.Library.Service.UserService;

@RestController
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delUser/{id}")
    public ResponseEntity<String> delUser(@PathVariable Long id) {
        return userService.delUser(id);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    
    @GetMapping("/getBooks/{id}")
    public ResponseEntity<List<Long>> getBooks(@PathVariable Long id){
    	return userService.getBooks(id);
    }
	

}
