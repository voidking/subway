package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.service.UserService;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if("".equals(username) || "".equals(password)){
			jsonObj = new JSONObject("{'code':'1','ext':'用户名和密码不能为空'}");
		}else if(!password.equals(password2)){
			jsonObj = new JSONObject("{'code':'2','ext':'两次输入密码不一致'}");
		}else if(userService.checkExist(username)){
			jsonObj = new JSONObject("{'code':'3','ext':'用户名已存在'}");
		}else{
			boolean result = userService.register(username, password);
			if(result){
				jsonObj = new JSONObject("{'code':'0','ext':'success'}");
			}else{
				// 写入数据库失败
				jsonObj = new JSONObject("{'code':'4','ext':'未知错误'}");
			}
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
