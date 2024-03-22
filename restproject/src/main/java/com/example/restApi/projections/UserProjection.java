package com.example.restApi.projections;

import java.util.List;

import com.example.restApi.models.Address;
import com.example.restApi.models.Blog;

public interface UserProjection {
	Integer getId();
	String getName();
	String getEmail();
	Address getAddress();
	List<Blog> getBlog();
}
