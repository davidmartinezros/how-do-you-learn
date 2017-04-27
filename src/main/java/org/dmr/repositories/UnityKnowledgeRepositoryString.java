package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UnityKnowledgeRepositoryString extends MongoRepository<UnityKnowledgeString, String> {

	public List<UnityKnowledgeString> findAll();
	
    public UnityKnowledgeString findByConcept(Object concept);

}