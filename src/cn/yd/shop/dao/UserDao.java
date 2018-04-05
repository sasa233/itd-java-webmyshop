package cn.yd.shop.dao;

import org.apache.ibatis.annotations.Param;

import cn.yd.shop.model.Users;

public interface UserDao {

	Users login(@Param("name") String name, @Param("pass") String pass) throws Exception;

}