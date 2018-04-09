package cn.yd.shop.listener;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.yd.shop.model.Product;
import cn.yd.shop.service.ProductService;
import cn.yd.shop.service.impl.ProductServiceImpl;

// 监听器ServletContextListener：在项目启动时创建，为单例模式，主要用来实现数据的初始化，主动拦截
//@WebListener // 监听器是不需要配置访问地址的，此处已配置于spring-bean.xml文件中
public class InitDataListener implements ServletContextListener {

	private ProductService productService = null;
	
	private ApplicationContext context = null;

//	// 默认时，项目加载后，组件启动优先级为Listener > Filter > sprint-*.xml文件加载，此时productService对象无法生成
//	// 因此如下注解方式不可行
//	@Resource
//	private ProductServiceImpl productService = null;
	
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
		// spring配置文件只能配置一次，项目启动时应加载spring配置文件且存放到Application对象中，若多次写如下代码，实际上会多次加载spring配置文件
		// 导致相应的对象被创建多次，单例模式失效，实际为多例，如下方式也不可行
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
//		context.getBean("productService",ProductServiceImpl.class);
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
//		productService = context.getBean("productService", ProductServiceImpl.class);
		// 面向接口编程，层之间
		productService = context.getBean("productService", ProductService.class);
		System.out.println("1: productService-->" + productService);
//		List<Product> proList = productService.queryByName("");
//		// request每次请求一个 session每个用户一个 application每个Tomcat一个
//		ServletContext application = event.getServletContext();
//		System.out.println("contextInitialized: " + application);
//		application.setAttribute("proList", proList);
	}

}
