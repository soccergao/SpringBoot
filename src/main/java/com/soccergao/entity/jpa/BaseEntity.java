package com.soccergao.entity.jpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TS")
	private LocalDateTime createdTs;
	
	@Column(name = "LASTUPDATED_BY")
	private String lastupdatedBy;
	
	@Column(name = "LASTUPDATED_TS")
	private LocalDateTime lastupdatedTs;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public String getLastupdatedBy() {
		return lastupdatedBy;
	}

	public void setLastupdatedBy(String lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}

	public LocalDateTime getLastupdatedTs() {
		return lastupdatedTs;
	}

	public void setLastupdatedTs(LocalDateTime lastupdatedTs) {
		this.lastupdatedTs = lastupdatedTs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((lastupdatedBy == null) ? 0 : lastupdatedBy.hashCode());
		result = prime * result + ((lastupdatedTs == null) ? 0 : lastupdatedTs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (lastupdatedBy == null) {
			if (other.lastupdatedBy != null)
				return false;
		} else if (!lastupdatedBy.equals(other.lastupdatedBy))
			return false;
		if (lastupdatedTs == null) {
			if (other.lastupdatedTs != null)
				return false;
		} else if (!lastupdatedTs.equals(other.lastupdatedTs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseEntity [createdBy=" + createdBy + ", createdTs=" + createdTs + ", lastupdatedBy=" + lastupdatedBy
				+ ", lastupdatedTs=" + lastupdatedTs + "]";
	}
}
