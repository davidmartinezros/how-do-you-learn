package org.dmr.domain.impl;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Robot {
	
	// id de la base de dades mongoDB
	@Id
	private String id;
	// nom del robot
	private String name;
	// edat del robot
	private Integer age;
	// professio del robot
	private String profession;
	// descripcio / caracteristiques del robot
	private String description;
	// unitats de coneixement del robot
	private List<UnityKnowledgeObject> unities;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UnityKnowledgeObject> getUnities() {
		return unities;
	}

	public void setUnities(List<UnityKnowledgeObject> unities) {
		this.unities = unities;
	}
	
}
