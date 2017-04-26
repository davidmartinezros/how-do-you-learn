package org.dmr.domain.impl;


import java.util.LinkedHashMap;
import java.util.List;

import org.dmr.domain.LruCache;
import org.dmr.repositories.UnityKnowledgeRepositoryString;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public class LruCacheImpl implements LruCache {
	
	private UnityKnowledgeRepositoryString repository;
	
    private static LruCacheImpl ourInstance;
    private LinkedHashMap<String,UnityKnowledgeString> lruMap;

    public static LruCacheImpl getInstance(UnityKnowledgeRepositoryString repository) {
    	if(ourInstance == null) {
    		ourInstance = new LruCacheImpl(repository);
    	}
        return ourInstance;
    }

    public LruCacheImpl(UnityKnowledgeRepositoryString repository) {
    	this.repository = repository;
        this.lruMap = new LinkedHashMap<>();
    }
    
    @Override
    public void put(String key, UnityKnowledgeString value) {
    
    	repository.save(value);
    
    }
    
    @Override
    public UnityKnowledgeString get(String key) throws Exception {
    	
        return repository.findByConcept(key);
    
    }

    @Override
    public List<UnityKnowledgeString> getLru() {
    
    	return repository.findAll();

    }

    @Override
    public String toString(){
        
    	String lruString = "";
        for (UnityKnowledgeString value : lruMap.values()) {
            lruString = lruString + value.getConcept() + " ";
        }
        return lruString;
    
    }
}
