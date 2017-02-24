package com.voidking.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.model.Order;
import com.voidking.service.OrderService;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String state = request.getParameter("state");
		Double rePrice = Double.parseDouble(request.getParameter("rePrice"));
		
		
		boolean flag1 = orderService.updateState(id, state);
		Order order = orderService.findById(id);
		boolean flag2 = orderService.updateRePrice(id, rePrice);
		if(flag1 && flag2){
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
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
