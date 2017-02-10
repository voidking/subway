package com.voidking.util;

import java.util.Comparator;

import com.voidking.model.Order;

public class DateComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		Order order1 = (Order) o1;
		Order order2 = (Order) o2;
		// 降序排序
		int flag = order2.getCreateAt().compareTo(order1.getCreateAt());
		return flag;
	}

}
