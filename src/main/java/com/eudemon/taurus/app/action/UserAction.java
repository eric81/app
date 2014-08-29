package com.eudemon.taurus.app.action;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eudemon.taurus.app.common.Log;
import com.eudemon.taurus.app.entity.User;
import com.eudemon.taurus.app.service.UserService;
import com.eudemon.taurus.app.util.RequestUtils;

/**
 * 用户管理Action
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction {
	private static int pageSize = 8;
	@Autowired
	private MessageSource message;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "user/login";
	}
	
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("name") String name) {
		if (userService.findUserByName(name) == null) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register() {
		return "user/register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user, RedirectAttributes redirectAttributes) {
		userService.registerUser(user);
		redirectAttributes.addFlashAttribute("username", user.getName());
		return "redirect:/user/login";
	}

	@RequestMapping(value = "listView", method = RequestMethod.GET)
	public String listView(Model model) {
		List<User> users = userService.getAllUser();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		// 获取分页条件
		int page = RequestUtils.getParameterAsInt(request, "page", 1);
		int rows = RequestUtils.getParameterAsInt(request, "rows", 1);
		// 获取排序字段和类型
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		Log.getDebugLogger().trace("<user list> page:" + page + ", rows:" + rows + ", sort:" + sort + ", order:" + order);

		int start = pageSize * (page - 1);
		int end = pageSize * page;

		int count = userService.getAllUser().size();
		List<User> users = userService.getUserList(start, end);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", users);

		return map;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();
		
		Locale locale = Locale.ENGLISH;
		redirectAttributes.addFlashAttribute("message",
				message.getMessage("user.delete.success", new Object[] { id }, locale));
		
		boolean rs = this.userService.delete(id);
		String msg = "";
		if(rs){
			msg = message.getMessage("user.delete.success", new Object[] { id }, locale);
		}else{
			msg = message.getMessage("user.delete.fail", new Object[] { id }, locale);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", rs);
		map.put("msg",msg);
		return map;
	}
}
