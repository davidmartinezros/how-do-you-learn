package org.dmr.services.impl;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.repositories.RobotRepository;
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
	RobotRepository repository;
	
	@Autowired
	UnityKnowledgeObjectRepository repositoryUnity;
	
	@Autowired
	MongoOperations mongoOperation;

    public HowDYLServiceImpl() {
        
    }
    
    @Override
    public UnityKnowledgeObject saveUnity(UnityKnowledgeObject unity) {
    	
    	unity = repositoryUnity.save(unity);
    	
    	return unity;
    }
    
    @Override
    public UnityKnowledgeObject addUnity(String idRobot, UnityKnowledgeObject unity) {
        
    	Robot robot = repository.findOne(idRobot);
    	
    	robot.addUnity(unity);
    	
    	robot = repository.save(robot);
    	
        return unity;
        
    }

    @Override
    public void deleteUnity(String idRobot, UnityKnowledgeObject unity) {
        
    	Robot robot = repository.findOne(idRobot);
    	
    	robot.removeUnity(unity);
    	
    	robot = repository.save(robot);
        
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
    public Robot getRobot(String name) {
    
    	return repository.findByName(name);
    	
    }
    
    @Override
    public List<Robot> getListRobots() {
    	
    	return repository.findAll();
        
    }
    
    @Override
    public UnityKnowledgeObject getUnity(Object concept) {
    
    	return repositoryUnity.findByConcept(concept);
    	
    }

    @Override
    public List<UnityKnowledgeObject> getListUnities() {
    	
    	return repositoryUnity.findAll();
        
    }
    
    @Override
    public UnityKnowledgeObject getUnityKnowledgeInRobot(String idRobot, Object concept) {
    	
    	return repository.findUnityKnowledgeInRobot(idRobot, concept);
    			
    }
    
}
