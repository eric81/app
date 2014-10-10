package com.eudemon.taurus.app.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eudemon.taurus.app.common.Config;
import com.eudemon.taurus.app.common.Log;

@Controller
@RequestMapping(value = "/config")
public class ConfigAction {

	@RequestMapping(value = "info")
	public void get(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Log.getDebugLogger().debug("ConfigAction-get-begin");
		List<String> infoList = Config.info();
		for(String info : infoList){
			response.getWriter().println(info);
			response.getWriter().println("<br>");
		}
	}
}
