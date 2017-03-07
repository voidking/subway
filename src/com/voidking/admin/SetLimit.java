package com.voidking.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.service.LimitService;

/**
 * Servlet implementation class SetLimit
 */
@WebServlet("/SetLimit")
public class SetLimit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LimitService limitService = new LimitService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetLimit() {
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
		int totalTickets = Integer.parseInt(request.getParameter("totalTickets"));
		int started = Integer.parseInt(request.getParameter("started"));
		
		boolean flag = limitService.updateLimit(1, oneSite, twoSite, totalTickets, 0, started);
		if(flag){
			jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		}else{
			jsonObj = new JSONObject("{'code':'1','ext':'设置失败，请检查输入！'}");
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
