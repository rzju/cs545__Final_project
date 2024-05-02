package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller 基类
 * 其他Controller继承此类的同时也继承了此类里面的各个方法 
 * 省掉了重新定义 实例化的麻烦
 */
@Controller
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());

	public Map<String, String[]> getParameters() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest().getParameterMap();
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = this.getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

}


















































	// 本类作者QQ : 四五五五七零九七零  提供程序有偿修改服务 本类制作日期 ：2022-04-06
