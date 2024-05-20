package com.project.SmartContactv1.Entity;

public class Country {
	 private String country;
	 private String phoneCode;
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the phoneCode
	 */
	public String getPhoneCode() {
		return phoneCode;
	}
	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public Country(String country, String phoneCode) {
		super();
		this.country = country;
		this.phoneCode = phoneCode;
	}
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}
