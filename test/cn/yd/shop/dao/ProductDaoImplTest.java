package cn.yd.shop.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yd.shop.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-bean.xml")
public class ProductDaoImplTest {
	
	@Resource(name="productDao")
	private  ProductDao daoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试方法前执行，一般用来初始化测试对象。");
//		daoImpl = new ProductDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("在所有测试方法后执行，用来销毁资源。");
//		daoImpl = null;  //java对象会自动被垃圾回收
	}

	@Test
	public void testQueryByName() {
		List<Product> proList = daoImpl.queryByName("");
		for (Product temp : proList) {
			System.out.println(temp.toString());
		}
	}
	
	@Test
	public void testQueryByName1() throws Exception {
		List<Product> proList = daoImpl.queryByName("笔记", 1, 3);
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetByID() throws Exception {
		System.out.println("......");
		Product product = daoImpl.getById(5);
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
