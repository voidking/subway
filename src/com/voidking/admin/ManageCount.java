package com.voidking.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voidking.model.Admin;
import com.voidking.model.Order;
import com.voidking.model.Sales;
import com.voidking.service.OrderService;
import com.voidking.util.DateComparator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class Order
 */
@WebServlet("/ManageCount")
public class ManageCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCount() {
        super();
        // TODO Auto-generated constructor stub
        orderService = new OrderService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		
		if(admin == null){
			//System.out.println("重定向");
			response.sendRedirect(request.getContextPath() + "/Admin/LoginPage");
			return;
		}
		//freemarker配置  
        Configuration config=new Configuration();
        ServletContext context = request.getServletContext();
        config.setServletContextForTemplateLoading(context, "template");
        
        //加载模板文件  
        Template template=config.getTemplate("manage-count.ftl"); 
        
        //创建数据模型  
        Map<String,Object> map=new HashMap<String,Object>();  
        map.put("basePath", request.getContextPath());
        map.put("admin", admin);
        
        ArrayList<Date> dateList = orderService.dateList();
        ArrayList<Sales> salesList = orderService.salesList(dateList);
        map.put("salesList", salesList);
        
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        try {
            // 输出模板到页面上
            template.process(map, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
