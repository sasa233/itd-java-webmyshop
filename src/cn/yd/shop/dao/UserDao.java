package cn.yd.shop.dao;

import cn.yd.shop.model.Users;

public interface UserDao {

	Users login(String name, String pass) throws Exception;

}