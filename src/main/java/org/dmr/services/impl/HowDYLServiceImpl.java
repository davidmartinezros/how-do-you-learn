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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;

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
	RobotRepository robotRepository;
	
	@Autowired
	UnityKnowledgeObjectRepository repositoryUnity;
	
	@Autowired
	MongoOperations mongoOperation;

    public HowDYLServiceImpl() {
        
    }
    
    @Override
    public Robot createRobot(Robot robot) {
    	
    	 robot = robotRepository.save(robot);
    	 
    	 return robot;
    	 
    }
    
    @Override
    public UnityKnowledgeObject saveUnity(UnityKnowledgeObject unity) {
    	
    	unity = repositoryUnity.save(unity);
    	
    	return unity;
    	
    }
    
    @Override
    public UnityKnowledgeObject addUnity(String idRobot, UnityKnowledgeObject unity) {
        
    	Robot robot = robotRepository.findOne(idRobot);
    	
    	unity = repositoryUnity.save(unity);
    	
    	robot.addUnity(unity);
    	
    	robot = robotRepository.save(robot);
    	
        return unity;
        
    }

    @Override
    public void deleteUnity(String idRobot, UnityKnowledgeObject unity) {
        
    	Robot robot = robotRepository.findOne(idRobot);
    	
    	robot.removeUnity(unity);
    	
    	robot = robotRepository.save(robot);
        
    }
    
    @Override
    public void deleteRelation(String idUnity, String idUnityRelation) {
    	
    	//repository.deleteRelations(unity.getId(), unityRelation.getId());
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where("id").is(idUnity)
			.andOperator(
					Criteria.where("relations.id").is(idUnityRelation)
					)
			);
		mongoOperation.remove(query, UnityKnowledgeObject.class);
    	
    }
    
    @Override
    public Robot getRobot(String name) {
    
    	return robotRepository.findByName(name);
    	
    }
    
    @Override
    public List<Robot> getListRobots() {
    	
    	return robotRepository.findAll();
        
    }
    
    @Override
    public UnityKnowledgeObject getUnity(Object concept) {
    
    	return repositoryUnity.findByConcept(concept);
    	
    }

    @Override
    public List<UnityKnowledgeObject> getListUnities() {
    	
    	return repositoryUnity.findAll();
        
    }
    
    public void deleteRobotWithUnity(String idRobot, Object concept) {
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where("id").is(idRobot)
			.andOperator(
					Criteria.where("unities.concept").is(concept)
					)
			);
		WriteResult result = mongoOperation.remove(query, Robot.class);
		
		System.out.println(result.toString());
		
    }
    
    @Override
    public void deleteUnity(String idRobot, Object concept) {
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where("id").is(idRobot)
		);
		Update update = new Update();
		update.pull("unities", new Query().addCriteria(Criteria.where("concept").is(concept)));
		
		WriteResult result = mongoOperation.updateFirst(query, update, Robot.class);
		
		System.out.println(result.toString());
		
    }
        
    
    	
}
