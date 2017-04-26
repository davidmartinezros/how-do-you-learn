package org.dmr.services;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface CallUrlService {
    
	UnityKnowledgeString addUnityKnowledgeStringInLRU(UnityKnowledgeString unity);
    
    UnityKnowledgeString getUnityKnowledgeStringFromLRU(String concept) throws Exception;
    
    public List<UnityKnowledgeString> getLRUState();
    
}
