package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.voidking.model.Order;
import com.voidking.util.ConnectMysql;
import com.voidking.util.DateComparator;

public class OrderService {
	private Connection conn = null;
	
	public OrderService() {
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public ArrayList<Order> orderList(int userId){
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
				String order_number = rs.getString("order_number");
				int user_id = rs.getInt("user_id");
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int price = rs.getInt("price");
				int re_price = rs.getInt("re_price");
				Timestamp create_at = rs.getTimestamp("create_at");
				String state = rs.getString("state");
				
				Order order = new Order(id, order_number, user_id, one_site, two_site, price, re_price, create_at, state);
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
	
	public boolean updateState(int id,int userId, String state){
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
	
	public static void main(String[] args) {
		OrderService service = new OrderService();
		ArrayList<Order> orderList = service.orderList(1);
		DateComparator comparator = new DateComparator();
		Collections.sort(orderList,comparator);
		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sf.format(order.getCreateAt()));
			//System.out.println(order.getCreatAt());
			//System.out.println(order.getOneSite());
			//String orderNumber = new MyRandom().getRandomString(10);
			//order.setOrderNumber(orderNumber);
			//service.creatOrder(order);
//			if(order.getId() == 14){
//				service.updateState(14, 1, "测试");
//			}
		}
		
		Order order = service.findById(17);
		System.out.println(order.getOneSite());
	}
}
