package com.example.restApi.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.restApi.models.Blog;

public interface BlogRepo extends CrudRepository<Blog, Integer>{

}
