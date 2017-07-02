package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Document
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
	// estat
	@Transient
	private String state;
	// missatge
	@Transient
	private String message;
	@DBRef
	private List<UnityKnowledgeObject> unities;
	
	public Robot() {
		// inicialitzem el llistat de unitats
        this.unities = new ArrayList<UnityKnowledgeObject>();
    }

    // la creacio d'una unitat de coneixement es la relacio entre un concepte i una imatge
	public Robot(String name, Integer age, String profession, String description) {
		
		this();
		//definicio robot
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.description = description;
    
	}

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
	
    public void addUnity(UnityKnowledgeObject unity) {
        
    	if(!this.unities.contains(unity)) {
    		
    		this.unities.add(unity);
    		
    	}
    
    }
    
    public void removeUnity(UnityKnowledgeObject unity) {
        
    	if(this.unities.contains(unity)) {
    		
    		this.unities.remove(unity);
    		
    	}
    
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Robot other = (Robot) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
