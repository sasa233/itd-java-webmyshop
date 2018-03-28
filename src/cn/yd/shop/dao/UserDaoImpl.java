package cn.yd.shop.dao;

import java.util.List;

import cn.yd.shop.model.Users;

public class UserDaoImpl extends BaseDaoImpl<Users> {

	public Users login(String name, String pass) throws Exception{
		
		String sql = "select * from users where name = ? and pass = ?";
		List<Users> uList = super.queryByName(sql, new Object[]{name, pass}, Users.class);
		return uList.size() == 1 ? uList.get(0) : null;
	}
	
}
