package org.dmr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.domain.impl.User;
import org.dmr.repositories.RobotRepository;
import org.dmr.repositories.TagRepository;
import org.dmr.repositories.UnityKnowledgeObjectRepository;
import org.dmr.repositories.UserRepository;
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
	RobotRepository robotRepository;
	
	@Autowired
	UnityKnowledgeObjectRepository repositoryUnity;
	
	@Autowired
	TagRepository repositoryTag;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MongoOperations mongoOperation;

    public HowDYLServiceImpl() {
        
    }
    
    // CRUD USER
    
    @Override
    public User createUser(User user) {
    	
    	return userRepository.save(user);
    	
    }
    
    @Override
    public User updateUser(User user) {
    	
    	return userRepository.save(user);
    	
    }
	
    @Override
    public User removeUser(String idUser) {

    	User user = userRepository.findById(idUser);
    	
    	if(user != null) {
    		
    		userRepository.delete(user);
    		
    	}
    	
    	return user;
    	
    }
	
    @Override
    public User getUser(String key, Object value) {
    	
    	Query query = new Query();
    	query.addCriteria(
			Criteria.where(key).is(value)
		);
		
    	return mongoOperation.findOne(query, User.class);
    	
    }
	
    @Override
    public User getUserByNick(String nick) {
    	
    	return userRepository.findByNick(nick);
    	
    }
	
    @Override
    public List<User> getListUsers() {
    	
    	return userRepository.findAll();
    	
    }
    
    @Override
    public User validateUser(String user, String password) {
    	
    	Query query = new Query();
    	// Criteri or del usuari
    	Criteria criteriaUser = new Criteria();
    	criteriaUser.orOperator(Criteria.where("nick").is(user), Criteria.where("email").is(user), Criteria.where("email").is(user));
    	// Definim el criteria de l'usuari i la contrasenya
    	query.addCriteria(criteriaUser.andOperator(Criteria.where("password").is(password)));
		
    	return mongoOperation.findOne(query, User.class);
    	
    }
    
    // CRUD ROBOT
    
    @Override
    public Robot createRobot(Robot robot) {
    	
    	return robotRepository.save(robot);
    	 
    }
    
    @Override
    public Robot updateRobot(Robot robot) {
    	
    	return robotRepository.save(robot);
    	 
    }
    
    @Override
    public Robot removeRobot(String idRobot) {
    	
    	Robot robot = robotRepository.findById(idRobot);
    	
    	if(robot != null) {
    		
    		robotRepository.delete(robot);
    		
    	}
    	
    	return robot;
    }
    
    @Override
    public Robot getRobotByName(String name) {
    
    	return robotRepository.findByName(name);
    	
    }
    
    @Override
    public Robot getRobot(String keyUser, Object valueUser, String key, Object value) {
    	
    	Query query = new Query();
    	query.addCriteria(
			Criteria.where(keyUser).is(valueUser)
		);
		
    	User user = mongoOperation.findOne(query, User.class);
    	
    	if(user != null) {
    		
    		return findRobotInUser(user.getRobots(), key, value);
    		
    	}
    	
    	return null;
    	
    }
    
    @Override
    public List<Robot> getListRobots(String idUser) {
    	
    	User user = userRepository.findById(idUser);
    	
    	return user.getRobots();
    	
    }
    
    @Override
    public UnityKnowledgeObject createUnity(UnityKnowledgeObject unity) {
    	
    	unity = repositoryUnity.save(unity);
    	
        return unity;
        
    }
    
    @Override
    public UnityKnowledgeObject createUnity(String idUser, String idRobot, UnityKnowledgeObject unity) {
        
    	unity = createUnity(unity);
    	
    	Robot robot = robotRepository.findOne(idRobot);
    	
    	robot.addUnity(unity);
    	
    	robot = robotRepository.save(robot);
    	
    	User user = userRepository.findOne(idUser);
    	
    	user.addRobot(robot);
    	
    	robot = robotRepository.save(robot);
    	
        return unity;
        
    }
    
    @Override
    public UnityKnowledgeObject removeUnity(String idUnity) {
    	
    	UnityKnowledgeObject unity = repositoryUnity.findById(idUnity);
    	
    	if(unity != null) {
    		// El delete de unity no acaba d'anar be
    		// falla quan hi ha un unic element a la llista
    		repositoryUnity.delete(unity);
    	
    	}
    	
    	return unity;
    	
    }
    
    @Override
    public UnityKnowledgeObject updateUnity(UnityKnowledgeObject unity) {
    	
    	unity = repositoryUnity.save(unity);
    	
    	return unity;
    	
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
    	
    	return new ArrayList<UnityKnowledgeObject>();
    }
    
    @Override
    public UnityKnowledgeObject removeRelation(String idRelation) {
    	
    	UnityKnowledgeObject relation = repositoryUnity.findById(idRelation);
    	
    	if(relation != null) {
    	
    		repositoryUnity.delete(relation);
    	
    	}
    	
    	return relation;
		
    }
    
    @Override
    public UnityKnowledgeObject getUnity(String keyUser, Object valueUser, String keyRobot, Object valueRobot, String key, Object value) {
    	
    	Query query = new Query();
    	query.addCriteria(
			Criteria.where(keyUser).is(valueUser)
		);
		
    	User user = mongoOperation.findOne(query, User.class);
    	
    	if(user != null) {
    		
    		Robot robot = findRobotInUser(user.getRobots(), keyRobot, valueRobot);
        	
        	if(robot != null) {
        	
        		return findUnityKnowledgeInRobot(robot.getUnities(), key, value);
        		
        	}
        	
    	}
    	
    	return null;
    	
    }
    
    private Robot findRobotInUser(List<Robot> list, String key, Object value) {
    	
    	for(Robot robot: list) {
    		
    		if(key.equals("name")) {
    			if(robot.getName() != null && robot.getName().equals(value)) {
    				return robot;
    			}
    		} else if(key.equals("id")) {
    			if(robot.getId() != null && robot.getId().equals(value)) {
    				return robot;
    			}
    		} else if(key.equals("description")) {
    			if(robot.getDescription() != null && robot.getDescription().equals(value)) {
    				return robot;
    			}
    		} else if(key.equals("profession")) {
    			if(robot.getProfession() != null && robot.getProfession().equals(value)) {
    				return robot;
    			}
    		} else if(key.equals("age")) {
    			if(robot.getAge() != null && robot.getAge().equals(value)) {
    				return robot;
    			}
    		}
    		
    	}
		
		return null;
		
    }
    
    private UnityKnowledgeObject findUnityKnowledgeInRobot(List<UnityKnowledgeObject> list, String key, Object value) {
    	
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
    		
    		UnityKnowledgeObject result = findUnityKnowledgeInRobot(unity.getUnities(), key, value);
    		
    		if(result != null) {
    			return result;
    		}
    		
    	}
		
		return null;
		
    }
    
    @Override
    public Tag createTag(UnityKnowledgeObject unity, Tag tag) {
        
    	repositoryTag.save(tag);
    	
    	unity.addTag(tag);
    	
    	unity = repositoryUnity.save(unity);
    	
        return tag;
        
    }
    
    @Override
    public Tag removeTag(String idTag) {
    	
    	Tag tag = repositoryTag.findById(idTag);
    	
    	if(tag != null) {
    		
    		repositoryTag.delete(tag);
    	
    	}		
    		
    	return tag;
    }
    
    @Override
    public Tag updateTag(Tag tag) {
    	
    	tag = repositoryTag.save(tag);
    	
    	return tag;
    	
    }
    
    @Override
    public Tag getTag(String keyUnity, Object valueUnity, String key, Object value) {
    	return null;
    }
    
    @Override
    public List<UnityKnowledgeObject> getListTags(String keyUnity, Object valueUnity) {
    	return null;
    }
    	
}
