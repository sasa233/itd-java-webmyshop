package cn.yd.shop.model;

public class Student {
	
	private int age;
	
	private static String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	//静态方法
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Student.name = name;
	}


//	public void setAge(int age) {
		//永远指向当前对象
		//System.out.println(this);
//		this.age = age;
//	}

	
}
