package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Admin {
	private String adminid = "A"+VeDate.getStringId();
	private String username;
	private String password;
	private String realname;
	private String contact;
	private String addtime;
	public String getAdminid() {
		return this.adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
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

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("adminid", this.adminid);
		jsonString.put("username", this.username);
		jsonString.put("password", this.password);
		jsonString.put("realname", this.realname);
		jsonString.put("contact", this.contact);
		jsonString.put("addtime", this.addtime);
		return jsonString.toString();
	}




}




