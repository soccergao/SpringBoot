package com.soccergao.bo.converter;

import org.springframework.core.convert.converter.Converter;

import com.soccergao.bo.Case;
import com.soccergao.entity.CaseEntity;

public class CaseConverter {
	public static Converter<? super CaseEntity, ? extends Case> convert() {
		return CaseConverter::toCase;
	}
	
	public static Case caseEntityToCase(CaseEntity entity) {
		return toCase(entity);
	}
	
	public static CaseEntity caseToCaseEntity (Case c) {
		return toCaseEntity(c);
	}
	
	private static CaseEntity toCaseEntity(Case c) {
		CaseEntity entity = new CaseEntity();
		entity.setCaseId(c.getCaseId());
		entity.setCaseName(c.getCaseName());
		return entity;
	}
	
	private static Case toCase(CaseEntity entity) {
		Case c = new Case();
		c.setCaseId(entity.getCaseId());
		c.setCaseName(entity.getCaseName());
		return c;
	}
}
