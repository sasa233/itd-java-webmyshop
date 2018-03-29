package cn.yd.shop.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yd.shop.dao.ProductDaoImpl;
import cn.yd.shop.model.Product;

public class MainTest {

	// 通过反射获取class文件的所有信息，java → JVM(.class)
	public static void main(String[] args) {
		// 加载spring的配置文件，默认情况下为饿汉模式（模式包括饿汉模式和懒汉模式）
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-bean.xml");
		//通过id获取相应的class文件，从而创建当前类的对象
		//System.out.println(context.getBean("product"));
		//System.out.println(context.getBean("product"));
		// 只能通过Spring的方式创建,采用使用IOC AOP的功能
		ProductDaoImpl daoImpl = context.getBean("productDao",ProductDaoImpl.class);
		daoImpl.delete(3);
	}

//	public static void main(String[] args)
//			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
//			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		// 采用普通的方式赋值和采用反射的方法赋值
//		Product product = new Product();
//		product.setId(12);
//		System.out.println(product.getId());
//		// 通过反射的方式进行赋值
//		Class clazz = Class.forName("cn.yd.shop.model.Product");
//		// 根据Product.class文件创建一个Product对象
//		Object t = clazz.newInstance(); // cmd + 2 + R
//		// 在Product.class中获取setID方法
//		Method setId = clazz.getMethod("setId", Integer.class);
//		setId.invoke(t, 15); // product.setId(12);
//		Method getId = clazz.getMethod("getId");
//		System.out.println(getId.invoke(t));
//
//		Field name = clazz.getDeclaredField("name");
//		name.setAccessible(true); // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
//		name.set(t, "反射");
//		System.out.println(name.get(t));
//	}

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
