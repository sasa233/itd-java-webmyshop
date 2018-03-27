package cn.yd.shop.servlet;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

// 由于此类继承了HttpServlet，它可接受Http请求，此类可与Dao交互

//@WebServlet(urlPatterns="/servlet/ProductServlet")
public class ProductServlet extends HttpServlet {

	// 此成员变量用于调用方法，无线程安全问题
	private ProductDaoImpl productDao = new ProductDaoImpl();

	/*
	 * Filter,Listener,Servlet称为web三大组件，他们都是单例模式
	 * Servlet第一个请求的时候创建，然后常驻内存，单例模式类不能有用来存储数据的成员变量，否则会出现线程安全问题
	 * 为解决此问题，使用了内置对象Session
	 * 
	 * 
	 */

	// Web：Servlet,Dao,Service等类的对象都是单例的，Model类是多例的
	// 因此，Servlet只有一个对象，该单例类的全局属性只有一份，当多个方法分别使用时，会出现安全问题-->线程安全
	// 非多线程
	// String keyword = null;

	/**
	 * Constructor of the object.
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void getById(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, Exception {
		System.out.println("User getById.");
		System.out.println(request);
		// 1、获取要删除的id
		String id = request.getParameter("id");
		System.out.println("----->" + id);
		// 2、获取需要更新的数据
		Product product = productDao.getByID(Integer.parseInt(id));
		// 3
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
		dispatcher.forward(request, response);

	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		// 获取前端数据，封装到product对象中
		// 从客户端发过来的请求被封装为request对象
		Product product = new Product();
		// getParameter("name") 与前端form表单的name属性对应
		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		System.out.println("get price----->" + request.getParameter("price"));
		product.setRemark(request.getParameter("remark"));
		System.out.println("get id----->" + request.getParameter("id"));
		product.setId(Integer.parseInt(request.getParameter("id")));
		productDao.update(product);
		// 返回给客户端响应 页面或JSON
		// sendRedirect页面重定向，web中访问资源都需要加工程名
		// Servlet到JSP不能共享request数据
		response.sendRedirect("/webmyshop/query.jsp");
	}

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取前端数据，封装到product对象中
		// 从客户端发过来的请求被封装为request对象
		System.out.println("request");
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(new BigDecimal(request.getParameter("price")));
		product.setRemark(request.getParameter("remark"));
		productDao.save(product);
		// 返回给客户端响应 页面或JSON
		// sendRedirect页面重定向，web中访问资源都需要加工程名
		// Servlet到JSP不能共享request数据
		response.sendRedirect("/webmyshop/query.jsp");
	}

	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session是服务器为每个用户分配的空间，在用户第一次访问服务器时会为用户创建，默认生命周期为30mins
		// 每个用户都有自己的查询关键字，应该存储到自己的Session中，浏览器关闭则Session丢失
		// 案例：电商网站的购物车信息不能放到Session中，因为时间间隔过场、数据量也过大，一般用cookie保存在本地，一旦禁用cookie，购物车信息丢失且无提示
		// Redis（高速缓存的内存型数据库）秒杀方案：数据库操作耗时，并发访问需要使用高速缓存数据库
		HttpSession session = request.getSession(); // 每个Session有sessionID
		System.out.println("session:" + session);
		String keyword = request.getParameter("keyword");
		session.setAttribute("keyword", keyword);
		// 2、调用Service业务逻辑
		List<Product> proList = productDao.queryByName(keyword);
		// 3、返回结果(json/jsp)
		// Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
		request.setAttribute("proList", proList);
		System.out.println(proList.size());
		// response.sendRedirect("/webmyshop/query.jsp");
		// 页面跳转只有两种：重定向（不能共享request数据）或者转发
		// forward:转发页面和转发到的页面可以共享request里面的数据.
		// redirect:不能共享数据.
		// 在转发时，只能转发系统内部页面，因为默认已经添加了工程名
		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		dispatcher.forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取要删除的id
		String id = request.getParameter("id");
		productDao.delete(new Integer(id));
		// 2、按照之前的查询关键字查询
		HttpSession session = request.getSession(); // 每个Session有sessionID
		System.out.println("session:" + session);
		String keyword = (String) session.getAttribute("keyword");
		List<Product> proList = productDao.queryByName(keyword);
		// 3、返回结果(json/jsp)
		// Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
		request.setAttribute("proList", proList);
		System.out.println(proList.size());
		// response.sendRedirect("/webmyshop/query.jsp");
		// 页面跳转只有两种：重定向（不能共享request数据）或者转发
		// forward:转发页面和转发到的页面可以共享request里面的数据.
		// redirect:不能共享数据.
		// 在转发时，只能转发系统内部页面，因为默认已经添加了工程名
		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 所有请求都在doPost中处理
		doPost(request, response);

		// // 1、获取前端数据
		// String keyword = request.getParameter("keyword");
		// // 2、调用Service业务逻辑
		// List<Product> proList = productDao.queryByName(keyword);
		// // 3、返回结果(json/jsp)
		// // Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
		// request.setAttribute("proList", proList);
		// System.out.println(proList.size());
		// // response.sendRedirect("/webmyshop/query.jsp");
		// // 页面跳转只有两种：重定向（不能共享request数据）或者转发
		// // forward:转发页面和转发到的页面可以共享request里面的数据.
		// // redirect:不能共享数据.
		// // 在转发时，只能转发系统内部页面，因为默认已经添加了工程名
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/query.jsp");
		// dispatcher.forward(request, response);
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01
		// Transitional//EN\">");
		// out.println("<HTML>");
		// out.println(" <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		// out.println(" <BODY>");
		// out.print(" This is ");
		// out.print(this.getClass());
		// out.println(", using the GET method");
		// out.println(" </BODY>");
		// out.println("</HTML>");
		// out.flush();
		// out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server（封装）
	 * @param response
	 *            the response send by the server to the client （封装）
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 配置request保存内容的格式
		// request.setCharacterEncoding("UTF-8"); //改为使用过滤器
		// POST方式的数据封装在表单中，Tomcat要解析地址
		// GET方式的数据放在了地址栏，Tomcat要解析地址，而Tomcat编码默认为8859-1，无法识别中文，需配置Tomcat编码为UTF-8
		// 找到server.xml，增加如下内容
		//    <Connector URIEncoding="UTF-8" 
		//      connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>

		
		// 通过获取type得到请求的类型
		String type = request.getParameter("type");
		// 通过反射获取当前Servlet中与type同名的方法
		Class clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(type, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
			// Method method = clazz.getDeclaredMethod(type);
			// method.invoke(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// // 获取前端数据，封装到product对象中
		// // 从客户端发过来的请求被封装为request对象
		// System.out.println("request");
		// Product product = new Product();
		// product.setName(request.getParameter("name"));
		// product.setPrice(new BigDecimal(request.getParameter("price")));
		// product.setRemark(request.getParameter("remark"));
		// productDao.save(product);
		// // 返回给客户端响应 页面或JSON
		// // sendRedirect页面重定向，web中访问资源都需要加工程名
		// // Servlet到JSP不能共享request数据
		// response.sendRedirect("/webmyshop/query.jsp");
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01
		// Transitional//EN\">");
		// out.println("<HTML>");
		// out.println(" <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		// out.println(" <BODY>");
		// out.print(" This is ");
		// out.print(this.getClass());
		// out.println(", using the POST method");
		// out.println(" </BODY>");
		// out.println("</HTML>");
		// out.flush();
		// out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
