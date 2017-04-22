package org.dmr.services;

import java.util.LinkedHashMap;

import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 */
public interface CallUrlService {
	
    int addStringInLRU(UnityKnowledgeString string);

    UnityKnowledgeString getStringFromLRU(int id) throws Exception;

    LinkedHashMap<Integer,UnityKnowledgeString> getLRUState();
}
