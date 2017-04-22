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
    private int nextKey;

    public CallUrlServiceImpl() {
        this.lru = LruCacheImpl.getInstance();
        this.lru.setLruSize(100);
        this.nextKey=0;
    }

    @Override
    public int addStringInLRU(final UnityKnowledgeString string) {
    	
        int newKey = getNewKey();
        lru.put(newKey,string);

        return newKey;
    }

    @Override
    public UnityKnowledgeString getStringFromLRU(final int key) throws Exception {
    	
        return lru.get(key);
    
    }

    @Override
    public LinkedHashMap<Integer,UnityKnowledgeString> getLRUState() {
    	
        return lru.getLru();
        
    }

    private int getNewKey(){
    	
        this.nextKey++;
        return nextKey;
        
    }
    
}
