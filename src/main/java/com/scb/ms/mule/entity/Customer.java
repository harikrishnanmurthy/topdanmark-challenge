package com.scb.ms.mule.entity;

public class Customer {

	private String customerId;
	private String name;
	private String street_1;
	private String street_2;
	private String zipcode;
	private String city;
	private String country;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet_1() {
		return street_1;
	}
	public void setStreet_1(String street_1) {
		this.street_1 = street_1;
	}
	public String getStreet_2() {
		return street_2;
	}
	public void setStreet_2(String street_2) {
		this.street_2 = street_2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
