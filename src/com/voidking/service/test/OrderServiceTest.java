package com.voidking.service.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.voidking.model.Order;
import com.voidking.model.Sales;
import com.voidking.service.OrderService;

/*
1. @Test : 测试方法，测试程序会运行的方法，后边可以跟参数代表不同的测试，如(expected=XXException.class) 异常测试，(timeout=xxx)超时测试
2. @Ignore : 被忽略的测试方法
3. @Before: 每一个测试方法之前运行
4. @After : 每一个测试方法之后运行
5. @BeforeClass: 所有测试开始之前运行
6. @AfterClass: 所有测试结束之后运行
fail方法是指测试失败
assertEquals测试2个参数是否相等
*/


public class OrderServiceTest {

	private OrderService orderService = new OrderService();
	
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void testOrderService() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDateList() {
		ArrayList<Date> dateList = orderService.dateList();
		for (int i=0;i<dateList.size();i++){
			System.out.println(dateList.get(i).toString());
		}
		assertNotEquals(dateList.size(), 0);
	}
	
	@Ignore
	public void testSalesList() {
		ArrayList<Date> dateList = orderService.dateList();
		ArrayList<Sales> salesList = orderService.salesList(dateList);
		for (int i = 0; i < salesList.size(); i++) {
			Sales sales = salesList.get(i);
			System.out.println(sales.getSell() +","+sales.getRet()+","+sales.getDate());
		}
		assertNotEquals(salesList.size(), 0);
	}
	
	@Ignore
	public void testFindOrderList(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse("2017-2-7");
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Order> orderList = orderService.findOrderList(date);
		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);
			System.out.println(order.getId());
		}
		assertNotEquals(orderList, 0);
	}


}
