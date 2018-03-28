package cn.yd.shop.model;

import java.io.Serializable;

public class Users implements Serializable{ //implements接口Serializable，以实现序列化和反序列化
	
	// 生成长整型随机数，在序列化与反序列化时，作为当前类的主键
	private static final long serialVersionUID = -3673424643028570304L;
	
	private Integer id;
	private String name;
	private String pass;
	
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", pass=" + pass + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
