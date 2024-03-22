package com.example.restApi.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.restApi.models.Address;
import com.example.restApi.models.Blog;
import com.example.restApi.models.User;
import com.example.restApi.projections.UserProjection;
import com.example.restApi.repo.AddressRepo;
import com.example.restApi.repo.BlogRepo;
import com.example.restApi.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	HashMap <Integer, User> data=new HashMap<>();
	AtomicInteger atomicInteger =new AtomicInteger();
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	BlogRepo blogRepo;
	
	public User create(User user) {
//		user.setId(atomicInteger.incrementAndGet());
//		this.data.put(user.getId(), user);
//		return user;
		return userRepo.save(user);
		
	
	}
	
//	public Collection<User> getAll(){
//		return this.data.values();
//	}
	@Cacheable("alldata")
	public Iterable<UserProjection> getAll(){
//		return this.userRepo.findAll();
		System.out.println("get all invoke");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.userRepo.findAllProjectedBy();
	}
	
	public UserProjection getProjectedById(Integer id) {
		return this.userRepo.findUserById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id "+id+" not found");
		});
	}
	
//	public User getById(Integer id) {
//		return this.data.get(id);
//	}

	public User getById(Integer id) {
		return this.userRepo.findById(id).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id "+id+" not found");
		});
	}
	
	
	public User update(Integer id,User user) {
		getById(id);
		user.setId(id);
		return this.userRepo.save(user);
		
	}
	
	
	 @Transactional
	    public User delete(Integer id) {
	        // Retrieve the user by ID
	        User user = getById(id);

	        try {
	            // Delete the user
	            userRepo.deleteById(id);
	        } catch (Exception e) {
	            // Handle any exceptions
	            throw new RuntimeException("Failed to delete user with ID: " + id, e);
	        }

	        return user;
	    }
	public List<User> findByName(String name) {
//		return this.userRepo.findByName(name);
//		return this.userRepo.findByNameLike(name);
		return this.userRepo.findByNameContaining(name);
	}
	
	public User findByEmail(String email) {
		return this.userRepo.findByEmail(email).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user with email "+email+"not found");
		});
	}
	
	public Address addAddress(Integer user_id, Address address) {
		User user=getById(user_id);
		Address savedAddress=this.addressRepo.save(address);
		user.setAddress(savedAddress);
		this.userRepo.save(user);
		return savedAddress;
	}
	
	public Address getAddress(Integer user_id) {
		User user=getById(user_id);
		return user.getAddress();
	}
	
	public Address updateAddress(Integer user_id, Address address) {
		User user=getById(user_id);
        Address currentAddress = user.getAddress();
		currentAddress.setState(address.getState());
		currentAddress.setCity(address.getCity());
		currentAddress.setZipcode(address.getZipcode());
		Address updateAddress=this.addressRepo.save(currentAddress);
		return updateAddress(user_id, address);
	}
	
	public Address deleteAddress(Integer user_id) {
		User user=getById(user_id);
		Address deleteAddress=user.getAddress();
		this.addressRepo.deleteById(deleteAddress.getId());
		return deleteAddress;
	}
	
	public Blog addBlog(Integer user_id,Blog blog) {
		User user=getById(user_id);
		blog.setUser(user);
		return this.blogRepo.save(blog);
	}
	
	public List<Blog> getblogs(Integer user_id) {
		User user=getById(user_id);
		return user.getBlog();
	}
	
	public Blog getBlog(Integer userId, Integer blogId) {
		User user = getById(userId);
		Optional<Blog> optionalBlog = user.getBlog().stream()
				.filter(blog -> blog.getId().equals(blogId))
				.findFirst();
		
		return optionalBlog.orElseThrow(() ->
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog with id "+blogId+ "not found"));
	}
}
