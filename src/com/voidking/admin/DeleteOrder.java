package com.voidking.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.voidking.model.User;
import com.voidking.service.OrderService;
import com.voidking.service.UserService;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrder() {
        super();
        orderService = new OrderService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		boolean flag = orderService.deleteOrderById(orderId);

		if(flag){			
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
			jsonObj = new JSONObject("{'code':'1','ext':'删除失败'}");
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
