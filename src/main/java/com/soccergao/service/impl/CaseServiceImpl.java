package com.soccergao.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccergao.bo.Case;
import com.soccergao.bo.converter.CaseConverter;
import com.soccergao.entity.jpa.CaseEntity;
import com.soccergao.repository.jpa.CaseRepository;
import com.soccergao.service.CaseService;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	private CaseRepository caseRepository;
	
	@Override
	public Page<Case> search(Pageable pageable) {
		Page<CaseEntity> page = caseRepository.findAll(pageable);
 		return page.map(CaseConverter.convert());
	}

	@Override
	public Case get(Long id) {
		CaseEntity entity = caseRepository.findOne(id);
		if (entity == null) {
			throw new EntityNotFoundException(String.format("case(id=%s) not found", id));
		}
		return CaseConverter.caseEntityToCase(entity);
	}

	@Override
	public Case add(Case c) {
		CaseEntity entity = CaseConverter.caseToCaseEntity(c);
		entity = caseRepository.save(entity);
		return CaseConverter.caseEntityToCase(entity);
	}

	@Override
	public void update(Case c) {
		CaseEntity entity = CaseConverter.caseToCaseEntity(c);
		caseRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		caseRepository.delete(id);
	}

}
