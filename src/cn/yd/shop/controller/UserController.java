package cn.yd.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yd.shop.model.Users;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{
	
	@RequestMapping("/login")
	public String login(String name, String pass) throws Exception{
		Users users = userService.login(name, pass);
		System.out.println(users);
		if(users != null){
			// 后台首页可以通过读取session获取登录用户的信息       
			request.getSession().setAttribute("users", users);
//			Cookie[] cookies = request.getCookies();
//			response.addCookie(cookies[0]);
//			request.getSession().removeAttribute("");
			System.out.println("登录成功！");
			// 3: 返回结果(jsp/json)
			// 若登录成功，则重定向到后台首页，数据存储在session中
//			response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			return "redirect:/admin/index.jsp";
		}else{
			request.setAttribute("error", "登录失败！");
			// 3: 返回结果(jsp/json)
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return "forward:/login.jsp";
		}
	}

}
