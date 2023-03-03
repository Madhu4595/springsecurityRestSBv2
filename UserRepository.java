package com.example.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.models.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	  Boolean existsByUsername(String username);

	  
}
