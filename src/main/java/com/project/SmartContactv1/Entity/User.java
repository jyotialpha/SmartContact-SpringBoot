package com.project.SmartContactv1.Entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name is required")
	@Size(min = 2,max = 20,message = "character should be min 2 and max 20")
	private String name;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    @Pattern(regexp = "^(?=.{1,256}$)[A-Za-z0-9_!#$%&'*+/=?`{|}~^.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
             message = "Email format is not valid")
	private String email;
	@NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", 
             message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    @Column(nullable = false)
	private String password;
	private String phonecode;
	@Column(nullable = false)
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^(?:[0-9] ?){6,14}[0-9]$", message = "Phone number must be in international format")
	private String phoneNumber;
    private String role;
    private String enable;
    private String imageUrl;
    @Column(length = 500)
    private String description;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,mappedBy ="user")
    private List<Contact> contacts = new ArrayList<>();
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phonecode
	 */
	public String getPhonecode() {
		return phonecode;
	}
	/**
	 * @param phonecode the phonecode to set
	 */
	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the enable
	 */
	public String getEnable() {
		return enable;
	}
	/**
	 * @param enable the enable to set
	 */
	public void setEnable(String enable) {
		this.enable = enable;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phonecode="
				+ phonecode + ", phoneNumber=" + phoneNumber + ", role=" + role + ", enable=" + enable + ", imageUrl="
				+ imageUrl + ", description=" + description + ", contacts=" + contacts + "]";
	}
	public User(int id, String name, String email, String password, String phonecode, String phoneNumber, String role,
			String enable, String imageUrl, String description, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phonecode = phonecode;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.enable = enable;
		this.imageUrl = imageUrl;
		this.description = description;
		this.contacts = contacts;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
