package com.soccergao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObject;
import com.soccergao.entity.mongo.CaseEntity;
import com.soccergao.repository.mongo.CaseMongoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class TestMongo {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CaseMongoRepository caseRepository;

	@Test
	public void testMongoTemplateNotNull() {
		Assert.assertNotNull(mongoTemplate);
	}

	@Test
	public void testSaveByMongoTemplate() {
		BasicDBObject obj = new BasicDBObject();
		obj.append("aa", "bb");
		mongoTemplate.save(obj, "test");
	}

	@Test
	public void testSave() {
		CaseEntity entity = new CaseEntity();
		entity.setName("mongo test");
		entity = caseRepository.save(entity);
		Assert.assertNotNull(entity.getId());
	}
}
