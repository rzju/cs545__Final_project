package com.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.util.VeDate;
public class Cate {
	private String cateid = "C" + VeDate.getStringId();
	private String catename;
	private String addtime;
	private String memo;
	private List<Foods> foodsList = new ArrayList<Foods>();

	public List<Foods> getFoodsList() {
		return foodsList;
	}

	public void setFoodsList(List<Foods> foodsList) {
		this.foodsList = foodsList;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("cateid", this.cateid);
		jsonString.put("catename", this.catename);
		jsonString.put("addtime", this.addtime);
		jsonString.put("memo", this.memo);
		return jsonString.toString();
	}

}
