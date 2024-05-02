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
import com.entity.Admin;
import com.github.pagehelper.Page;
import com.service.AdminService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/admin", produces = "application/json; charset=utf-8")
@CrossOrigin
public class AdminController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private AdminService adminService;

	@PostMapping("editpwd.action")
	public Map<String, Object> editpwd(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String adminid = obj.getString("adminid");
		String password = obj.getString("password");
		String repassword = obj.getString("repassword");
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = this.adminService.getAdminById(adminid);
		if (password.equals(admin.getPassword())) {
			admin.setPassword(repassword);
			this.adminService.updateAdmin(admin);
			map.put("success", true);
			map.put("message", "edit success");
		} else {
			map.put("success", false);
			map.put("message", "edit fail");
		}
		return map;
	}

	@GetMapping(value = "createAdmin.action")
	public Map<String, Object> createAdmin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增管理员
	@PostMapping(value = "insertAdmin.action")
	public Map<String, Object> insertAdmin(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Admin admin = new Admin();
		admin.setUsername(obj.getString("username"));
		admin.setPassword(obj.getString("password"));
		admin.setRealname(obj.getString("realname"));
		admin.setContact(obj.getString("contact"));
		admin.setAddtime(VeDate.getStringDateShort());
		int num = this.adminService.insertAdmin(admin);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "save success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "save fail");
		}
		return map;
	}

	@GetMapping(value = "deleteAdmin.action")
	public Map<String, Object> deleteAdmin(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.adminService.deleteAdmin(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "delete success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "delete fail");
		}
		return map;
	}

	@PostMapping(value = "deleteAdminByIds.action")
	public Map<String, Object> deleteAdminByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String adminid : ids) {
			num += this.adminService.deleteAdmin(adminid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "delete success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "delete fail");
		}
		return map;
	}

	@PostMapping(value = "updateAdmin.action")
	public Map<String, Object> updateAdmin(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Admin admin = this.adminService.getAdminById(obj.getString("adminid"));
		admin.setUsername(obj.getString("username"));
		admin.setRealname(obj.getString("realname"));
		admin.setContact(obj.getString("contact"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.adminService.updateAdmin(admin);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "edit success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "edit fail");
		}
		return map;
	}

	@GetMapping(value = "getAllAdmin.action")
	public List<Admin> getAllAdmin() {
		return this.adminService.getAllAdmin();
	}

	@GetMapping(value = "getAdminMap.action")
	public Map<String, Object> getAdminMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = new Admin();
		admin.setUsername(keywords);
		List<Admin> list = this.adminService.getAdminByLike(admin);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getAdminByPage.action")
	public Map<String, Object> getAdminByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Admin> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Admin> list = this.adminService.getAllAdmin();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getAdmin.action")
	public Map<String, Object> getAdmin(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Admin> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Admin admin = new Admin();
		admin.setUsername(keywords);
		List<Admin> list = this.adminService.getAdminByLike(admin);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerAdmin.action")
	public Map<String, Object> getOwnerAdmin(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Admin> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Admin admin = new Admin();
		List<Admin> list = this.adminService.getAdminByLike(admin);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getAdminById.action")
	public Admin getAdminById(String id) {
		Admin admin = this.adminService.getAdminById(id);
		return admin;
	}

	// TODO Auto-generated method stub
}


