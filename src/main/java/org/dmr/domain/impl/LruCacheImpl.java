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
    private int lruSize = 0;

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
    public void setLruSize(int lruSize) {
    	this.lruSize = lruSize;
    }
    
    @Override
    public void put(String key, UnityKnowledgeString value) {
//        if(lruMap.values().size()>=lruSize){
//            lruMap.remove(lruMap.keySet().iterator().next());
//        }
//        lruMap.put(key, value);
        // grabem a mongoDB
        repository.save(value);
    }
    
    @Override
    public UnityKnowledgeString get(String key) throws Exception {
//        if(lruMap.get(key)!=null){
//            final UnityKnowledgeString value = lruMap.get(key);
//            lruMap.remove(key);
//            lruMap.put(key, value);
//            return value;
//        } else {
//            throw new NullPointerException("Key is not in cache");
//        }
        return repository.findByConcept(key);
    }

    @Override
    public List<UnityKnowledgeString> getLru() {
    	return repository.findAll();
//        return this.lruMap;
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
