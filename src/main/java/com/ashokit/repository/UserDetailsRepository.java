package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Serializable> {
	
	public UserDetailsEntity findByEmailAndPazzword(String email, String pazzword);
	public UserDetailsEntity findByEmail(String emailId);
	

}
