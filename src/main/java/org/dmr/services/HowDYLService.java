package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
    
	UnityKnowledgeObject saveUnity(UnityKnowledgeObject unity);
	
	void deleteUnity(UnityKnowledgeObject unity);
	
	void deleteRelation(UnityKnowledgeObject unity, UnityKnowledgeObject unityRelation);
    
    UnityKnowledgeObject getUnity(Object concept) throws Exception;
    
    public List<UnityKnowledgeObject> getList();
    
}
