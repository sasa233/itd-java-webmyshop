package cn.yd.shop.service;

import cn.yd.shop.dao.UserDaoImpl;
import cn.yd.shop.model.Users;

public class UserServiceImpl {
	
	private UserDaoImpl userDao =  new UserDaoImpl();
	
	public Users login(String name, String pass) throws Exception{
		return userDao.login(name, pass);
	}

}