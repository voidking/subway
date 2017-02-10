package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.voidking.model.Order;
import com.voidking.model.User;
import com.voidking.service.OrderService;
import com.voidking.util.MyRandom;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String oneSite = request.getParameter("oneSite");
		String twoSite = request.getParameter("twoSite");
		int price = Integer.parseInt(request.getParameter("price"));

		MyRandom myRandom = new MyRandom();
		String orderNumber = myRandom.getRandomString(10);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		Order order = orderService.creatOrder(orderNumber, user.getId(), oneSite, twoSite, price, "待取票");
		if(order != null){
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
			// 写入数据库失败
			jsonObj = new JSONObject("{'code':'1','ext':'未知错误'}");
		}

		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
