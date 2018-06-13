package com.soccergao.bo.converter;

import org.springframework.core.convert.converter.Converter;

import com.soccergao.bo.Case;
import com.soccergao.entity.CaseEntity;

public class CaseConverter {
	public static Converter<? super CaseEntity, ? extends Case> convert() {
		return CaseConverter::toCase;
	}
	
	public static Case toCase(CaseEntity caseEntity) {
		Case c = new Case();
		c.setCaseId(caseEntity.getCaseId());
		c.setCaseName(caseEntity.getCaseName());
		return c;
	}
	
	public static CaseEntity toCaseEntity(Case c) {
		CaseEntity entity = new CaseEntity();
		entity.setCaseId(c.getCaseId());
		entity.setCaseName(c.getCaseName());
		return entity;
	}
}
