package com.jn.domain;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author Carrot
 *
 */
@Component("customer")
@Scope("prototype")
public class Customer {
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String yearOfBirth;
	private String gender;
	private String phoneNumber;
	private String address;
	private String image;
	private String type1SN;
	private int grade1;
	private String type2SN;
	private int grade2;
	private String type3SN;
	private int grade3;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType1SN() {
		return type1SN;
	}
	public void setType1SN(String type1sn) {
		type1SN = type1sn;
	}
	public int getGrade1() {
		return grade1;
	}
	public void setGrade1(int grade1) {
		this.grade1 = grade1;
	}
	public String getType2SN() {
		return type2SN;
	}
	public void setType2SN(String type2sn) {
		type2SN = type2sn;
	}
	public int getGrade2() {
		return grade2;
	}
	public void setGrade2(int grade2) {
		this.grade2 = grade2;
	}
	public String getType3SN() {
		return type3SN;
	}
	public void setType3SN(String type3sn) {
		type3SN = type3sn;
	}
	public int getGrade3() {
		return grade3;
	}
	public void setGrade3(int grade3) {
		this.grade3 = grade3;
	}
	
}
