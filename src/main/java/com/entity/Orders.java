package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Orders {
	private String ordersid = "O"+VeDate.getStringId();
	private String ordercode;
	private String usersid;
	private String total;
	private String status;
	private String addtime;
	private String receiver;
	private String address;
	private String contact;
	private String username;
	private Users users;
	public String getOrdersid() {
		return this.ordersid;
	}

	public void setOrdersid(String ordersid) {
		this.ordersid = ordersid;
	}

	public String getOrdercode() {
		return this.ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
		jsonString.put("ordersid", this.ordersid);
		jsonString.put("ordercode", this.ordercode);
		jsonString.put("usersid", this.usersid);
		jsonString.put("total", this.total);
		jsonString.put("status", this.status);
		jsonString.put("addtime", this.addtime);
		jsonString.put("receiver", this.receiver);
		jsonString.put("address", this.address);
		jsonString.put("contact", this.contact);
		jsonString.put("Users", this.users);
		jsonString.put("username", this.username);
		return jsonString.toString();
	}




}




