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
import com.entity.Complains;
import com.github.pagehelper.Page;
import com.service.ComplainsService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/complains", produces = "application/json; charset=utf-8")
@CrossOrigin
public class ComplainsController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private ComplainsService complainsService;

	@GetMapping(value = "createComplains.action")
	public Map<String, Object> createComplains() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertComplains.action")
	public Map<String, Object> insertComplains(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Complains complains = new Complains();
		complains.setUsersid(obj.getString("usersid"));
		complains.setTitle("");
		complains.setContents(obj.getString("contents"));
		complains.setAddtime(VeDate.getStringDateShort());
		complains.setStatus("");
		complains.setReps(obj.getString("reps"));
		int num = this.complainsService.insertComplains(complains);
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

	@GetMapping(value = "deleteComplains.action")
	public Map<String, Object> deleteComplains(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.complainsService.deleteComplains(id);
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

	@PostMapping(value = "deleteComplainsByIds.action")
	public Map<String, Object> deleteComplainsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String complainsid : ids) {
			num += this.complainsService.deleteComplains(complainsid);
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

	@PostMapping(value = "updateComplains.action")
	public Map<String, Object> updateComplains(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Complains complains = this.complainsService.getComplainsById(obj.getString("complainsid"));
		complains.setContents(obj.getString("contents"));
		complains.setReps(obj.getString("reps"));
		complains.setStatus("responded");
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.complainsService.updateComplains(complains);
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

	@GetMapping(value = "status.action")
	public Map<String, Object> status(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = this.complainsService.getComplainsById(id);
		String status = "";
		if ("".equals(complains.getStatus())) {
			complains.setStatus(status);
		}
		int num = this.complainsService.updateComplains(complains);
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

	@GetMapping(value = "getAllComplains.action")
	public List<Complains> getAllComplains() {
		return this.complainsService.getAllComplains();
	}

	@GetMapping(value = "getComplainsMap.action")
	public Map<String, Object> getComplainsMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = new Complains();
		complains.setUsersid(keywords);
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getComplainsByPage.action")
	public Map<String, Object> getComplainsByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Complains> list = this.complainsService.getAllComplains();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getComplains.action")
	public Map<String, Object> getComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Complains complains = new Complains();
		complains.setUsersid(keywords);
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getUserComplains.action")
	public Map<String, Object> getUserComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Complains complains = new Complains();
		complains.setUsersid(id);
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerComplains.action")
	public Map<String, Object> getOwnerComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Complains complains = new Complains();
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getComplainsById.action")
	public Complains getComplainsById(String id) {
		Complains complains = this.complainsService.getComplainsById(id);
		return complains;
	}

	// TODO Auto-generated method stub
}
