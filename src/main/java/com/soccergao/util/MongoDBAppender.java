package com.soccergao.util;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
	@Override
	protected void append(ILoggingEvent eventObject) {
		MongoTemplate mongoTemplate = ApplicationContextProvider.getBean(MongoTemplate.class);
		System.out.println("mongoTemplate: " + mongoTemplate);
        if (mongoTemplate != null) {
            final BasicDBObject doc = new BasicDBObject();
            doc.append("level", eventObject.getLevel().toString());
            doc.append("logger", eventObject.getLoggerName());
            doc.append("thread", eventObject.getThreadName());
            doc.append("message", eventObject.getFormattedMessage());
            mongoTemplate.save(doc, "log");
        }
		
	}
	
	@Override
	public void start() {
		started = false;
	}
}
