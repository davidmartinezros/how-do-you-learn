package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
    
	UnityKnowledgeObject saveUnity(UnityKnowledgeObject unity);
	
	UnityKnowledgeObject addUnity(String idRobot, UnityKnowledgeObject unity);
	
	void deleteUnity(String idRobot, UnityKnowledgeObject unity);
	
	void deleteRelation(UnityKnowledgeObject unity, UnityKnowledgeObject unityRelation);
	
	Robot getRobot(String name);
	
	List<Robot> getListRobots();
    
    UnityKnowledgeObject getUnity(Object concept);
    
    List<UnityKnowledgeObject> getListUnities();
    
    UnityKnowledgeObject getUnityKnowledgeInRobot(String idRobot, Object concept);
    
}
