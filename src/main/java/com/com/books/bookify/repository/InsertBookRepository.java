package com.com.books.bookify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.com.books.bookify.models.AvailableBooks;


public interface InsertBookRepository extends JpaRepository<AvailableBooks, String>{

}
