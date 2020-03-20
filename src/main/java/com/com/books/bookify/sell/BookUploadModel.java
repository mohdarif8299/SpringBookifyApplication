package com.com.books.bookify.sell;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class BookUploadModel {
	@Id
	private String upload_id;
	public String getUpload_id() {
		return upload_id;
	}
	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}
	@NotNull
	private Long id;
	@NotNull
	private String image1,image2,image3,image4;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private String category;
	@NotNull
	private String price;
	@NotNull
	private String username;
	@NotNull
	private String String;
	public String getString() {
		return String;
	}
	public void setString(String String) {
		this.String = String;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
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
	public BookUploadModel() {
		super();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BookUploadModel(String upload_id, @NotNull Long id, @NotNull String image1, @NotNull String image2,
			@NotNull String image3, @NotNull String image4, @NotNull String title, @NotNull String description,
			@NotNull String category, @NotNull String price, @NotNull String username, @NotNull String String) {
		super();
		this.upload_id = upload_id;
		this.id = id;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.title = title;
		this.description = description;
		this.category = category;
		this.price = price;
		this.username = username;
		this.String = String;
	}
	
}
