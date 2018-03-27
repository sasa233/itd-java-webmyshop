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

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

// 由于此类继承了HttpServlet，它可接受Http请求，此类可与Dao交互
//@WebServlet(urlPatterns = "/servlet/ProductServlet")
public class ProductServlet extends HttpServlet {

	private ProductDaoImpl productDao = new ProductDaoImpl();
	String keyword = null;

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

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取前端数据
		keyword = request.getParameter("keyword");
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

	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		keyword = request.getParameter("keyword");
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

//		// 1、获取前端数据
//		String keyword = request.getParameter("keyword");
//		// 2、调用Service业务逻辑
//		List<Product> proList = productDao.queryByName(keyword);
//		// 3、返回结果(json/jsp)
//		// Servlet到JSP如何传递数据；JSP提供内置对象：request, session, application
//		request.setAttribute("proList", proList);
//		System.out.println(proList.size());
//		// response.sendRedirect("/webmyshop/query.jsp");
//		// 页面跳转只有两种：重定向（不能共享request数据）或者转发
//		// forward:转发页面和转发到的页面可以共享request里面的数据.
//		// redirect:不能共享数据.
//		// 在转发时，只能转发系统内部页面，因为默认已经添加了工程名
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
//		dispatcher.forward(request, response);
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

		// 通过获取type得到请求的类型
		String type = request.getParameter("type");
		// 通过反射获取当前Servlet中与type同名的方法
		Class clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(type, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

//		// 获取前端数据，封装到product对象中
//		// 从客户端发过来的请求被封装为request对象
//		System.out.println("request");
//		Product product = new Product();
//		product.setName(request.getParameter("name"));
//		product.setPrice(new BigDecimal(request.getParameter("price")));
//		product.setRemark(request.getParameter("remark"));
//		productDao.save(product);
//		// 返回给客户端响应 页面或JSON
//		// sendRedirect页面重定向，web中访问资源都需要加工程名
//		// Servlet到JSP不能共享request数据
//		response.sendRedirect("/webmyshop/query.jsp");
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
