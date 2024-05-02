package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Users {
	private String usersid = "U"+VeDate.getStringId();
	private String username;
	private String password;
	private String realname;
	private String sex;
	private String birthday;
	private String contact;
	private String regdate;
	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRegdate() {
		return this.regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("usersid", this.usersid);
		jsonString.put("username", this.username);
		jsonString.put("password", this.password);
		jsonString.put("realname", this.realname);
		jsonString.put("sex", this.sex);
		jsonString.put("birthday", this.birthday);
		jsonString.put("contact", this.contact);
		jsonString.put("regdate", this.regdate);
		return jsonString.toString();
	}




}




