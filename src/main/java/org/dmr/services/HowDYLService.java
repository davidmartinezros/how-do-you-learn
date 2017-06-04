package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
	
	// CRUD ROBOT
	
	Robot createRobot(Robot robot);
	
	Robot updateRobot(Robot robot);
	
	Robot removeRobot(Robot robot);
	
	Robot getRobot(String name);
	
	List<Robot> getListRobots();
	
	//-->
	
	// CRUD UNITY
	
	UnityKnowledgeObject createUnity(String idRobot, UnityKnowledgeObject unity);
    
	UnityKnowledgeObject updateUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject getUnity(String keyRobot, Object valueRobot, String key, Object value);
    
	void removeUnity(String keyRobot, Object valueRobot, String key, Object value);
    
    public List<UnityKnowledgeObject> getListUnities(String keyRobot, Object valueRobot);
    
    //-->
    
    // ALTRES
    
    void deleteRelation(String keyUnity, Object valueUnity, String key, Object value);
    
    
}
