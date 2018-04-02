package cn.yd.shop.service;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yd.shop.model.Users;
import cn.yd.shop.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	private static ApplicationContext context = null;
	
	private static UserService userService = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//直接通过JVM new的方式创建对象,不具备IOC依赖注入的功能
		//usersService = new UserServiceImpl();
		context = new ClassPathXmlApplicationContext("spring-bean.xml");
		userService = context.getBean("userService", UserServiceImpl.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userService = null;
	}

	@Test
	public void testLogin() throws Exception {
		String name = "admin";
		String pass = "***123";
		System.out.println(userService.login(name, pass));
//		Users users = usersService.login(name, pass);
//		System.out.println(users);
	}

}
