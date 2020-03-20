package com.com.books.bookify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.com.books.bookify.sell.BookUploadModel;

public interface UploadBookRepository extends JpaRepository<BookUploadModel, String>{

}
