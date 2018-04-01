package cn.yd.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.yd.shop.service.ProductServiceImpl;

/*
 * Filter是单例类，且在项目启动时就会实例化，主动过滤
 * 可实现对请求相同的过滤处理，经典应用场景即是用户的登录
 * 如果有多个Filter，则按照web.xml中按照配置的先后顺序先后进行过滤
 * 如果有多个Filter，且通过注释配置(@WebFilter)，过滤顺序则为过滤器名称的第一个字母的次序(ASCII编码)
 * 因此，调整Filter顺序，则可以给Filter名字加一个前缀，例如Filter0LoginFilter
 * */
@WebFilter(urlPatterns = "/admin/*") // 此处无需写工程名；路径过滤或后缀名过滤
public class LoginFilter implements Filter {
	
	
	private ProductServiceImpl productService = null;
	
	private ApplicationContext context = null;

	// 缺省有一个构造方法
	public LoginFilter() {
		System.out.println("LoginFilter......");
	}

	@Override
	public void destroy() {

	}

	@Override // 复合条件的请求会进入doF方法中ilter
	// ServletRequest是HttpServletRequest的子接口，虽然父接口只有这一个子接口，但是这样写具有可扩展性，我们可以自己写一个新协议对应的子接口
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter for login......");
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		productService = context.getBean("productService", ProductServiceImpl.class);
		System.out.println("2: productService-->" + productService);
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if(session.getAttribute("users") != null){
			chain.doFilter(request, response);
		}else{
			request.setAttribute("error", "请先登录！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
