package com.voidking.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.model.Order;
import com.voidking.model.User;
import com.voidking.service.OrderService;
import com.voidking.service.UserService;

/**
 * Servlet implementation class SearchUsers
 */
@WebServlet("/SearchOrders")
public class SearchOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOrders() {
        super();
        orderService = new OrderService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String key = request.getParameter("key");
		ArrayList<Order> orderList = orderService.findOrderList(key);
			
		jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		jsonObj.put("orderList", orderList);
		
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
