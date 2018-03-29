package cn.yd.shop.service;

import java.util.List;

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

// JSP --> Servlet --> Service --> Dao --> JDBC --> DB
public class ProductServiceImpl {

	//private ProductDaoImpl productDao = new ProductDaoImpl();
	
	private ProductDaoImpl productDao = null;
	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}
    

	public List<Product> queryByName(String name, int page, int size) throws Exception {
		return productDao.queryByBame(name, page, size);
	}

	// java中集合不限大小
	// 如果没有给集合制定类型，则默认为object类型，可以指定泛型
	// 数组和集合的区别：数组限制大小、类型（需指定），集合不限大小、类型，泛型集合不限大小、限类型
	public List<Product> queryByName(String name) {
		return productDao.queryByBame(name);
	}

	// 通过id获取Product
	public Product getById(int id) throws Exception {
		return productDao.getById(id);
	}

	// 完成数据的插入操作
	public void save(Product product) {
		productDao.save(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}

	// public void delete(Product product) {
	// 方法参数超过1-2个，需要进行封装；只有1个的话，就直接传入——参数设计越简单越好
	public void delete(int id) {
		productDao.delete(id);
	}
}
