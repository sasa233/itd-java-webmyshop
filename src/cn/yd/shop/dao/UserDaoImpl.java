package cn.yd.shop.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.yd.shop.model.Users;

//public class UserDaoImpl extends BaseDaoImpl<Users> {

public class UserDaoImpl {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Users login(String name, String pass) throws Exception{
		
		String sql = "select * from users where name = ? and pass = ?";
		try {
			Users users = jdbcTemplate.queryForObject(sql, new Object[]{name, pass}, new BeanPropertyRowMapper<Users> (Users.class));
			return users;
		} catch (Exception e) {
			return null;
		}
		//List<Users> uList = super.queryByName(sql, new Object[]{name, pass}, Users.class);
		//return uList.size() == 1 ? uList.get(0) : null;
	}
	
}
