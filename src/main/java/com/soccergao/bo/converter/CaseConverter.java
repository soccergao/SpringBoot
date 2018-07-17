package com.soccergao.bo.converter;

import org.springframework.util.Assert;

import com.soccergao.bo.Case;
import com.soccergao.entity.jpa.CaseEntity;

public class CaseConverter {
	public static CaseEntity toCaseEntity(Case c) {
		Assert.notNull(c, "");
		
		CaseEntity entity = new CaseEntity();
		entity.setCaseId(c.getCaseId());
		entity.setCaseName(c.getCaseName());
		return entity;
	}
	
	public static Case toCase(CaseEntity entity) {
		Assert.notNull(entity, "");
		
		Case c = new Case();
		c.setCaseId(entity.getCaseId());
		c.setCaseName(entity.getCaseName());
		return c;
	}
}
