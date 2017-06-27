package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.domain.impl.User;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
	
	// CRUD USER
	
	User createUser(User user);
	
	User updateUser(User user);
	
	User removeUser(String idUser);
	
	User getUser(String key, Object value);
	
	User getUserByNick(String nick);
	
	List<User> getListUsers();
	
	// CRUD ROBOT
	
	Robot createRobot(Robot robot);
	
	Robot updateRobot(Robot robot);
	
	Robot removeRobot(String idRobot);
	
	Robot getRobot(String keyUser, Object valueUser, String key, Object value);
	
	Robot getRobotByName(String name);
	
	List<Robot> getListRobots();
	
	//-->
	
	// CRUD UNITY
	
	UnityKnowledgeObject createUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject createUnity(String idUser, String idRobot, UnityKnowledgeObject unity);
    
	UnityKnowledgeObject updateUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject getUnity(String keyUser, Object valueUser, String keyRobot, Object valueRobot, String key, Object value);
    
	UnityKnowledgeObject removeUnity(String idUnity);
    
    List<UnityKnowledgeObject> getListUnities(String keyRobot, Object valueRobot);
    
    //-->
    
    // CRUD TAG
    
    Tag createTag(UnityKnowledgeObject unity, Tag tag);
    
    Tag updateTag(Tag tag);
    
    Tag getTag(String keyUnity, Object valueUnity, String key, Object value);
    
    Tag removeTag(String idTag);
    
    List<UnityKnowledgeObject> getListTags(String keyUnity, Object valueUnity);
    
    // ALTRES
    
    UnityKnowledgeObject removeRelation(String idRelation);
    
    
}
