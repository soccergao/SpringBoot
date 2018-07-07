package com.soccergao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.soccergao.bo.Case;
import com.soccergao.rest.service.RestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class TestRest {
	private final static Logger logger = LoggerFactory.getLogger(TestRest.class); 

	@Autowired
	private RestService restService;

	@Test
	public void testGet() {
		String url = "http://localhost:8080/case/{id}";
		Case resp = restService.get(url, Case.class, 2);
		logger.info("method:Get");
		logger.info("url:" + url);
		logger.info("resp: " + resp);
		Assert.assertNotNull(resp);
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
