package com.voidking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.voidking.util.GetPage;

/**
 * Servlet implementation class GetPrice
 */
@WebServlet("/GetPrice")
public class GetPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GetPage getPage = new GetPage();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPrice() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonObj = null;
		
		String oneSite = request.getParameter("oneSite");
		String twoSite = request.getParameter("twoSite");
		
		String url = "http://www.njmetro.com.cn/PageAjax/SelectTickPrice.ashx?oneSite="+oneSite+"&twoSite="+twoSite;
		String price = getPage.getString(url);
		
		jsonObj = new JSONObject("{'code':'0','ext':'success'}");
		jsonObj.put("price", price);
		
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
