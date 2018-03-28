package cn.yd.shop.service;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.yd.shop.model.Users;

public class UserServiceImplTest {

	private static UserServiceImpl usersService = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usersService = new UserServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		usersService = null;
	}

	@Test
	public void testLogin() throws Exception {
		String name = "admin";
		String pass = "***123";
		Users users = usersService.login(name, pass);
		System.out.println(users);
	}

}
