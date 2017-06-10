package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
	
	// CRUD ROBOT
	
	Robot createRobot(Robot robot);
	
	Robot updateRobot(Robot robot);
	
	Robot removeRobot(String idRobot);
	
	Robot getRobot(String idRobot);
	
	Robot getRobotByName(String name);
	
	List<Robot> getListRobots();
	
	//-->
	
	// CRUD UNITY
	
	UnityKnowledgeObject createUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject createUnity(String idRobot, UnityKnowledgeObject unity);
    
	UnityKnowledgeObject updateUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject getUnity(String keyRobot, Object valueRobot, String key, Object value);
    
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
