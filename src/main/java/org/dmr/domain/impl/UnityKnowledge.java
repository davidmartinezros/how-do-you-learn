package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.UnityKnowledgeType;
import org.springframework.data.annotation.Id;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */

/**
 * <T> is a Generic Type
 * It can be any Object: String, Boolean, Number, Date, Object, null, undefined
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
	private List<UnityKnowledgeObject> relations;
    
    public UnityKnowledge() {
    	
    }

    // la creacio d'una unitat de coneixement es la relacio entre un concepte i una imatge
	public UnityKnowledge(T concept, byte[] image) {
    
		//definicio unitat de coneixement
        this.concept = concept;
        this.image = image;
        
        // info complementaria
        this.relations = new ArrayList<UnityKnowledgeObject>();
        this.tags = new ArrayList<String>();
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

	public List<UnityKnowledgeObject> getRelations() {
		return relations;
	}

	public void setRelations(List<UnityKnowledgeObject> relations) {
		this.relations = relations;
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
    public void addRelation(UnityKnowledgeObject unity) {
        
    	if(!this.relations.contains(unity)) {
    		
    		this.relations.add(unity);
    		
    	}
    
    }
    
    @Override
    public void removeRelation(UnityKnowledgeObject unity) {
        
    	if(this.relations.contains(unity)) {
    		
//    		BasicDBObject match = new BasicDBObject("id", this.id);
//    		
//    		BasicDBObject update = new BasicDBObject("id", unity.getId());
    		
    		this.relations.remove(unity);
    		
    	}
    
    }
    
}
