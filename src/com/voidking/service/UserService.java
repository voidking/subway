package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.voidking.model.Order;
import com.voidking.model.User;
import com.voidking.util.ConnectMysql;

public class UserService {
	
	private Connection conn = null;
	
	public UserService() {
		// TODO Auto-generated constructor stub
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public ArrayList<User> findUserList(String key){
		ArrayList<User> result = new ArrayList<User>();
		try {
			String sql;
			sql = "select * from sub_user where deleted = 0 and (username like ? or email like ? or tel like ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%" );
			pstmt.setString(2, "%" + key + "%" );
			pstmt.setString(3, "%" + key + "%" );
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				
				User user = new User(id, username, password,email,tel);
				result.add(user);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public ArrayList<User> userList(){
		ArrayList<User> result = new ArrayList<User>();
		try {
			String sql;
			sql = "select * from sub_user where deleted = 0";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				
				User user = new User(id, username, password,email,tel);
				result.add(user);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public boolean checkExist(String username){
		try {
			String sql;
			sql = "select * from sub_user where username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setString(1, username ); 
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			boolean result = rs.first();
			pstmt.close();
			
			if(result){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(String username,String password){
		try {
			String sql;
			sql = "insert into sub_user(username,password) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User login(String username, String password){
		try {
			String sql;
			sql = "select * from sub_user where username=? and password=? and deleted = 0";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setString(1, username); 
			pstmt.setString(2, password);
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			
			User user = new User();
			if(rs.first()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				pstmt.close();
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public boolean deleteUser(int id){
		try {
			String sql;
			sql = "update sub_user set deleted = 1 where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePwd(int id, String newPwd){
		try {
			String sql;
			sql = "update sub_user set password=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, id);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateInfo(int id, String newEmail, String newTel){
		try {
			String sql;
			sql = "update sub_user set email=?,tel=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newEmail);
			pstmt.setString(2, newTel);
			pstmt.setInt(3, id);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User findById(int id) {
		try {
			String sql;
			sql = "select * from sub_user where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setInt(1, id); 
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			
			User user = new User();
			if(rs.first()){
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setTel(rs.getString("tel"));
				pstmt.close();
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void main(String[] args) {
		UserService service = new UserService();
//		service.register("huangbo", "huangbo");
//		User user = service.login("huangbo", "huangb");
//		if(user != null){
//			System.out.println(user.getId());
//		}else{
//			System.out.println("用户名或密码错误");
//		}
		service.updateInfo(1, "xiaopang@qq.com", "15100000000");
		
	}
}
