package cn.yd.shop.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.yd.shop.model.Product;
import cn.yd.shop.service.ProductServiceImpl;

// 监听器ServletContextListener：在项目启动时创建，为单例模式，主要用来实现数据的初始化，主动拦截
@WebListener // 监听器是不需要配置访问地址的
public class InitDataListener implements ServletContextListener {

	private ProductServiceImpl productService = new ProductServiceImpl();

	public InitDataListener() {
		System.out.println("InitDataListener.........");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("contextDestroyed.........");
		ServletContext application = event.getServletContext();
		System.out.println("contextDestroyed: " + application);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitialized.........");
		// 项目启动时，在此处可以加载公共性数据
		List<Product> proList = productService.queryByName("");
		// request每次请求一个 session每个用户一个 application每个Tomcat一个
		ServletContext application = event.getServletContext();
		System.out.println("contextInitialized: " + application);
		application.setAttribute("proList", proList);
	}

}
