package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Foods {
	private String foodsid = "F"+VeDate.getStringId();
	private String foodsname;
	private String image;
	private String cateid;
	private String price;
	private String recommend;
	private String special;
	private String addtime;
	private String hits;
	private String sellnum;
	private String contents;
	private String catename;
	private Cate cate;
	public String getFoodsid() {
		return this.foodsid;
	}

	public void setFoodsid(String foodsid) {
		this.foodsid = foodsid;
	}

	public String getFoodsname() {
		return this.foodsname;
	}

	public void setFoodsname(String foodsname) {
		this.foodsname = foodsname;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRecommend() {
		return this.recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getSellnum() {
		return this.sellnum;
	}

	public void setSellnum(String sellnum) {
		this.sellnum = sellnum;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Cate getCate() {
		return this.cate;
	}

	public void setCate(Cate cate) {
		this.cate = cate;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("foodsid", this.foodsid);
		jsonString.put("foodsname", this.foodsname);
		jsonString.put("image", this.image);
		jsonString.put("cateid", this.cateid);
		jsonString.put("price", this.price);
		jsonString.put("recommend", this.recommend);
		jsonString.put("special", this.special);
		jsonString.put("addtime", this.addtime);
		jsonString.put("hits", this.hits);
		jsonString.put("sellnum", this.sellnum);
		jsonString.put("contents", this.contents);
		jsonString.put("Cate", this.cate);
		jsonString.put("catename", this.catename);
		return jsonString.toString();
	}




}




