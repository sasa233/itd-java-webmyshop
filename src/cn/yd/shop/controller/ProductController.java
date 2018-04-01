package cn.yd.shop.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.yd.shop.model.Product;


// 无需继承HttpServlet，因此可以支持JUnit测试？？？？
@Controller //代表当前类是控制层，默认为单例模式
@RequestMapping(value="/productController")
//默认为饿汉模式，可配置为懒汉模式
public class ProductController extends BaseController{ //单例模式，项目启动时实例化，为后续被访问做准备，这样效率高
	
//	// MVC会自动注入request session application内置对象，此时request session已做处理-装饰模式，无需担心线程安全
//	@Resource
//	private HttpServletRequest request; // 按类型注入
//	
//	@Resource
//	private HttpSession session; // 按类型注入
//	
//	@Resource
//	private ServletContext application; // 按类型注入
	
	// ProductController项目启动时初始化，且单例模式
	public ProductController() {
		System.out.println("ProductController......");
		for(int i=0;i<=10;i++){
			System.out.println(UUID.randomUUID().toString());
		}
	}

	// 1：获取参数
	@RequestMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file){
	
		// 实现文件上传
		String path = request.getServletContext().getRealPath("/images/");
		// 上传文件名
		String filename = file.getOriginalFilename();
		File dest = new File(path, filename);
		try {
			file.transferTo(dest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		product.setPic(filename);
		
//		product.setName(request.getParameter("name"));
//		product.setPrice(new BigDecimal(request.getParameter("price")));
//		product.setRemark(request.getParameter("remark"));
		productService.save(product);
		// 返回给客户端响应 页面或JSON
		// sendRedirect页面重定向，web中访问资源都需要加工程名
		// Servlet到JSP不能共享request数据
//		response.sendRedirect("/webmyshop/query.jsp");
		return "redirect:/query.jsp";
		
//		// 2：调用逻辑
//		product.setName("update name");
//		System.out.println(request); //每人每次一个 Current HttpServletRequest
//		System.out.println(session); //每人一个 Current response
//		System.out.println(application); //项目唯一 org.apache.catalina.core.ApplicationContextFacade@d305b51
//		request.setAttribute("product", product);
//		session.setAttribute("product", product);
//		application.setAttribute("product", product);
//		// 3：返回结果（无论转发还是重定向都自动添加了工程名）
//		// return "redirect:/mvc/index.jsp";
//		return "redirect:/index.jsp";
			
	}
	
	@RequestMapping("/query")
	public String query(String keyword){
		
//		HttpSession session = request.getSession(); // 每个Session有sessionID
//		System.out.println("session:" + session);
//		String keyword = request.getParameter("keyword");
		session.setAttribute("keyword", keyword);
		// 2、调用Service业务逻辑
		List<Product> proList = productService.queryByName(keyword);
		// 3、返回结果(json/jsp)
		// Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
		request.setAttribute("proList", proList);
//		System.out.println(proList.size());
//		System.out.println(proList);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
//		dispatcher.forward(request, response);
		return "forward:/query.jsp";
		
		
//		System.out.println("query......");
//		// 3：返回结果（无论转发还是重定向都自动添加了工程名）
//		return "forward:/index.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id){
//		// 1、获取要删除的id
//		String id = request.getParameter("id");
		productService.delete(id);
//		// 2、按照之前的查询关键字查询
//		HttpSession session = request.getSession(); // 每个Session有sessionID
//		System.out.println("session:" + session);
		String keyword = (String) session.getAttribute("keyword");
		List<Product> proList = productService.queryByName(keyword);
		// 3、返回结果(json/jsp)
		// Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
		request.setAttribute("proList", proList);
//		System.out.println(proList.size());
//		// 在转发时，只能转发系统内部页面，因为默认已经添加了工程名
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
//		dispatcher.forward(request, response);
		return "forward:/query.jsp";
	}
	

	@RequestMapping("/getById")
	public String getById(Integer id) throws Exception{
//		System.out.println("User getById.");
//		System.out.println(request);
		// 1、获取要删除的id
//		String id = request.getParameter("id");
//		System.out.println("----->" + id);
		// 2、获取需要更新的数据
		Product product = productService.getById(id);
		// 3
		request.setAttribute("product", product);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
//		dispatcher.forward(request, response);
		return "forward:/update.jsp";
	}
	
	@RequestMapping("/update")
	public String update(Product product){
//		System.out.println(request);
//		// 获取前端数据，封装到product对象中
//		// 从客户端发过来的请求被封装为request对象
//		Product product = new Product();
//		// getParameter("name") 与前端form表单的name属性对应
//		product.setName(request.getParameter("name"));
//		product.setPrice(new BigDecimal(request.getParameter("price")));
//		System.out.println("get price----->" + request.getParameter("price"));
//		product.setRemark(request.getParameter("remark"));
//		System.out.println("get id----->" + request.getParameter("id"));
//		product.setId(Integer.parseInt(request.getParameter("id")));
		productService.update(product);
		// 返回给客户端响应 页面或JSON
		// sendRedirect页面重定向，web中访问资源都需要加工程名
		// Servlet到JSP不能共享request数据
//		response.sendRedirect("/webmyshop/query.jsp");
		return "redirect:/query.jsp";
	}
	
	
}
