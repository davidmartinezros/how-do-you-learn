package org.dmr.services.impl;

import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;
import org.dmr.repositories.UnityKnowledgeRepositoryString;
import org.dmr.services.CallUrlService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	UnityKnowledgeRepositoryString repository;

    public CallUrlServiceImpl() {
        
    }

    @Override
    public UnityKnowledgeString addUnityKnowledgeStringInLRU(String concept, UnityKnowledgeString unity) {
        
    	unity = repository.save(unity);
    	
        return unity;
        
    }

    @Override
    public UnityKnowledgeString getUnityKnowledgeStringFromLRU(final String concept) throws Exception {
    
    	return repository.findByConcept(concept);
    	
    }

    @Override
    public List<UnityKnowledgeString> getLRUState() {
    	
    	return repository.findAll();
        
    }
    
}
