package org.dmr.domain.impl;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Document
public class UnityKnowledgeObject extends UnityKnowledge<Object> {
	
	public UnityKnowledgeObject() {
		super();
	}
	
	public UnityKnowledgeObject(Object concept, byte[] image) {
		super(concept, image);
	}
	
	public UnityKnowledgeObject(Object concept, String description, byte[] image) {
		super(concept, description, image);
	}
	
}
