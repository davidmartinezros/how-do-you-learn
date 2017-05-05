package org.dmr.controllers;

import java.util.List;

import org.dmr.domain.impl.Robot;
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
	
	private List<Robot> listRobots;
	
	private List<UnityKnowledgeObject> listUnities;
	
	@Autowired
    private HowDYLService howDYLService;
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public HowDYLController() {
        
    }

    @RequestMapping(value = "/howdyl/addUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject addUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("description") String description, @RequestParam("image") byte[] image) throws Exception {
    	
    	UnityKnowledgeObject unity = new UnityKnowledgeObject(concept, description, image);
    	
    	// actualitzem la unity
    	unity = howDYLService.addUnity(idRobot, unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject removeUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
    	
    	// esborrem unity
    	howDYLService.deleteUnity(idRobot, unity);
    	
    	return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/addTag", method = RequestMethod.POST)
    public UnityKnowledgeObject addTagInUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
    		
    	unity.addTag(tag);
    	
    	unity = howDYLService.addUnity(idRobot, unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.POST)
    public UnityKnowledgeObject removedTagInUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
    		
    	unity.removeTag(tag);
    	
    	unity = howDYLService.saveUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/addRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject addRelationInUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
    		
    	UnityKnowledgeObject unityRelation = howDYLService.getUnityKnowledgeInRobot(idRobot, conceptRelation);
	    	
	    unity.addRelation(unityRelation);
	    
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject removeRelationInUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
		
    	UnityKnowledgeObject unityRelation = howDYLService.getUnityKnowledgeInRobot(idRobot, conceptRelation);
    	
    	unity.removeRelation(unityRelation);
    	
	    unity = howDYLService.saveUnity(unity);
	    
	    return unity;
	    
    }
    
    @RequestMapping(value = "/howdyl/modifyDescription", method = RequestMethod.POST)
    public UnityKnowledgeObject modifyDescriptionInUnityKnowledge(@RequestParam("idRobot") String idRobot, @RequestParam("concept") String concept, @RequestParam("description") String description) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
	    	
	    unity.modifyDescription(description);
	    
	    unity = howDYLService.saveUnity(unity);
	    
    	return unity;
    	
    }

    @RequestMapping(value = "/howdyl/getUnity/{concept}")
    public UnityKnowledgeObject getUnityKnowledge(@PathVariable String concept) throws Exception {
    	
    	// obtenim unity
    	return howDYLService.getUnity(concept);
    	   
    }
    
    @RequestMapping(value = "/howdyl/getRobot/{robot}")
    public Robot getRobot(@PathVariable String robot) throws Exception {
    	
    	// obtenim robot
    	return howDYLService.getRobot(robot);
    	   
    }

    @RequestMapping(value = "/howdyl/listUnities")
    public List<UnityKnowledgeObject> getListUnities() throws InterruptedException {
    	    	
        ResourceSubscriber<List<UnityKnowledgeObject>> subscriber = new ResourceSubscriber<List<UnityKnowledgeObject>>() {
        	
            @Override
            public void onNext(List<UnityKnowledgeObject> s) {
            	listUnities = s;
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Complete List Unities Search");
            }
            
        };
        
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Flowable.just(howDYLService.getListUnities()).subscribeWith(subscriber));

        return listUnities;
        
    }
    
    @RequestMapping(value = "/howdyl/listRobots")
    public List<Robot> getListRobots() throws InterruptedException {

        ResourceSubscriber<List<Robot>> subscriber = new ResourceSubscriber<List<Robot>>() {
        	
            @Override
            public void onNext(List<Robot> s) {
            	listRobots = s;
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Complete List Robots Search");
            }
            
        };
        
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Flowable.just(howDYLService.getListRobots()).subscribeWith(subscriber));

        return listRobots;
        
    }
    
    @RequestMapping(value = "/howdyl/state")
    public String getState() throws Exception {
    	
    	return "OK";
    	
    }
    
}
