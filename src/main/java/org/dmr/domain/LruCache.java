package org.dmr.domain;

import java.util.LinkedHashMap;

import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface LruCache {
	
	void setLruSize(int lruSize);
	
    void put(int key, UnityKnowledgeString value);

    UnityKnowledgeString get(int key) throws Exception;

    LinkedHashMap<Integer,UnityKnowledgeString> getLru();
    
}
