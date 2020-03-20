package com.com.books.bookify.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.books.bookify.models.UserDetails;
import com.com.books.bookify.repository.BookiRepository;

@Service
public class UserDao {
	@Autowired
	BookiRepository bookiRepository;
	public Optional<UserDetails> findOne(String empid) {
		return bookiRepository.findById(empid);
	}
	public UserDetails registerUser(UserDetails userDetails) {
		return bookiRepository.save(userDetails);
	}
	public boolean checkUsername(String empid) {
		return bookiRepository.existsById(empid);
	}
}
