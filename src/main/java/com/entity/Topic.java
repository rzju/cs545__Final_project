package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Topic {
	private String topicid = "T"+VeDate.getStringId();
	private String ordersid;
	private String usersid;
	private String foodsid;
	private String num;
	private String contents;
	private String addtime;
	private String ordercode;
	private String username;
	private String foodsname;
	private Orders orders;
	private Users users;
	private Foods foods;
	public String getTopicid() {
		return this.topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getOrdersid() {
		return this.ordersid;
	}

	public void setOrdersid(String ordersid) {
		this.ordersid = ordersid;
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

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
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

	public String getOrdercode() {
		return this.ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
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
		jsonString.put("topicid", this.topicid);
		jsonString.put("ordersid", this.ordersid);
		jsonString.put("usersid", this.usersid);
		jsonString.put("foodsid", this.foodsid);
		jsonString.put("num", this.num);
		jsonString.put("contents", this.contents);
		jsonString.put("addtime", this.addtime);
		jsonString.put("Orders", this.orders);
		jsonString.put("Users", this.users);
		jsonString.put("Foods", this.foods);
		jsonString.put("ordercode", this.ordercode);
		jsonString.put("username", this.username);
		jsonString.put("foodsname", this.foodsname);
		return jsonString.toString();
	}




}




