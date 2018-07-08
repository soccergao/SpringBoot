package com.soccergao.entity.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "case")
public class CaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	// @Field(value = "name")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CaseEntity [id=" + id + ", name=" + name + "]";
	}
}
