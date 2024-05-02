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
import com.entity.Foods;
import com.github.pagehelper.Page;
import com.service.FoodsService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/foods", produces = "application/json; charset=utf-8")
@CrossOrigin
public class FoodsController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private FoodsService foodsService;

	@GetMapping(value = "createFoods.action")
	public Map<String, Object> createFoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertFoods.action")
	public Map<String, Object> insertFoods(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Foods foods = new Foods();
		foods.setFoodsname(obj.getString("foodsname"));
		foods.setImage(obj.getString("image"));
		foods.setCateid(obj.getString("cateid"));
		foods.setPrice(obj.getString("price"));
		foods.setRecommend(obj.getString("recommend"));
		foods.setSpecial(obj.getString("special"));
		foods.setAddtime(VeDate.getStringDateShort());
		foods.setHits("0");
		foods.setSellnum("0");
		foods.setContents(obj.getString("contents"));
		int num = this.foodsService.insertFoods(foods);
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

	@GetMapping(value = "deleteFoods.action")
	public Map<String, Object> deleteFoods(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.foodsService.deleteFoods(id);
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

	@PostMapping(value = "deleteFoodsByIds.action")
	public Map<String, Object> deleteFoodsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String foodsid : ids) {
			num += this.foodsService.deleteFoods(foodsid);
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

	@PostMapping(value = "updateFoods.action")
	public Map<String, Object> updateFoods(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Foods foods = this.foodsService.getFoodsById(obj.getString("foodsid"));
		foods.setFoodsname(obj.getString("foodsname"));
		foods.setImage(obj.getString("image"));
		foods.setCateid(obj.getString("cateid"));
		foods.setPrice(obj.getString("price"));
		foods.setRecommend(obj.getString("recommend"));
		foods.setSpecial(obj.getString("special"));
		foods.setContents(obj.getString("contents"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.foodsService.updateFoods(foods);
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

	@GetMapping(value = "getAllFoods.action")
	public List<Foods> getAllFoods() {
		return this.foodsService.getAllFoods();
	}

	@GetMapping(value = "getFoodsMap.action")
	public Map<String, Object> getFoodsMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Foods foods = new Foods();
		foods.setFoodsname(keywords);
		List<Foods> list = this.foodsService.getFoodsByLike(foods);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getFoodsByPage.action")
	public Map<String, Object> getFoodsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> list = this.foodsService.getAllFoods();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getFoods.action")
	public Map<String, Object> getFoods(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Foods foods = new Foods();
		foods.setFoodsname(keywords);
		List<Foods> list = this.foodsService.getFoodsByLike(foods);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerFoods.action")
	public Map<String, Object> getOwnerFoods(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Foods foods = new Foods();
		List<Foods> list = this.foodsService.getFoodsByLike(foods);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getFoodsById.action")
	public Foods getFoodsById(String id) {
		Foods foods = this.foodsService.getFoodsById(id);
		return foods;
	}

	// TODO Auto-generated method stub
}


