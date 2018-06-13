package com.soccergao.bo;

import java.io.Serializable;

public class Case implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long caseId;
	private String caseName;

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

	@Override
	public String toString() {
		return "Case [caseId=" + caseId + ", caseName=" + caseName + "]";
	}
}
