package cn.yd.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yd.shop.model.Users;
import cn.yd.shop.service.UserServiceImpl;

//@WebServlet(urlPatterns = "/servlet/UsersServlet")
public class UsersServlet extends HttpServlet {

	private UserServiceImpl usersService = new UserServiceImpl();

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1: 获取前端数据
		// request.setCharacterEncoding("UTF-8"); // 过滤器已经解决此问题
		// 2: 调用业务逻辑(如果登录成功则把登录信息存储到session中,否则重新调回login.jsp)
		try {
			Users users = usersService.login(request.getParameter("name"),
					request.getParameter("password"));
			if(users != null){
				// 后台首页可以通过读取session获取登录用户的信息       
				request.getSession().setAttribute("users", users);
				System.out.println("登录成功！");
				// 3: 返回结果(jsp/json)
				// 若登录成功，则重定向到后台首页，数据存储在session中
				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			}else{
				request.setAttribute("error", "登录失败！");
				// 3: 返回结果(jsp/json)
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
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
