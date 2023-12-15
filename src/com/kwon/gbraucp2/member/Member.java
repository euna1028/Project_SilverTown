package com.kwon.gbraucp2.member;

import java.util.Date;

public class Member {
	private String gm_id;
	private String gm_pw;
	private String gm_name;
	private Date gm_birth;
	private String gm_addr;
	private String gm_photo;
	private int gm_grade;
	private String gm_email;
	private String gm_gender;

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String gm_id, String gm_pw, String gm_name, Date gm_birth, String gm_addr, String gm_photo,int gm_grade,String gm_email,String gm_gender) {
		super();
		this.gm_id = gm_id;
		this.gm_pw = gm_pw;
		this.gm_name = gm_name;
		this.gm_birth = gm_birth;
		this.gm_addr = gm_addr;
		this.gm_photo = gm_photo;
	}
	public String getGm_id() {
		return gm_id;
	}

	public void setGm_id(String gm_id) {
		this.gm_id = gm_id;
	}

	public String getGm_pw() {
		return gm_pw;
	}

	public void setGm_pw(String gm_pw) {
		this.gm_pw = gm_pw;
	}

	public String getGm_name() {
		return gm_name;
	}

	public void setGm_name(String gm_name) {
		this.gm_name = gm_name;
	}

	public Date getGm_birth() {
		return gm_birth;
	}

	public void setGm_birth(Date gm_birth) {
		this.gm_birth = gm_birth;
	}

	public String getGm_addr() {
		return gm_addr;
	}

	public void setGm_addr(String gm_addr) {
		this.gm_addr = gm_addr;
	}

	public String getGm_photo() {
		return gm_photo;
	}

	public void setGm_photo(String gm_photo) {
		this.gm_photo = gm_photo;
	}

	public int getGm_grade() {
		return gm_grade;
	}

	public void setGm_grade(int gm_grade) {
		this.gm_grade = gm_grade;
	}

	public String getGm_email() {
		return gm_email;
	}

	public void setGm_email(String gm_email) {
		this.gm_email = gm_email;
	}

	public String getGm_gender() {
		return gm_gender;
	}

	public void setGm_gender(String gm_gender) {
		this.gm_gender = gm_gender;
	}

	
}