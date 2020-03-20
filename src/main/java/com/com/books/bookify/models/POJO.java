package com.com.books.bookify.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class POJO {
	@JsonProperty
   private byte[] bytes;
   private String name;
public byte[] getBytes() {
	return bytes;
}
public void setBytes(byte[] bytes) {
	this.bytes = bytes;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public POJO(byte[] bytes, String name) {
	super();
	this.bytes = bytes;
	this.name = name;
}
public POJO() {
	super();
}
@Override
public String toString() {
	return "POJO [bytes=" + Arrays.toString(bytes) + ", name=" + name + "]";
}

}
