package org.dmr.services;

import java.util.LinkedHashMap;

import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface CallUrlService {
    
	UnityKnowledgeString addUnityKnowledgeStringInLRU(final String concept, final UnityKnowledgeString unity);
    
    UnityKnowledgeString getUnityKnowledgeStringFromLRU(final String concept) throws Exception;
    
    LinkedHashMap<String, UnityKnowledgeString> getLRUState();
    
}
