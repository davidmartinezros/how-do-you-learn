package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnityKnowledgeRepositoryString extends MongoRepository<UnityKnowledgeString, String> {

	public List<UnityKnowledgeString> findAll();
	
    public UnityKnowledgeString findByConcept(Object concept);

}