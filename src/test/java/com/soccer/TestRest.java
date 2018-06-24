package com.soccer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.soccergao.Application;
import com.soccergao.bo.Case;
import com.soccergao.rest.service.RestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class TestRest {

	@Autowired
	private RestService restService;

	@Test
	public void testGet() {
		try {
			String url1 = "https://www.zhihu.com/api/v4/search_v3?t=general&q=java&correction=1&offset=5&limit=10&search_hash_id=0a0159f4b8374794bc37e562d4c4f498";
			String resp1 = restService.get(url1, String.class);
			String url2 = "http://localhost:8080/case/{id}";
			Case resp2 = restService.get(url2, Case.class, 2);
			System.out.println("method:Get");
			System.out.println("url:" + url1);
			System.out.println("resp: " + resp1);
			System.out.println("url:" + url2);
			System.out.println("resp: " + resp2);
			Assert.assertNotNull(resp1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSearch() {
		String url = "http://localhost:8080/case?page={page}&size={size}";
		Page<Case> resp = restService.search(url, Case.class, 0, 10);
		System.out.println("method:search");
		System.out.println("url:" + url);
		System.out.println("resp:" + resp.getContent());
		Assert.assertNotNull(resp.getContent());
		Assert.assertEquals(resp.getSize(), 10);
		Assert.assertEquals(resp.getNumber(), 0);
	}

	@Test
	public void testPost() {
		String url = "http://localhost:8080/case";
		Case c = new Case();
		c.setCaseName("case one");
		c = restService.post(url, c, Case.class);
		System.out.println("method:add");
		System.out.println("url:" + url);
		System.out.println(c);
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getCaseId());
	}
	 
}
