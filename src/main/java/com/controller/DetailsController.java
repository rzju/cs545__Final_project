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
import com.entity.Details;
import com.github.pagehelper.Page;
import com.service.DetailsService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/details", produces = "application/json; charset=utf-8")
@CrossOrigin
public class DetailsController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private DetailsService detailsService;

	@GetMapping(value = "createDetails.action")
	public Map<String, Object> createDetails() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dno", "D" + VeDate.getStringDatex());
		return map;
	}

	@PostMapping(value = "insertDetails.action")
	public Map<String, Object> insertDetails(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Details details = new Details();
		details.setOrdercode(obj.getString("ordercode"));
		details.setFoodsid(obj.getString("foodsid"));
		details.setPrice(obj.getString("price"));
		details.setNum(obj.getString("num"));
		int num = this.detailsService.insertDetails(details);
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

	@GetMapping(value = "deleteDetails.action")
	public Map<String, Object> deleteDetails(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.detailsService.deleteDetails(id);
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

	@PostMapping(value = "deleteDetailsByIds.action")
	public Map<String, Object> deleteDetailsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String detailsid : ids) {
			num += this.detailsService.deleteDetails(detailsid);
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

	@PostMapping(value = "updateDetails.action")
	public Map<String, Object> updateDetails(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Details details = this.detailsService.getDetailsById(obj.getString("detailsid"));
		details.setOrdercode(obj.getString("ordercode"));
		details.setFoodsid(obj.getString("foodsid"));
		details.setPrice(obj.getString("price"));
		details.setNum(obj.getString("num"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.detailsService.updateDetails(details);
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

	@GetMapping(value = "getAllDetails.action")
	public List<Details> getAllDetails() {
		return this.detailsService.getAllDetails();
	}

	@GetMapping(value = "getDetailsMap.action")
	public Map<String, Object> getDetailsMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Details details = new Details();
		details.setOrdercode(keywords);
		List<Details> list = this.detailsService.getDetailsByLike(details);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getDetailsByPage.action")
	public Map<String, Object> getDetailsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Details> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Details> list = this.detailsService.getAllDetails();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getDetails.action")
	public Map<String, Object> getDetails(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Details> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Details details = new Details();
		details.setOrdercode(keywords);
		List<Details> list = this.detailsService.getDetailsByLike(details);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerDetails.action")
	public Map<String, Object> getOwnerDetails(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Details> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Details details = new Details();
		List<Details> list = this.detailsService.getDetailsByLike(details);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getDetailsById.action")
	public Details getDetailsById(String id) {
		Details details = this.detailsService.getDetailsById(id);
		return details;
	}

	// TODO Auto-generated method stub
}


