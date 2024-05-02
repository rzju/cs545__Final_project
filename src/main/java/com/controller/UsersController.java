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
import com.entity.Users;
import com.github.pagehelper.Page;
import com.service.UsersService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")
@CrossOrigin
public class UsersController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private UsersService usersService;

	@PostMapping(value = "login.action")
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Users usersEntity = new Users();
		usersEntity.setUsername(username);
		List<Users> userslist = this.usersService.getUsersByCond(usersEntity);
		if (userslist.size() == 0) {
			map.put("success", false);
			map.put("message", "User name does not exist");
		} else {
			Users users = userslist.get(0);
			if (password.equals(users.getPassword())) {
				map.put("success", true);
				map.put("message", "success");
				map.put("userid", users.getUsersid());
				map.put("username", users.getUsername());
				map.put("realname", users.getRealname());
			} else {
				map.put("success", false);
				map.put("message", "wrong password");
			}
		}
		return map;
	}

	@PostMapping(value = "editpwd.action")
	public Map<String, Object> editpwd(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String userid = obj.getString("userid");
		String password = obj.getString("password");
		String repassword = obj.getString("repassword");
		int num = 0;
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			num = this.usersService.updateUsers(users);
			if (num > 0) {
				map.put("success", true);
				map.put("code", num);
				map.put("message", "success");
			} else {
				map.put("success", false);
				map.put("code", num);
				map.put("message", "fail");
			}
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "wrong password");
		}
		return map;
	}

	@GetMapping(value = "userinfo.action")
	public Map<String, Object> userinfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = this.usersService.getUsersById(id);
		map.put("users", users);
		return map;
	}

	@PostMapping(value = "personal.action")
	public Map<String, Object> personal(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Users users = this.usersService.getUsersById(obj.getString("usersid"));
		users.setUsername(obj.getString("username"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		int num = this.usersService.updateUsers(users);
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

	@GetMapping(value = "createUsers.action")
	public Map<String, Object> createUsers() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertUsers.action")
	public Map<String, Object> insertUsers(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Users users = new Users();
		users.setUsername(obj.getString("username"));
		users.setPassword(obj.getString("password"));
		users.setRealname(obj.getString("realname"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRegdate(VeDate.getStringDateShort());
		int num = this.usersService.insertUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "saved");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@GetMapping(value = "deleteUsers.action")
	public Map<String, Object> deleteUsers(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.usersService.deleteUsers(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "deleted");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@PostMapping(value = "deleteUsersByIds.action")
	public Map<String, Object> deleteUsersByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String usersid : ids) {
			num += this.usersService.deleteUsers(usersid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "deleted");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@PostMapping(value = "updateUsers.action")
	public Map<String, Object> updateUsers(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Users users = this.usersService.getUsersById(obj.getString("usersid"));
		users.setUsername(obj.getString("username"));
		users.setRealname(obj.getString("realname"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.usersService.updateUsers(users);
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

	@GetMapping(value = "getAllUsers.action")
	public List<Users> getAllUsers() {
		return this.usersService.getAllUsers();
	}

	@GetMapping(value = "getUsersMap.action")
	public Map<String, Object> getUsersMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = new Users();
		users.setUsername(keywords);
		List<Users> list = this.usersService.getUsersByLike(users);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getUsersByPage.action")
	public Map<String, Object> getUsersByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Users> list = this.usersService.getAllUsers();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getUsers.action")
	public Map<String, Object> getUsers(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Users users = new Users();
		users.setUsername(keywords);
		List<Users> list = this.usersService.getUsersByLike(users);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerUsers.action")
	public Map<String, Object> getOwnerUsers(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Users users = new Users();
		List<Users> list = this.usersService.getUsersByLike(users);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getUsersById.action")
	public Users getUsersById(String id) {
		Users users = this.usersService.getUsersById(id);
		return users;
	}

	// TODO Auto-generated method stub
}
