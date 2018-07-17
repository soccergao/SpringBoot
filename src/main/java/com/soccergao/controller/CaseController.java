package com.soccergao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccergao.bo.Case;
import com.soccergao.service.CaseService;

@RestController
@RequestMapping(value = "case")
public class CaseController {

	@Autowired
	private CaseService caseService;

	@GetMapping
	public ResponseEntity<Page<Case>> search(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<Case> c = caseService.search(pageable);

		return ResponseEntity.ok(c);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Case> get(@PathVariable Long id) {
		Case c = caseService.get(id);

		return ResponseEntity.ok(c);
	}

	@PostMapping
	public ResponseEntity<Case> add(@RequestBody Case c) {
		c = caseService.add(c);

		return ResponseEntity.ok(c);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Case c) {
		caseService.update(c);

		return ResponseEntity.ok(null);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		caseService.delete(id);

		return ResponseEntity.ok(null);
	}
}
