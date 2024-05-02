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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.entity.Admin;
import com.service.AdminService;

@RestController
@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
@CrossOrigin
public class LoginController extends BaseController {

	@Autowired
	private AdminService adminService;

	@PostMapping(value = "login.action")
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Admin adminEntity = new Admin();
		adminEntity.setUsername(username);
		List<Admin> adminlist = this.adminService.getAdminByCond(adminEntity);
		if (adminlist.size() == 0) {
			map.put("success", false);
			map.put("message", "User name does not exist.");
		} else {
			Admin admin = adminlist.get(0);
			if (password.equals(admin.getPassword())) {
				map.put("success", true);
				map.put("message", "Login Success");
				map.put("adminid", admin.getAdminid());
				map.put("adminname", admin.getUsername());
				map.put("realname", admin.getRealname());
				map.put("role", "addmin");
			} else {
				map.put("success", false);
				map.put("message", "wrong password");
			}
		}
		return map;
	}

	@GetMapping("exit.action")
	public Map<String, Object> exit() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

}










