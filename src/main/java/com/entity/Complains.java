package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Complains {
	private String complainsid = "C"+VeDate.getStringId();
	private String usersid;
	private String title;
	private String contents;
	private String addtime;
	private String status;
	private String reps;
	private String username;
	private Users users;
	public String getComplainsid() {
		return this.complainsid;
	}

	public void setComplainsid(String complainsid) {
		this.complainsid = complainsid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReps() {
		return this.reps;
	}

	public void setReps(String reps) {
		this.reps = reps;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("complainsid", this.complainsid);
		jsonString.put("usersid", this.usersid);
		jsonString.put("title", this.title);
		jsonString.put("contents", this.contents);
		jsonString.put("addtime", this.addtime);
		jsonString.put("status", this.status);
		jsonString.put("reps", this.reps);
		jsonString.put("Users", this.users);
		jsonString.put("username", this.username);
		return jsonString.toString();
	}




}




