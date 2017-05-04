package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.services.HowDYLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class HowDYLController {
	
	@Autowired
    private HowDYLService howDYLService;
	
    private List<UnityKnowledgeObject> list;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public HowDYLController() {
        
        this.list = new ArrayList<>();
        
    }

    @RequestMapping(value = "/howdyl/addUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject addUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("description") String description, @RequestParam("image") byte[] image) throws Exception {
    	
    	UnityKnowledgeObject unity = new UnityKnowledgeObject(concept, description, image);
    	
    	// actualitzem la unity
    	unity = howDYLService.saveUnity(unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject removeUnityKnowledge(@RequestParam("concept") String concept) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
    	
    	// esborrem unity
    	howDYLService.deleteUnity(unity);
    	
    	return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/addTag", method = RequestMethod.POST)
    public UnityKnowledgeObject addTagInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
    		
    	unity.addTag(tag);
    	
    	unity = howDYLService.saveUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.POST)
    public UnityKnowledgeObject removedTagInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
    		
    	unity.removeTag(tag);
    	
    	unity = howDYLService.saveUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/addRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject addRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
    		
    	UnityKnowledgeObject unityRelation = howDYLService.getUnity(conceptRelation);
	    	
	    unity.addRelation(unityRelation);
	    
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject removeRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
    		
    	UnityKnowledgeObject unityRelation = howDYLService.getUnity(conceptRelation);
    	
    	unity.removeRelation(unityRelation);
    	
	    unity = howDYLService.saveUnity(unity);
	    
	    return unity;
	    
    }
    
    @RequestMapping(value = "/howdyl/modifyDescription", method = RequestMethod.POST)
    public UnityKnowledgeObject modifyDescriptionInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("description") String description) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity(concept);
	    	
	    unity.modifyDescription(description);
	    
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }

    @RequestMapping(value = "/howdyl/{concept}")
    public UnityKnowledgeObject getUnityKnowledge(@PathVariable String concept) throws Exception {
    	
    	// obtenim unity
    	return howDYLService.getUnity(concept);
    	   
    }

    @RequestMapping(value = "/howdyl/list")
    public List<UnityKnowledgeObject> getLRUList() throws InterruptedException {

        ResourceSubscriber<List<UnityKnowledgeObject>> subscriber = new ResourceSubscriber<List<UnityKnowledgeObject>>() {
        	
            @Override
            public void onNext(List<UnityKnowledgeObject> s) {
            	list = s;
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Complete List Search");
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
