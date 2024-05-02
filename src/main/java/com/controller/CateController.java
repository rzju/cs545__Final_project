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
import com.entity.Cate;
import com.github.pagehelper.Page;
import com.service.CateService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/cate", produces = "application/json; charset=utf-8")
@CrossOrigin
public class CateController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private CateService cateService;

	@GetMapping(value = "createCate.action")
	public Map<String, Object> createCate() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertCate.action")
	public Map<String, Object> insertCate(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Cate cate = new Cate();
		cate.setCatename(obj.getString("catename"));
		cate.setAddtime(VeDate.getStringDateShort());
		cate.setMemo(obj.getString("memo"));
		int num = this.cateService.insertCate(cate);
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

	@GetMapping(value = "deleteCate.action")
	public Map<String, Object> deleteCate(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.cateService.deleteCate(id);
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

	@PostMapping(value = "deleteCateByIds.action")
	public Map<String, Object> deleteCateByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String cateid : ids) {
			num += this.cateService.deleteCate(cateid);
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

	@PostMapping(value = "updateCate.action")
	public Map<String, Object> updateCate(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Cate cate = this.cateService.getCateById(obj.getString("cateid"));
		cate.setCatename(obj.getString("catename"));
		cate.setMemo(obj.getString("memo"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.cateService.updateCate(cate);
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

	@GetMapping(value = "getAllCate.action")
	public List<Cate> getAllCate() {
		return this.cateService.getAllCate();
	}

	@GetMapping(value = "getCateMap.action")
	public Map<String, Object> getCateMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cate cate = new Cate();
		cate.setCatename(keywords);
		List<Cate> list = this.cateService.getCateByLike(cate);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getCateByPage.action")
	public Map<String, Object> getCateByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Cate> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Cate> list = this.cateService.getAllCate();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getCate.action")
	public Map<String, Object> getCate(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Cate> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Cate cate = new Cate();
		cate.setCatename(keywords);
		List<Cate> list = this.cateService.getCateByLike(cate);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerCate.action")
	public Map<String, Object> getOwnerCate(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Cate> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Cate cate = new Cate();
		List<Cate> list = this.cateService.getCateByLike(cate);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getCateById.action")
	public Cate getCateById(String id) {
		Cate cate = this.cateService.getCateById(id);
		return cate;
	}

	// TODO Auto-generated method stub
}


