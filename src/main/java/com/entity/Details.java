package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Details {
	private String detailsid = "D"+VeDate.getStringId();
	private String ordercode;
	private String foodsid;
	private String price;
	private String num;
	private String foodsname;
	private Foods foods;
	public String getDetailsid() {
		return this.detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}

	public String getOrdercode() {
		return this.ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getFoodsid() {
		return this.foodsid;
	}

	public void setFoodsid(String foodsid) {
		this.foodsid = foodsid;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Foods getFoods() {
		return this.foods;
	}

	public void setFoods(Foods foods) {
		this.foods = foods;
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
		jsonString.put("detailsid", this.detailsid);
		jsonString.put("ordercode", this.ordercode);
		jsonString.put("foodsid", this.foodsid);
		jsonString.put("price", this.price);
		jsonString.put("num", this.num);
		jsonString.put("Foods", this.foods);
		jsonString.put("foodsname", this.foodsname);
		return jsonString.toString();
	}




}




