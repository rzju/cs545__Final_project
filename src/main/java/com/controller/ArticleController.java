package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.github.pagehelper.Page;
import com.service.ArticleService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/article", produces = "application/json; charset=utf-8")
@CrossOrigin
public class ArticleController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private ArticleService articleService;

	@GetMapping(value = "createArticle.action")
	public Map<String, Object> createArticle() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertArticle.action")
	public Map<String, Object> insertArticle(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Article article = new Article();
		article.setTitle(obj.getString("title"));
		article.setImage(obj.getString("image"));
		article.setContents(obj.getString("contents"));
		article.setAddtime(VeDate.getStringDateShort());
		article.setHits("0");
		int num = this.articleService.insertArticle(article);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@GetMapping(value = "deleteArticle.action")
	public Map<String, Object> deleteArticle(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.articleService.deleteArticle(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@PostMapping(value = "deleteArticleByIds.action")
	public Map<String, Object> deleteArticleByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String articleid : ids) {
			num += this.articleService.deleteArticle(articleid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@PostMapping(value = "updateArticle.action")
	public Map<String, Object> updateArticle(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Article article = this.articleService.getArticleById(obj.getString("articleid"));
		article.setTitle(obj.getString("title"));
		article.setImage(obj.getString("image"));
		article.setContents(obj.getString("contents"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.articleService.updateArticle(article);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@GetMapping(value = "getAllArticle.action")
	public List<Article> getAllArticle() {
		return this.articleService.getAllArticle();
	}

	@GetMapping(value = "getArticleMap.action")
	public Map<String, Object> getArticleMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Article article = new Article();
		article.setTitle(keywords);
		List<Article> list = this.articleService.getArticleByLike(article);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getArticleByPage.action")
	public Map<String, Object> getArticleByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Article> list = this.articleService.getAllArticle();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getArticle.action")
	public Map<String, Object> getArticle(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Article article = new Article();
		article.setTitle(keywords);
		List<Article> list = this.articleService.getArticleByLike(article);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerArticle.action")
	public Map<String, Object> getOwnerArticle(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Article article = new Article();
		List<Article> list = this.articleService.getArticleByLike(article);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getArticleById.action")
	public Article getArticleById(String id) {
		Article article = this.articleService.getArticleById(id);
		return article;
	}

	// TODO Auto-generated method stub
}


