package com.soccergao.entity.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SO_CASE")
public class CaseEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CASE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long caseId;
	
	@Column(name = "CASE_NAME")
	private String caseName;
	
	@OneToMany(mappedBy = "caseEntity")
	private Set<ProductEntity> productEntity;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Set<ProductEntity> getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(Set<ProductEntity> productEntity) {
		this.productEntity = productEntity;
	}

	@Override
	public String toString() {
		return "CaseEntity [caseId=" + caseId + ", caseName=" + caseName + ", productEntity=" + productEntity + "]";
	}
}
