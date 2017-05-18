package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.UnityKnowledgeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */

/**
 * T is a Generic Type that defines the <b>Concept Type of the UnityKnowledge</b>.<br/>
 * K is a Generic Type that defines the <b>Concept Type of the UnityKnowledge Relation</b>.<br/>
 * A Generic Type can be <b>Any Object</b>: String, Boolean, Number, Date, Object, null, undefined.
 */
public class UnityKnowledge<T> implements UnityKnowledgeType<T> {
	
    // id de la base de dades mongoDB
	@Id
	private String id;
	// concepte que estable el coneixement
	private T concept;
    // Necessito fer un mecanisme que extregui informacio d'una imatge, per associar-la a unitat de coneixement
    // La imatge associada al concepte estable el coneixement
	private byte[] image;
    // conclusions que fa la mente humana al llarg del temps
	private String description;
    // Criteris pels que pots buscar la unitat de coneixement
	private List<String> tags;
    // Relacions amb altres unitats de coneixement
	@DBRef
	private List<UnityKnowledgeObject> unities;
	
	@Autowired
    MongoTemplate mongoTemplate;
    
    public UnityKnowledge() {
    	//inicialitzem llistats
        this.unities = new ArrayList<UnityKnowledgeObject>();
        this.tags = new ArrayList<String>();
    }

    // la creacio d'una unitat de coneixement es la relacio entre un concepte i una imatge
	public UnityKnowledge(T concept, byte[] image) {
		
		this();
		//definicio unitat de coneixement
        this.concept = concept;
        this.image = image;
        
        //info complementaria
        this.description = "";
    
	}
	
	//creacio d'unitat de coneixement amb descripcio
	public UnityKnowledge(T concept, String description, byte[] image) {
		
		this(concept, image);
		this.description = description;
	
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<UnityKnowledgeObject> getUnities() {
		return unities;
	}

	public void setUnities(List<UnityKnowledgeObject> unities) {
		this.unities = unities;
	}
	
	@Override
	public T getConcept() {
		return concept;
	}

	public void setConcept(T concept) {
		this.concept = concept;
	}

	@Override
    public void modifyDescription(String description) {
        
        this.description = description;

    }
	
	@Override
	public void modifyImage(byte[] image) {
		
		this.image = image;
		
	}
    
    @Override
    public void addTag(String tag) {

    	if(!this.tags.contains(tag)) {
    		
    		this.tags.add(tag);
    		
    	}
    
    }
    
    @Override
    public void removeTag(String tag) {

    	if(this.tags.contains(tag)) {
    		
    		this.tags.remove(tag);
    		
    	}
    
    }
    
    @Override
    public void addUnity(UnityKnowledgeObject unity) {
        
    	if(!this.unities.contains(unity)) {
    		
    		this.unities.add(unity);
    		
    	}
    
    }
    
    @Override
    public void removeUnity(UnityKnowledgeObject unity) {
        
    	if(this.unities.contains(unity)) {
    		
    		this.unities.remove(unity);
    		
    	}
    
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnityKnowledge<T> other = (UnityKnowledge<T>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}
