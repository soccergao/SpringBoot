package com.soccergao.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
 		return page.map(CaseConverter::toCase);
	}

	@Override
	public Case get(Long id) {
		Optional<CaseEntity> optional = caseRepository.findById(id);
		CaseEntity entity = optional.orElseThrow(() -> new EntityNotFoundException(String.format("case(%s) not found", id)));
		return CaseConverter.toCase(entity);
	}

	@Override
	@Transactional
	public Case add(Case c) {
		CaseEntity entity = CaseConverter.toCaseEntity(c);
		entity = caseRepository.save(entity);
		return CaseConverter.toCase(entity);
	}

	@Override
	@Transactional
	public void update(Case c) {
		CaseEntity entity = CaseConverter.toCaseEntity(c);
		caseRepository.save(entity);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		caseRepository.deleteById(id);
	}

}
