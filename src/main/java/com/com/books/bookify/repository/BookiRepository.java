package com.com.books.bookify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.com.books.bookify.models.UserDetails;

public interface BookiRepository extends JpaRepository<UserDetails, String> {

}
