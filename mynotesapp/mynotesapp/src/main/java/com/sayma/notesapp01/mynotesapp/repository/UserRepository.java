package com.sayma.notesapp01.mynotesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayma.notesapp01.mynotesapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
