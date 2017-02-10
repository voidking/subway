package com.voidking.servlet;

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
import com.voidking.service.UserService;

/**
 * Servlet implementation class UpdatePwd
 */
@WebServlet("/UpdatePwd")
public class UpdatePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = null;
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if("".equals(oldPwd)|| "".equals(newPwd) ){
			jsonObj = new JSONObject("{'code':'1','ext':'密码不能为空'}");
		}else if(!newPwd.equals(newPwd2)){
			jsonObj = new JSONObject("{'code':'2','ext':'两次输入密码不一致'}");
		}else if(!oldPwd.equals(user.getPassword())){
			jsonObj = new JSONObject("{'code':'3','ext':'当前密码错误'}");
		}else{
			boolean result = userService.updatePwd(user.getId(), newPwd);
			if(result){
				user.setPassword(newPwd);
				session.setAttribute("user", user);
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
