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
import com.entity.Orders;
import com.github.pagehelper.Page;
import com.service.OrdersService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/orders", produces = "application/json; charset=utf-8")
@CrossOrigin
public class OrdersController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private OrdersService ordersService;

	@GetMapping(value = "createOrders.action")
	public Map<String, Object> createOrders() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("ono", "O" + VeDate.getStringDatex());
		return map;
	}

	@PostMapping(value = "insertOrders.action")
	public Map<String, Object> insertOrders(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Orders orders = new Orders();
		orders.setOrdercode(obj.getString("ordercode"));
		orders.setUsersid(obj.getString("usersid"));
		orders.setTotal(obj.getString("total"));
		orders.setStatus("");
		orders.setAddtime(VeDate.getStringDateShort());
		orders.setReceiver(obj.getString("receiver"));
		orders.setAddress(obj.getString("address"));
		orders.setContact(obj.getString("contact"));
		int num = this.ordersService.insertOrders(orders);
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

	@GetMapping(value = "deleteOrders.action")
	public Map<String, Object> deleteOrders(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.ordersService.deleteOrders(id);
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

	@PostMapping(value = "deleteOrdersByIds.action")
	public Map<String, Object> deleteOrdersByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String ordersid : ids) {
			num += this.ordersService.deleteOrders(ordersid);
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

	@PostMapping(value = "updateOrders.action")
	public Map<String, Object> updateOrders(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Orders orders = this.ordersService.getOrdersById(obj.getString("ordersid"));
		orders.setOrdercode(obj.getString("ordercode"));
		orders.setUsersid(obj.getString("usersid"));
		orders.setTotal(obj.getString("total"));
		orders.setReceiver(obj.getString("receiver"));
		orders.setAddress(obj.getString("address"));
		orders.setContact(obj.getString("contact"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.ordersService.updateOrders(orders);
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
		Orders orders = this.ordersService.getOrdersById(id);
		String status = "shipped";
		orders.setStatus(status);
		int num = this.ordersService.updateOrders(orders);
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

	@GetMapping(value = "getAllOrders.action")
	public List<Orders> getAllOrders() {
		return this.ordersService.getAllOrders();
	}

	@GetMapping(value = "getOrdersMap.action")
	public Map<String, Object> getOrdersMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = new Orders();
		orders.setOrdercode(keywords);
		List<Orders> list = this.ordersService.getOrdersByLike(orders);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getOrdersByPage.action")
	public Map<String, Object> getOrdersByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Orders> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Orders> list = this.ordersService.getAllOrders();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOrders.action")
	public Map<String, Object> getOrders(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Orders> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Orders orders = new Orders();
		orders.setOrdercode(keywords);
		List<Orders> list = this.ordersService.getOrdersByLike(orders);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getUserOrders.action")
	public Map<String, Object> getUserOrders(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Orders> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Orders orders = new Orders();
		orders.setUsersid(id);
		List<Orders> list = this.ordersService.getOrdersByLike(orders);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerOrders.action")
	public Map<String, Object> getOwnerOrders(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Orders> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Orders orders = new Orders();
		List<Orders> list = this.ordersService.getOrdersByLike(orders);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOrdersById.action")
	public Orders getOrdersById(String id) {
		Orders orders = this.ordersService.getOrdersById(id);
		return orders;
	}

	// TODO Auto-generated method stub
}
