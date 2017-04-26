package org.dmr.domain.impl;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public class UnityKnowledgeString extends UnityKnowledge<String> {
	
	public UnityKnowledgeString() {
		super();
	}
	
	public UnityKnowledgeString(String concept, byte[] image) {
		super(concept, image);
	}
	
	public UnityKnowledgeString(String concept, String description, byte[] image) {
		super(concept, description, image);
	}
	
}
