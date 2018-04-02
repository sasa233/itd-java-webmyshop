package cn.yd.shop.service;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yd.shop.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-bean.xml")
public class ProductServiceImplTest {

//	private static ApplicationContext context = null;
	
	@Resource(name="productService")  // 查找spring-bean.xml中id为productService的Bean，得到这个类的实例化对象
	private  ProductService productService;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//productService = new ProductServiceImpl();
//		context = new ClassPathXmlApplicationContext("spring-bean.xml");
//		productService = context.getBean("productService", ProductServiceImpl.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//静态方法不能操作非静态属性
		//productService = null;
	}

	@Test
	public void testQueryByNameStringIntInt() throws Exception {
		List<Product> proList = productService.queryByName("西服", 1, 3);
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testQueryByNameString() {
		List<Product> proList = productService.queryByName("");
		for (Product temp : proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetByID() throws Exception {
		System.out.println(productService.getById(5));
	}

	@Test
	public void testSave() {
		Product product = new Product();
		product.setName("test");
		product.setPrice(new BigDecimal(99));;
		productService.save(product);
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
