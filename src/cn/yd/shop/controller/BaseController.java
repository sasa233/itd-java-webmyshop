package cn.yd.shop.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.yd.shop.service.ProductService;
import cn.yd.shop.service.UserService;

public class BaseController {
	// MVC会自动注入request session application内置对象，此时request session已做处理-装饰模式，无需担心线程安全
	@Resource
	protected HttpServletRequest request; // 按类型注入
	
	@Resource
	protected HttpSession session; // 按类型注入
	
	@Resource
	protected ServletContext application; // 按类型注入
	
	@Resource(name="productService")
	protected ProductService productService = null;
	
	@Resource(name="userService")
	protected UserService userService = null;
}
