package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;
public class Article {
	private String articleid = "A"+VeDate.getStringId();
	private String title;
	private String image;
	private String contents;
	private String addtime;
	private String hits;
	public String getArticleid() {
		return this.articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}


	@Override
	public String toString() {
		return this.toJsonString();
	}

	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("articleid", this.articleid);
		jsonString.put("title", this.title);
		jsonString.put("image", this.image);
		jsonString.put("contents", this.contents);
		jsonString.put("addtime", this.addtime);
		jsonString.put("hits", this.hits);
		return jsonString.toString();
	}




}




