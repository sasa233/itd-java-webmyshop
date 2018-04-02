package cn.yd.shop.service;

import cn.yd.shop.model.Users;

public interface UserService {

	Users login(String name, String pass) throws Exception;

}