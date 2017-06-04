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
    
    // CRUD ROBOT
    
    @Override
    public Robot createRobot(Robot robot) {
    	
    	 robot = robotRepository.save(robot);
    	 
    	 return robot;
    	 
    }
    
    @Override
    public Robot updateRobot(Robot robot) {
    	
    	 robot = robotRepository.save(robot);
    	 
    	 return robot;
    	 
    }
    
    @Override
    public Robot removeRobot(Robot robot) {
    	
    	robotRepository.delete(robot);
    	
    	return robot;
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
    public UnityKnowledgeObject createUnity(String idRobot, UnityKnowledgeObject unity) {
        
    	Robot robot = robotRepository.findOne(idRobot);
    	
    	unity = repositoryUnity.save(unity);
    	
    	robot.addUnity(unity);
    	
    	robot = robotRepository.save(robot);
    	
        return unity;
        
    }
    
    @Override
    public UnityKnowledgeObject updateUnity(UnityKnowledgeObject unity) {
    	
    	unity = repositoryUnity.save(unity);
    	
    	return unity;
    	
    }
    
    @Override
    public void removeUnity(String keyRobot, Object valueRobot, String key, Object value) {
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where(keyRobot).is(valueRobot)
		);
		Update update = new Update();
		update.pull("unities", new Query().addCriteria(Criteria.where(key).is(value)));
		
		WriteResult result = mongoOperation.updateFirst(query, update, Robot.class);
		
		System.out.println(result.toString());
		
    }

    @Override
    public List<UnityKnowledgeObject> getListUnities(String keyRobot, Object valueRobot) {
    	
    	Query query = new Query();
    	query.addCriteria(
			Criteria.where(keyRobot).is(valueRobot)
		);
		
    	Robot robot = mongoOperation.findOne(query, Robot.class);
    	
    	if(robot != null) {
    		
    		return robot.getUnities();	
    	
    	}
    	
    	return null;
    }
    
    @Override
    public void deleteRelation(String keyUnity, Object valueUnity, String key, Object value) {
    	
    	Query query = new Query();
		query.addCriteria(
			Criteria.where(keyUnity).is(valueUnity)
		);
		Update update = new Update();
		update.pull("relations", new Query().addCriteria(Criteria.where(key).is(value)));
		
		WriteResult result = mongoOperation.updateFirst(query, update, UnityKnowledgeObject.class);
		
		System.out.println(result.toString());
		
    }    
    
    @Override
    public UnityKnowledgeObject getUnity(String keyRobot, Object valueRobot, String key, Object value) {
    	
    	Query query = new Query();
    	query.addCriteria(
			Criteria.where(keyRobot).is(valueRobot)
		);
		
    	Robot robot = mongoOperation.findOne(query, Robot.class);
    	
    	return findUnityKnowledge(robot.getUnities(), key, value);
    	
    }
    
    private UnityKnowledgeObject findUnityKnowledge(List<UnityKnowledgeObject> list, String key, Object value) {
    	
    	for(UnityKnowledgeObject unity: list) {
    		
    		if(key.equals("concept")) {
    			if(unity.getConcept() != null && unity.getConcept().equals(value)) {
    				return unity;
    			}
    		} else if(key.equals("id")) {
    			if(unity.getId() != null && unity.getId().equals(value)) {
    				return unity;
    			}
    		} else if(key.equals("description")) {
    			if(unity.getDescription() != null && unity.getDescription().equals(value)) {
    				return unity;
    			}
    		} else if(key.equals("image")) {
    			if(unity.getImage() != null && unity.getImage().equals(value)) {
    				return unity;
    			}
    		} else if(key.equals("tags")) {
    			if(unity.getTags() != null && unity.getTags().contains(value)) {
    				return unity;
    			}
    		}
    		
    		UnityKnowledgeObject result = findUnityKnowledge(unity.getUnities(), key, value);
    		
    		if(result != null) {
    			return result;
    		}
    		
    	}
		
		return null;
		
    }
    	
}
