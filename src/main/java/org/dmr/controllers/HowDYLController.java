package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;
import org.dmr.services.HowDYLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@RestController
@RequestMapping("/api")
public class HowDYLController {
	
	@Autowired
    private HowDYLService howDYLService;
	
    private List<UnityKnowledgeString> list;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public HowDYLController() {
        
        this.list = new ArrayList<>();
        
    }

    @RequestMapping(value = "/howdyl/addUnity", method = RequestMethod.POST)
    public UnityKnowledgeString addUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("description") String description, @RequestParam("image") byte[] image) throws Exception {
    	
    	UnityKnowledgeString unity = new UnityKnowledgeString(concept, description, image);
    	
    	// actualitzem la unity
    	unity = howDYLService.saveUnity(unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.POST)
    public void removeUnityKnowledge(@RequestParam("concept") String concept) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
    	
    	howDYLService.deleteUnity(unity);
        
    }
    
    @RequestMapping(value = "/howdyl/addTag", method = RequestMethod.POST)
    public UnityKnowledgeString addTagInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
    		
    	unity.addTag(tag);
    	
    	// actualitzem la unity
    	unity = howDYLService.saveUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.POST)
    public UnityKnowledgeString removedTagInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
    		
    	unity.removeTag(tag);
    	
    	// actualitzem la unity
    	unity = howDYLService.saveUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/addRelation", method = RequestMethod.POST)
    public UnityKnowledgeString addRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
    		
    	UnityKnowledgeString unityRelation = howDYLService.getUnity(conceptRelation);
	    	
	    unity.addRelation(unityRelation);
	    
	    // actualitzem la unity
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.POST)
    public UnityKnowledgeString removeRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
    		
    	UnityKnowledgeString unityRelation = howDYLService.getUnity(conceptRelation);
	    	
	    unity.removeRelation(unityRelation);
	    
	    // actualitzem la unity
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/modifyDescription", method = RequestMethod.POST)
    public UnityKnowledgeString modifyDescriptionInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("description") String description) throws Exception {
    	
    	UnityKnowledgeString unity = howDYLService.getUnity(concept);
	    	
	    unity.modifyDescription(description);
	    
	    // actualitzem la unity
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }

    @RequestMapping(value = "/howdyl/{concept}")
    public UnityKnowledgeString getUnityKnowledge(@PathVariable String concept) throws Exception {
    	
    	return howDYLService.getUnity(concept);
    	   
    }

    @RequestMapping(value = "/howdyl/list")
    public List<UnityKnowledgeString> getLRUList() throws InterruptedException {

        ResourceSubscriber<List<UnityKnowledgeString>> subscriber = new ResourceSubscriber<List<UnityKnowledgeString>>() {
        	
            @Override
            public void onNext(List<UnityKnowledgeString> s) {
            	list = s;
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Complete");
            }
            
        };
        
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Flowable.just(howDYLService.getList()).subscribeWith(subscriber));

        //Thread.sleep(5000);

        return list;
        
    }
    
    @RequestMapping(value = "/howdyl/state")
    public String getState() throws Exception {
    	
    	return "OK";
    	
    }
    
}
