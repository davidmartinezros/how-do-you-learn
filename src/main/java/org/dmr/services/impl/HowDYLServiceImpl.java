package org.dmr.services.impl;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.repositories.UnityKnowledgeObjectRepository;
import org.dmr.services.HowDYLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Service
public class HowDYLServiceImpl implements HowDYLService {
	
	@Autowired
	UnityKnowledgeObjectRepository repository;
	
	@Autowired
	MongoOperations mongoOperation;

    public HowDYLServiceImpl() {
        
    }
    
    @Override
    public UnityKnowledgeObject saveUnity(UnityKnowledgeObject unity) {
        
    	unity = repository.save(unity);
    	
        return unity;
        
    }

    @Override
    public void deleteUnity(UnityKnowledgeObject unity) {
        
    	repository.delete(unity);
        
    }
    
    @Override
    public void deleteRelation(UnityKnowledgeObject unity, UnityKnowledgeObject unityRelation) {
    	
    	//repository.deleteRelations(unity.getId(), unityRelation.getId());
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where("id").is(unity.getId())
			.andOperator(
					Criteria.where("relations.id").is(unityRelation.getId())
					)
			);
		mongoOperation.remove(query, UnityKnowledgeObject.class);
    	
    }

    @Override
    public UnityKnowledgeObject getUnity(Object concept) throws Exception {
    
    	return repository.findByConcept(concept);
    	
    }

    @Override
    public List<UnityKnowledgeObject> getList() {
    	
    	return repository.findAll();
        
    }
    
}
