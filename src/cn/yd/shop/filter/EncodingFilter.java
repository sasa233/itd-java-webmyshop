package cn.yd.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * Filter是单例类，且在项目启动时就会实例化，主动过滤
 * 可实现对请求相同的过滤处理，经典应用场景即是用户的登录
 * */
@WebFilter(urlPatterns="/*") //此处无需写工程名；路径过滤或后缀名过滤 
public class EncodingFilter implements Filter {

	// 缺省有一个构造方法
	public EncodingFilter() {
		System.out.println("EncodingFilter......");
	}
	
	@Override
	public void destroy() {

	}

	@Override // 复合条件的请求会进入doF方法中ilter
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter......");
		//所有请求进入后可配置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 如果有下一个过滤器则跳转，否则就跳转到目标页面
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
