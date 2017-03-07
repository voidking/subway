package com.voidking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.voidking.model.Order;
import com.voidking.model.Stop;
import com.voidking.model.User;
import com.voidking.util.ConnectMysql;

public class StopService {
private Connection conn = null;
	
	public StopService(){
		ConnectMysql connectMysql = new ConnectMysql();
		conn = connectMysql.getConnection();
	}
	
	public ArrayList<Stop> stopList(){
		ArrayList<Stop> result = new ArrayList<Stop>();
		try {
			String sql = "select * from sub_stop";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 获取查询的结果集            
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String stop_name = rs.getString("stop_name");
				String line_name = rs.getString("line_name");				
				int value = rs.getInt("value");
		
				Stop stop = new Stop(id, stop_name, line_name, value);
				result.add(stop);
			}
			
			pstmt.close();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
