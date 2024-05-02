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
import com.entity.Topic;
import com.github.pagehelper.Page;
import com.service.TopicService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/topic", produces = "application/json; charset=utf-8")
@CrossOrigin
public class TopicController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired
	private TopicService topicService;

	@GetMapping(value = "createTopic.action")
	public Map<String, Object> createTopic() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	@PostMapping(value = "insertTopic.action")
	public Map<String, Object> insertTopic(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Topic topic = new Topic();
		topic.setOrdersid(obj.getString("ordersid"));
		topic.setUsersid(obj.getString("usersid"));
		topic.setFoodsid(obj.getString("foodsid"));
		topic.setNum(obj.getString("num"));
		topic.setContents(obj.getString("contents"));
		topic.setAddtime(VeDate.getStringDateShort());
		int num = this.topicService.insertTopic(topic);
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

	@GetMapping(value = "deleteTopic.action")
	public Map<String, Object> deleteTopic(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.topicService.deleteTopic(id);
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

	@PostMapping(value = "deleteTopicByIds.action")
	public Map<String, Object> deleteTopicByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String topicid : ids) {
			num += this.topicService.deleteTopic(topicid);
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

	@PostMapping(value = "updateTopic.action")
	public Map<String, Object> updateTopic(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Topic topic = this.topicService.getTopicById(obj.getString("topicid"));
		topic.setOrdersid(obj.getString("ordersid"));
		topic.setUsersid(obj.getString("usersid"));
		topic.setFoodsid(obj.getString("foodsid"));
		topic.setNum(obj.getString("num"));
		topic.setContents(obj.getString("contents"));

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.topicService.updateTopic(topic);
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

	@GetMapping(value = "getAllTopic.action")
	public List<Topic> getAllTopic() {
		return this.topicService.getAllTopic();
	}

	@GetMapping(value = "getTopicMap.action")
	public Map<String, Object> getTopicMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Topic topic = new Topic();
		topic.setOrdersid(keywords);
		List<Topic> list = this.topicService.getTopicByLike(topic);
		map.put("data", list);
		return map;
	}

	@GetMapping(value = "getTopicByPage.action")
	public Map<String, Object> getTopicByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Topic> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Topic> list = this.topicService.getAllTopic();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getTopic.action")
	public Map<String, Object> getTopic(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Topic> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Topic topic = new Topic();
		topic.setOrdersid(keywords);
		List<Topic> list = this.topicService.getTopicByLike(topic);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getUserTopic.action")
	public Map<String, Object> getUserTopic(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Topic> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Topic topic = new Topic();
		topic.setUsersid(id);
		List<Topic> list = this.topicService.getTopicByLike(topic);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getOwnerTopic.action")
	public Map<String, Object> getOwnerTopic(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Topic> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Topic topic = new Topic();
		List<Topic> list = this.topicService.getTopicByLike(topic);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "getTopicById.action")
	public Topic getTopicById(String id) {
		Topic topic = this.topicService.getTopicById(id);
		return topic;
	}

	// TODO Auto-generated method stub
}


