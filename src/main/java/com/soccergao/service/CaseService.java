package com.soccergao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.soccergao.bo.Case;

public interface CaseService {
	Page<Case> search(Pageable pageable);

	Case get(Long id);
	
	boolean add(Case c);
	
	boolean update(Case c);
	
	boolean delete(Long id);
}
