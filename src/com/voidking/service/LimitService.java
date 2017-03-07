package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.voidking.model.Admin;
import com.voidking.model.Limit;
import com.voidking.model.Order;
import com.voidking.util.ConnectMysql;

public class LimitService {
	private Connection conn = null;
	
	public LimitService(){
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public boolean isStarted(){
		try {
			String sql;
			sql = "select * from sub_limit";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
						
			if(rs.first()){
				int started = rs.getInt("started");
				if(started == 0){
					return false;
				}else{
					return true;
				}
			}
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	public Limit getLimit(){
		try {
			String sql;
			sql = "select * from sub_limit";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			Limit limit = new Limit();
			if(rs.first()){
				limit.setId(rs.getInt("id"));
				limit.setOneSite(rs.getString("one_site"));
				limit.setTwoSite(rs.getString("two_site"));
				limit.setTotalTickets(rs.getInt("total_tickets"));
				limit.setSoldTickets(rs.getInt("sold_tickets"));
				limit.setStarted(rs.getInt("started"));
				pstmt.close();
				
				return limit;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public Limit creatLimit(String oneSite,String twoSite, int totalTickets, int soldTickets){
		try {
			String sql = "insert into sub_limit(one_site,two_site,total_tickets,sold_tickets) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, oneSite);
			pstmt.setString(2, twoSite);
			pstmt.setInt(3, totalTickets);
			pstmt.setInt(4, soldTickets);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int newId = -1;
			if(rs.next()){
				newId = rs.getInt(1);
	            pstmt.close();
	            
	    		Limit limit = this.findLimitById(newId);
	    		return limit;
			}else{
				pstmt.close();
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Limit findLimitById(int id){
		try {
			String sql = "select * from sub_limit where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设定参数
			pstmt.setInt(1, id); 
			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			Limit limit = null;
			if(rs.next()){
				int limit_id = rs.getInt("id");
				String one_site = rs.getString("one_site");
				String two_site = rs.getString("two_site");
				int total_tickets = rs.getInt("total_tickets");
				int sold_tickets = rs.getInt("sold_tickets");
				int started = rs.getInt("started");
				
				
				limit = new Limit(limit_id, one_site, two_site, total_tickets, sold_tickets, started);
			}	
			return limit;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public boolean updateLimit(int id,String oneSite, String twoSite, int totalTickets, int soldTickets, int started){
		try {
			String sql;
			sql = "update sub_limit set one_site=?,two_site=?,total_tickets=?,sold_tickets=?,started=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oneSite);
			pstmt.setString(2, twoSite);
			pstmt.setInt(3,totalTickets);
			pstmt.setInt(4, soldTickets);
			pstmt.setInt(5, started);
			pstmt.setInt(6, id);
			
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// sold_tickets加一
	public boolean soldOne(){
		try {
			Limit limit = this.getLimit();
			int id = limit.getId();
			int sold_tickets = limit.getSoldTickets();
			int total_tickets = limit.getTotalTickets();
			
			if(sold_tickets == total_tickets){
				return false;
			}
			
			String sql;
			sql = "update sub_limit set sold_tickets=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sold_tickets+1);
			pstmt.setInt(2, id);
			
			int rs = pstmt.executeUpdate();
			pstmt.close();
			
			if (rs == 1) {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
