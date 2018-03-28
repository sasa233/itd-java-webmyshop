package cn.yd.shop.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.yd.shop.model.Product;

public class ProductServiceImplTest {

	private static ProductServiceImpl productService = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productService = new ProductServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		productService = null;
	}

	@Test
	public void testQueryByNameStringIntInt() throws Exception {
		ArrayList<Product> proList = productService.queryByName("西服", 1, 3);
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testQueryByNameString() {
		ArrayList<Product> proList = productService.queryByName("");
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
