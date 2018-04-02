package cn.yd.shop.service.impl;

import cn.yd.shop.dao.UserDao;
import cn.yd.shop.model.Users;
import cn.yd.shop.service.UserService;

public class UserServiceImpl implements UserService {
	
	//private UserDaoImpl userDao =  new UserDaoImpl();
	private UserDao userDao = null;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/* (non-Javadoc)
	 * @see cn.yd.shop.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Users login(String name, String pass) throws Exception{
		return userDao.login(name, pass);
	}

}
