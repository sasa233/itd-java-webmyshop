package cn.yd.shop.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.yd.shop.model.Product;

public class ProductDaoImplTest {
	
	private static ProductDaoImpl daoImpl = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试方法前执行，一般用来初始化测试对象。");
		daoImpl = new ProductDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("在所有测试方法后执行，用来销毁资源。");
		daoImpl = null;  //java对象会自动被垃圾回收
	}

	@Test
	public void testQueryByName() {
		ArrayList<Product> proList = daoImpl.queryByName("");
		for (Product temp : proList) {
			System.out.println(temp.toString());
		}
	}
	
	@Test
	public void testQueryByName1() throws Exception {
		ArrayList<Product> proList = daoImpl.queryByName("笔记", 1, 3);
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetByID() throws Exception {
		System.out.println("......");
		Product product = daoImpl.getByID(7);
		System.out.println(product);
	}

	@Test
	public void testSave() {
		Product product = new Product();
		product.setName("sasa加油");
		product.setRemark("Dream");
		product.setPrice(new BigDecimal(999999.99));
		daoImpl.save(product);
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setName("黄明昊");
		product.setRemark("Heart");
		product.setPrice(new BigDecimal(999999.99));
		product.setId(15);
		daoImpl.update(product);
	}

	@Test
	public void testDelete() {
		daoImpl.delete(4);
	}

}
