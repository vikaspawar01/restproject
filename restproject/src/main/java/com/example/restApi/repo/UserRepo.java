package com.example.restApi.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.restApi.models.User;
import com.example.restApi.projections.UserProjection;
@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	List<UserProjection> findAllProjectedBy();
	Optional<UserProjection> findUserById(Integer id);
	List<User> findByName(String name);
	List<User> findByNameLike(String name);
	List<User> findByNameContaining(String name);
	Optional<User> findByEmail(String email);
	List<User> findByEmailContaining(String email);
	List<User> findByNameOrEmail(String name,String email);
}
