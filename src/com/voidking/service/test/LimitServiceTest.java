package com.voidking.service.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.voidking.model.Limit;
import com.voidking.service.LimitService;

public class LimitServiceTest {
	private LimitService limitService = new LimitService();

	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void testLimitService() {
		fail("Not yet implemented"); // TODO
	}

	@Ignore
	public void testIsStarted() {
		System.out.println(limitService.isStarted());
	}

	@Ignore
	public void testGetLimit() {
		Limit limit = limitService.getLimit();
		System.out.println(limit.getId());
	}

	@Ignore
	public void testCreatLimit() {
		Limit limit = limitService.creatLimit("龙眠大道", "软件大道", 10, 0);
		System.out.println(limit.getId());
	}

	@Ignore
	public void testFindLimitById() {
		Limit limit = limitService.findLimitById(1);
		System.out.println(limit.getId());
	}
	
	@Ignore
	public void testUpdateLimit(){
		boolean flag = limitService.updateLimit(1, "软件大道", "龙眠大道", 5, 0,0);
		System.out.println(flag);
	}

	@Test
	public void testSoldOne(){
		System.out.println(limitService.soldOne());
	}
}
