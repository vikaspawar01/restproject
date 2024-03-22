package com.example.restApi.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.restApi.models.Address;
import com.example.restApi.models.Blog;
import com.example.restApi.models.User;
import com.example.restApi.services.UserService;
import com.example.restApi.wrappers.ResponseWrapper;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResponseWrapper responseWrapper;
	@GetMapping("")
		public ResponseEntity<User> getAllUsers() {
		responseWrapper.setMsg("users");
		responseWrapper.setData(this.userService.getAll());
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		responseWrapper.setMsg("user with id "+id);
		responseWrapper.setData(this.userService.getProjectedById(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<User> createuser(@RequestBody @Valid User user) {
		responseWrapper.setMsg("user created successfully");
		responseWrapper.setData(this.userService.create(user));
		return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id,@RequestBody User user) {
//		User userexit=this.userService.getById(id);
//		if(userexit==null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id " +id+" not found");
//		}
		responseWrapper.setMsg("user updated successfully");
		responseWrapper.setData(this.userService.update(id, user));	
		return  new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		responseWrapper.setMsg("user with id "+id+" deleted successfully");
		responseWrapper.setData(this.userService.delete(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity<User> findByName(@RequestParam("name") String name){
		responseWrapper.setMsg("user with name "+name);
		responseWrapper.setData(this.userService.findByName(name));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@GetMapping("/find-by-email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email){
		responseWrapper.setMsg("user with email "+ email);
		responseWrapper.setData(this.userService.findByEmail(email));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	

	@PostMapping("/{id}/address")
	public ResponseEntity<User> createAddress(@PathVariable Integer id,@RequestBody  Address address) {
		responseWrapper.setMsg("address created successfully");
		responseWrapper.setData(this.userService.addAddress(id, address));
		return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/address")
	public ResponseEntity<User> getAddress(@PathVariable Integer id) {
		responseWrapper.setMsg("Address with id"+id);
		responseWrapper.setData(this.userService.getAddress(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/address")
	public ResponseEntity<User> updateAddress(@PathVariable Integer id,@RequestBody Address address) {
		responseWrapper.setMsg("Address for user with id " + id + " updated successfully");
		responseWrapper.setData(this.userService.updateAddress(id, address));	
		return  new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/address")
	public ResponseEntity<User> deleteAddress(@PathVariable Integer id) {
		responseWrapper.setMsg("address with id "+ id + " deleted successfully");
		responseWrapper.setData(userService.deleteAddress(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@PostMapping("/{id}/blogs")
	public ResponseEntity<User> createBlog(@PathVariable Integer id,@RequestBody  Blog blog) {
		responseWrapper.setMsg("Blog created successfully");
		responseWrapper.setData(this.userService.addBlog(id, blog));
		return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/blogs")
	public ResponseEntity<User> getblogs(@PathVariable Integer id) {
		responseWrapper.setMsg("All blogs of id"+id);
		responseWrapper.setData(this.userService.getblogs(id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/blogs/{blog_id}")
	public ResponseEntity<User> getBlog(@PathVariable Integer id, @PathVariable Integer blog_id) {
		responseWrapper.setMsg("blog with id"+blog_id);
		responseWrapper.setData(this.userService.getBlog(id,blog_id));
		return new ResponseEntity(responseWrapper,HttpStatus.OK);
	}
}

