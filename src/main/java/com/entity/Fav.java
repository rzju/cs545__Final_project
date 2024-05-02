package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Fav {
	private String favid = "F"+VeDate.getStringId();
	private String usersid;
	private String foodsid;
	private String addtime;
	private String username;
	private String foodsname;
	private Users users;
	private Foods foods;
	public String getFavid() {
		return this.favid;
	}

	public void setFavid(String favid) {
		this.favid = favid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getFoodsid() {
		return this.foodsid;
	}

	public void setFoodsid(String foodsid) {
		this.foodsid = foodsid;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Foods getFoods() {
		return this.foods;
	}

	public void setFoods(Foods foods) {
		this.foods = foods;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFoodsname() {
		return this.foodsname;
	}

	public void setFoodsname(String foodsname) {
		this.foodsname = foodsname;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("favid", this.favid);
		jsonString.put("usersid", this.usersid);
		jsonString.put("foodsid", this.foodsid);
		jsonString.put("addtime", this.addtime);
		jsonString.put("Users", this.users);
		jsonString.put("Foods", this.foods);
		jsonString.put("username", this.username);
		jsonString.put("foodsname", this.foodsname);
		return jsonString.toString();
	}




}




