package com.com.books.bookify.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "books_available")
@EntityListeners(AuditingEntityListener.class)
public class AvailableBooks {
  @Id
  private String book_id;
  @NotNull
  private String author,
  							pages,
  							isbn,
  							title,
  							image,
  							edition,
  							quantity,
  							category,
  							price
  							;
  @Column(columnDefinition="LONGTEXT")
  private String description;
  							
  
public String getBook_id() {
	return book_id;
}

public void setBook_id(String book_id) {
	this.book_id = book_id;
}

public String getAuthor() {
	return author;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getPages() {
	return pages;
}

public void setPages(String pages) {
	this.pages = pages;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getEdition() {
	return edition;
}

public void setEdition(String edition) {
	this.edition = edition;
}

public String getQuantity() {
	return quantity;
}

public void setQuantity(String quantity) {
	this.quantity = quantity;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}



public AvailableBooks(String book_id, @NotNull String author, @NotNull String pages, @NotNull String isbn,
		@NotNull String title, @NotNull String description, @NotNull String image, @NotNull String edition,
		@NotNull String quantity, @NotNull String category, @NotNull String price) {
	super();
	this.book_id = book_id;
	this.author = author;
	this.pages = pages;
	this.isbn = isbn;
	this.title = title;
	this.description = description;
	this.image = image;
	this.edition = edition;
	this.quantity = quantity;
	this.category = category;
	this.price = price;
}

public AvailableBooks() {
	super();
}
  
}
