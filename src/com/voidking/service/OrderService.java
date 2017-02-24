package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.xml.crypto.Data;

import com.voidking.model.Order;
import com.voidking.model.Sales;
import com.voidking.model.User;
import com.voidking.util.ConnectMysql;
import com.voidking.util.DateComparator;

public class OrderService {
	private Connection conn = null;
	private UserService userService = null;	
	public OrderService() {
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
		userService = new UserService();
	}
	
	// 查询单个用户的订单
	public ArrayList<Order> findOrdersByuserId(int userId){
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			String sql = "select * from sub_order where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setInt(1, userId); 
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				User user = userService.findById(id);
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				String username = user.getUsername();
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				int re_price = rs.getInt("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				Order order = new Order(id, order_number, user_id, username,one_site, two_site, price, re_price, create_at, state);
				result.add(order);
			}
			
			pstmt.close();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 查询所有订单
	public ArrayList<Order> orderList(){
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			String sql = "select * from sub_order where deleted = 0";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				User user = userService.findById(user_id);
				String username = user.getUsername();
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				double re_price = rs.getDouble("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				Order order = new Order(id, order_number, user_id, username, one_site, two_site, price, re_price, create_at, state);
				result.add(order);
			}
			
			pstmt.close();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Order creatOrder(String orderNumber, int userId, String oneSite,String twoSite, int price, String state){
		try {
			String sql = "insert into sub_order(order_number,user_id,one_site,two_site,price,create_at,state) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, orderNumber);
			pstmt.setInt(2, userId);
			pstmt.setString(3, oneSite);
			pstmt.setString(4, twoSite);
			pstmt.setInt(5, price);
			pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
			pstmt.setString(7, state);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int newId = -1;
			if(rs.next()){
				newId = rs.getInt(1);
	            pstmt.close();
	            
	    		Order resultOrder = this.findById(newId);
	    		return resultOrder;
			}else{
				pstmt.close();
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteOrderById(int id){
		try {
			String sql;
			sql = "update sub_order set deleted = 1 where id=?";
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
	
	public boolean deleteOrdersByIds(int ids[]){
		boolean flag = true;
		for(int i=0;i<ids.length;i++){
			if(!this.deleteOrderById(ids[i])){
				flag = false;
			}
		}
		return flag;
	}
	
	public boolean deleteOrderByuserId(int userId){
		try {
			String sql;
			sql = "update sub_order set deleted = 1 where user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			// STEP 5: Extract data from result set

			if (rs >= 0) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Order findById(int id) {
		try {
			String sql = "select * from sub_order where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setInt(1, id); 
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			Order resultOrder = null;
			if(rs.next()){
				int order_id = rs.getInt("id");
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				int re_price = rs.getInt("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				resultOrder = new Order(order_id, order_number, user_id, one_site, two_site, price, re_price, create_at, state);
			}	
			return resultOrder;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public boolean updateState(int id,String state){
		try {
			String sql;
			sql = "update sub_order set state=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state);
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
	
	public boolean updateRePrice(int id, double rePrice){
		try {
			String sql;
			sql = "update sub_order set re_price=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, rePrice);
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
	
	public ArrayList<Order> findOrderList(String key){
		ArrayList<Order> result = new ArrayList<Order>();
		try {
			String sql;
			sql = "select * from sub_order where deleted = 0 and (order_number like ? or one_site like ? or two_site like ? or state like ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%" );
			pstmt.setString(2, "%" + key + "%" );
			pstmt.setString(3, "%" + key + "%" );
			pstmt.setString(4, "%" + key + "%" );
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				User user = userService.findById(user_id);
				String username = user.getUsername();
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				double re_price = rs.getDouble("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				Order order = new Order(id, order_number, user_id, username, one_site, two_site, price, re_price, create_at, state);
				result.add(order);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public ArrayList<Order> findOrderList(Date date){
		ArrayList<Order> result = new ArrayList<Order>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String sql;
			sql = "select * from sub_order where deleted = 0 and create_at >= ? and create_at <= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, df.format(date));
			pstmt.setString(2, df.format(date) + " 24:00:00");
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				User user = userService.findById(user_id);
				String username = user.getUsername();
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				double re_price = rs.getDouble("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				Order order = new Order(id, order_number, user_id, username, one_site, two_site, price, re_price, create_at, state);
				result.add(order);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public ArrayList<Date> dateList(){
		ArrayList<Date> result = new ArrayList<Date>();
		try {
			String sql;
			sql = "select distinct DATE_FORMAT(create_at,'%Y-%m-%d') from sub_order order by create_at desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Date date = rs.getDate(1);
				result.add(date);
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public ArrayList<Sales> salesList(ArrayList<Date> dateList){
		ArrayList<Sales> result = new ArrayList<Sales>();
		try {
			for (int i = 0; i < dateList.size(); i++) {
				String sql;
				sql = "select * from sub_order where deleted = 0 and create_at >= ? and create_at <= ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setDate(1, (java.sql.Date) dateList.get(i));
				pstmt.setString(2, dateList.get(i).toString() + " 24:00:00");
				// 获取查询的结果集            
				ResultSet rs = pstmt.executeQuery();
				
				int sell = 0;
				int ret = 0;
				while(rs.next()){
					sell += 1;
					if(rs.getDouble("re_price") != 0){
						ret += 1;
					}
				}
				Sales sales = new Sales(sell, ret, dateList.get(i));
				result.add(sales);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
}
