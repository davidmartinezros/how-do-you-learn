package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface HowDYLService {
    
	UnityKnowledgeString saveUnity(UnityKnowledgeString unity);
	
	void deleteUnity(UnityKnowledgeString unity);
    
    UnityKnowledgeString getUnity(String concept) throws Exception;
    
    public List<UnityKnowledgeString> getList();
    
}
