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
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = null;
    private OrderService orderService = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        userService = new UserService();
        orderService = new OrderService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		int userId = Integer.parseInt(request.getParameter("userId"));
		boolean flag1 = userService.deleteUser(userId);
		boolean flag2 = orderService.deleteOrderByuserId(userId);
		//System.out.println(flag1 + "" + flag2);
		if(flag1 && flag2){			
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
