package org.dmr.services.impl;

import java.util.LinkedHashMap;

import org.dmr.domain.LruCache;
import org.dmr.domain.impl.LruCacheImpl;
import org.dmr.domain.impl.UnityKnowledgeString;
import org.dmr.services.CallUrlService;
import org.springframework.stereotype.Service;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Service
public class CallUrlServiceImpl implements CallUrlService {
	
    private LruCache lru;

    public CallUrlServiceImpl() {
        
    	this.lru = LruCacheImpl.getInstance();
        this.lru.setLruSize(100);
        
    }

    @Override
    public UnityKnowledgeString addUnityKnowledgeStringInLRU(final String concept, final UnityKnowledgeString unity) {
    	
        lru.put(concept, unity);
        
        return unity;
        
    }

    @Override
    public UnityKnowledgeString getUnityKnowledgeStringFromLRU(final String concept) throws Exception {
    	
        return lru.get(concept);
    
    }

    @Override
    public LinkedHashMap<String, UnityKnowledgeString> getLRUState() {
    	
        return lru.getLru();
        
    }
    
}
