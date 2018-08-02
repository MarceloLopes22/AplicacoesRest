package com.claro.mobile.api.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mobile {

	@Id
	private String id;
	
	@Size(min = 2)
	private int price;
	
	@NotBlank(message = "campo modelo não pode ser vazio")
	private String model;
	
	@NotBlank(message = "campo modelo não pode ser vazio")
	private String brand;
	
	@NotBlank(message = "campo photo não pode ser vazio")
	private String photo;
	
	private String date;
	
	@Indexed(unique = true)
	@NotBlank(message = "campo codigo não pode ser branco")
	@Size(min=6)
	private String code;

	//private ProfileEnum profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}/*

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}*/
}
