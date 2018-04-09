package cn.yd.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yd.shop.model.Product;

public interface ProductDao {

	// 通过id获取制定的商品数据
	Product getById(int id);

	// 如果没有给集合指定类型,则默认就是object类型.可以指定泛型<Product>
	List<Product> queryByName(String name);

	List<Product> queryByName(String name, int page, int size);

	// 完成数据的插入操作 ctrl + shift + f
	void save(Product product);

	void update(Product product);

	void delete(int id);

}