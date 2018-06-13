package com.soccergao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccergao.bo.Case;
import com.soccergao.bo.converter.CaseConverter;
import com.soccergao.entity.CaseEntity;
import com.soccergao.repository.jpa.CaseRepository;
import com.soccergao.service.CaseService;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	CaseRepository caseRepository;
	
	@Override
	public Page<Case> search(Pageable pageable) {
		Page<CaseEntity> page = caseRepository.findAll(pageable);
 		return page.map(CaseConverter.convert());
	}

	@Override
	public Case get(Long id) {
		CaseEntity entity = caseRepository.findOne(id);
		return CaseConverter.toCase(entity);
	}

	@Override
	public void add(Case c) {
		CaseEntity entity = CaseConverter.toCaseEntity(c);
		caseRepository.save(entity);
	}

	@Override
	public void update(Case c) {
		CaseEntity entity = CaseConverter.toCaseEntity(c);
		caseRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		caseRepository.delete(id);
	}

}
