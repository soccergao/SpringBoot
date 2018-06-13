package com.soccergao.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccergao.entity.CaseEntity;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {

}
