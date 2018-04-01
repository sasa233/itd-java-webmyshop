package cn.yd.shop.util;

import java.lang.reflect.Method;

interface MyInterface {
	public void save();
	public void update();
}


class ProxyObj implements MyInterface{
	
	private MyInterface target;
	
	public ProxyObj(MyInterface target){
		super();
		this.target = target;
	}

	@Override
	public void save() {
		System.out.println("开启事务.............");
		target.save();
		System.out.println("提交事务.............");		
	}

	@Override
	public void update() {
		System.out.println("开启事务.............");
		target.update();
		System.out.println("提交事务.............");
	}
}


public class TargetObj implements MyInterface{
	
	public static void main(String[] args){
		TargetObj targetObj = new TargetObj();
		// 创建代理对象（必须先创建目标对象）
		ProxyObj obj = new ProxyObj(targetObj);
		// 之后通过代理对象去操作（内部封装了目标对象）
		obj.save();
		obj.update();

		// 获取当前目标对象实现的接口
		Class clazz = TargetObj.class.getInterfaces()[0];
		// 获取当前接口的方法
		for (Method temp : clazz.getMethods()) {
			System.out.println(temp);
		}
	}
	
	public void save(){
		System.out.println("数据入库......");
	}

	public void update(){
		System.out.println("数据更新......");
	}
}
