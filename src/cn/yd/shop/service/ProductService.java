package cn.yd.shop.service;

import java.util.List;

import cn.yd.shop.model.Product;

public interface ProductService {

	List<Product> queryByName(String name, int page, int size) throws Exception;

	// java中集合不限大小
	// 如果没有给集合制定类型，则默认为object类型，可以指定泛型
	// 数组和集合的区别：数组限制大小、类型（需指定），集合不限大小、类型，泛型集合不限大小、限类型
	List<Product> queryByName(String name);

	// 通过id获取Product
	Product getById(int id) throws Exception;

	// 完成数据的插入操作
	void save(Product product);

	void update(Product product);

	// public void delete(Product product) {
	// 方法参数超过1-2个，需要进行封装；只有1个的话，就直接传入——参数设计越简单越好
	void delete(int id);

}