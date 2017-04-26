package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
	public String id;
	// concepte que estable el coneixement
    T concept;
    // Necessito fer un mecanisme que extregui informacio d'una imatge, per associar-la a unitat de coneixement
    // La imatge associada al concepte estable el coneixement
    byte[] image;
    // conclusions que fa la mente humana al llarg del temps
    String description;
    // Criteris pels que pots buscar la unitat de coneixement
    List<String> tags;
    // Relacions amb altres unitats de coneixement
    List<UnityKnowledgeType<T>> relations;
    
    public UnityKnowledge() {
    	
    }

    // la creacio d'una unitat de coneixement es la relacio entre un concepte i una imatge
	public UnityKnowledge(T concept, byte[] image) {
    
		//definicio unitat de coneixement
        this.concept = concept;
        this.image = image;
        
        // info complementaria
        this.relations = new ArrayList<UnityKnowledgeType<T>>();
        this.tags = new ArrayList<String>();
        this.description = "";
    
	}
	
	//creacio d'unitat de coneixement amb descripcio
	public UnityKnowledge(T concept, String description, byte[] image) {
		
		this(concept, image);
		this.description = description;
	
	}
	
	@Override
    public String getValue() {
		
        return this.toString();
    
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

	public List<UnityKnowledgeType<T>> getRelations() {
		return relations;
	}

	public void setRelations(List<UnityKnowledgeType<T>> relations) {
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

        this.tags.add(tag);
    
    }
    
    @Override
    public void createRelation(UnityKnowledgeType<T> unity) {
        
        this.relations.add(unity);

        this.addTag(unity.getConcept().toString());
    
    }
    
    @Override
	public String toString() {
		return "UnityKnowledge [concept=" + concept + ", image=" + Arrays.toString(image) + ", description="
				+ description + ", tags=" + tags + ", relations=" + relations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((relations == null) ? 0 : relations.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		UnityKnowledge other = (UnityKnowledge) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (relations == null) {
			if (other.relations != null)
				return false;
		} else if (!relations.equals(other.relations))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
    
}
