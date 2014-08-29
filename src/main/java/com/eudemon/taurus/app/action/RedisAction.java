package com.eudemon.taurus.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eudemon.taurus.app.cache.redis.RedisTemplate;
import com.eudemon.taurus.app.common.Log;

@Controller
@RequestMapping(value = "/redis")
public class RedisAction {
	@Autowired
	@Qualifier("redisTemplateDefaultImpl")
	private RedisTemplate redisTemplate;
	
	@RequestMapping(value = "view")
	public String view(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Log.getTraceLogger().trace("RedisAction:view:begin, trace");
		Log.getDebugLogger().debug("RedisAction:view:begin");
		return "redis/admin";
	}
	
	@RequestMapping(value = "get")
	public String get(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		byte[] rs = null;
		String key = "";
		try {
			key = request.getParameter("key");
			Log.getDebugLogger().trace("RedisActon:get:key=" + key);
			
			rs = redisTemplate.get(key.getBytes());
		} catch (Exception e) {
			Log.getErrorLogger().error("get byte array from redis fail, key:" + key, e);
		}
		
		
		Log.getDebugLogger().trace("RedisActon:get:key=" + key + ", rs=" + rs);
		model.addAttribute("rs", rs);
		return "redis/admin";
	}
}
