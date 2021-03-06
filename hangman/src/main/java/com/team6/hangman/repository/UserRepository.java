package com.team6.hangman.repository;

import com.team6.hangman.entity.Users;

import java.util.Optional;


public interface UserRepository {
	
	Users signIn(Users user);
	Optional<Users> findById(String user_id);
	Optional<Users> findByNn(String user_nickname);
	
}
