package cn.yd.shop.util;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yd.shop.dao.impl.ProductDaoImpl;
import cn.yd.shop.model.Product;
import cn.yd.shop.service.impl.ProductServiceImpl;

// 了解注解的基本语法
//@WebServlet(urlPatterns="/hmh")
public class MainTest {

	// 增加VM参数: ‐XX:+TraceClassLoading
	public static void main(String[] args) throws Exception {
		// System.out.println(args[0] + "," + args[1]);
		// demo01();
		// demo02();
		demo03();
	}

	// 检测当前的堆空间大小
	// 可动态配置 -Xms128M -Xmx256M
	public static void demo01() {
		// Runtime是当前程序的实例
		System.out.println("当前项目的内存最大值：" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "MB");
		System.out.println("当前项目可用的初始值：" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "MB");
		System.out.println("当前项目空闲的内存：" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "MB");
	}

	// 测试堆空间的自动扩张 -Xms16M -Xmx32M
	// 设置新生代和老年代大小 年轻代---> -Xmn   -Xms64M -Xmx128M -Xmn32M
	public static void demo02() throws Exception {
		System.out.println("当前项目的内存最大值：" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "MB");
		System.out.println("当前项目可用的初始值：" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "MB");
		List<Object> objList = new ArrayList<Object>();
		for (int i = 0; i < 30; i++) {
			objList.add(new byte[1024 * 1024 * 1]); // 添加1M的数组队形
			System.out.println("i: " + i + "----->" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "MB");
			System.out.println("i: " + i + "----->" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "MB");
			Thread.sleep(500);
		}
	}

	// 栈是每个线程独享空间，可用通过递归来测试栈空间，存储数据量随程序开启关闭而波动，若均值持续增加意味着部分内存没有回收
	private static int count = 0;
	public void test2(int x, double y, long z) {
		// 普通方法中，this占一个字word的空间；静态方法中没有this
	}
	public static void test(int x, double y, long z) {
		int a = x;
		double b = y;
		long c = z;
		count++;
		test(a, b, c);
	}
	// -Xms16M -Xmx32M -Xss1M
	public static void demo03() {
		try {
			test(100, 3.14, 1000);
		} catch (Throwable e) { // Exception有父类异常Throwable，对应严重的异常
			System.out.println("count: " + count);
		}
	}

	// 方法区：存放类，项目启动存放数据量迅速增加，但之后基本维持不变
	// -XX:PermSize=64M -­XX:MaxPermSize=128M
	
	
	
	
	/*
	 * @interface + 注解名 三种注解： 1：元注解-用来定义注解 Target 2：系统注解-系统自带的 Override 3：自定义注解
	 * 
	 * @Target(ElementType.METHOD) 当前注解可以存储的位置，此处为METHOD
	 * 
	 * @Retention(RetentionPolicy.SOURCE)
	 * 当前注解的生命周期：SOURCE-源码里，CLASS-转换为Class文件时，RUNTIME-加载到Java虚拟机中
	 */

	public static void annoDemo(String[] args) {
		// Main方法执行时就是运行时
		WebServlet annotation = MainTest.class.getAnnotation(WebServlet.class);
		if (annotation != null) {
			System.out.println(annotation.urlPatterns()[0]);
		}
	}

	// @Override
	// public String toString() {
	// return "MainTest [toString()=" + super.toString() + "]";
	// }

	// 通过反射获取class文件的所有信息，java → JVM(.class)
	public static void springXmlTest(String[] args) {
		// 加载spring的配置文件，默认情况下为饿汉模式（模式包括饿汉模式和懒汉模式）
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
		// 通过id获取相应的class文件，从而创建当前类的对象
		// System.out.println(context.getBean("product"));
		// System.out.println(context.getBean("product"));
		// 只能通过Spring的方式创建使用IOC AOP的功能
		// ProductDaoImpl daoImpl =
		// context.getBean("productDao",ProductDaoImpl.class);
		// daoImpl.delete(3);
		ProductServiceImpl serviceImpl = context.getBean("productService", ProductServiceImpl.class);
		for (Product temp : serviceImpl.queryByName("")) {
			System.out.println(temp);
		}
	}

	public static void refDemo2(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// 采用普通的方式赋值和采用反射的方法赋值
		Product product = new Product();
		product.setId(12);
		System.out.println(product.getId());
		// 通过反射的方式进行赋值
		Class clazz = Class.forName("cn.yd.shop.model.Product");
		// 根据Product.class文件创建一个Product对象
		Object t = clazz.newInstance(); // cmd + 2 + R
		// 在Product.class中获取setID方法
		Method setId = clazz.getMethod("setId", Integer.class);
		setId.invoke(t, 15); // product.setId(12);
		Method getId = clazz.getMethod("getId");
		System.out.println(getId.invoke(t));

		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true); // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
		name.set(t, "反射");
		System.out.println(name.get(t));
	}

	public static void refDemo(String[] args) throws ClassNotFoundException {
		// file对象可以代表：txt/jpg/class等文件类型
		// 类的每个对象就是一个一个的类文件，每个对象的类型都是class类型
		File file = new File("/Users/sasa/Desktop/ITD/Java/demo-java/Hello Java!.pdf");
		file.delete();

		// 获取class文件的三种方法
		Class clazz1 = Product.class;
		// 通过对象获取当前Class文件的类型
		Class clazz2 = new Product().getClass();
		// 通过类全名获取Product.class文件
		Class clazz3 = Class.forName("cn.yd.shop.model.Product"); // 不涉及资源的销毁，可直接抛出异常
		// 第三种最灵活好用？？？
		if ((clazz1 == clazz2) && (clazz2 == clazz3)) {
			// 说明三个引用类型指向相同的对象
			System.out.println("True");
		}

		// 上面已获Product.class文件/对象，可通过Class提供的方法获取对象里的属性和方法
		// getMethods只能获取当前类和父类定义的public方法
		Method[] methods = clazz3.getMethods(); // 获取对象中的所有方法
		for (Method temp : methods) {
			// method是一个对象，本身有自己的属性和方法
			// System.out.println(temp.getName());
			System.out.println(temp.toString());
		}

		System.out.println("==========VS===========");

		// getDeclaredMethods只能获取当前类的所有方法，包括public、private、protected等方法
		methods = clazz3.getDeclaredMethods();
		for (Method temp : methods) {
			System.out.println(temp.toString());
		}

		// 通过反射机制，获取当前Product.class文件的属性
		// getFields 只能获取当前类和父类定义的public属性
		// 类的属性一般是private的，这样需使用间接访问的方式使用属性，便于控制
		Field[] fields = clazz3.getFields();
		for (Field temp : fields) {
			System.out.println("----->" + temp);
		}

		// getDeclaredFields只能获取当前类的所有属性，包括public、private、protected等属性
		fields = clazz3.getDeclaredFields();
		for (Field temp : fields) {
			System.out.println("----->" + temp);
		}
	}

}
