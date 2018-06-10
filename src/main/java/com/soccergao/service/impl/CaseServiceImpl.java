package com.soccergao.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccergao.bo.Case;
import com.soccergao.service.CaseService;

@Service
public class CaseServiceImpl implements CaseService {

	@Override
	public Page<Case> search(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Case get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Case c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Case c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
