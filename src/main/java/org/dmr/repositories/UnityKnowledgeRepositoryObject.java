package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeObject;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UnityKnowledgeRepositoryObject extends MongoRepository<UnityKnowledgeObject, String> {

	public List<UnityKnowledgeObject> findAll();
	
    public UnityKnowledgeObject findByConcept(Object concept);

}