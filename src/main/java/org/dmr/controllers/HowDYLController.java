package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.TagWithUnityKnowlegdeWrapper;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.domain.impl.UnityKnowledgeWithRobotWrapper;
import org.dmr.services.HowDYLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public HowDYLController() {
        
    }
    
    @RequestMapping(value = "/howdyl/createRobot", method = RequestMethod.POST)
    public Robot createRobot(@RequestBody Robot robot) throws Exception {
    	
    	// creem el robot
    	robot = howDYLService.createRobot(robot);
    	
        return robot;
        
    }
    
    @RequestMapping(value = "/howdyl/removeRobot", method = RequestMethod.POST)
    public Robot removeRobot(@RequestParam("name_robot") String nameRobot) throws Exception {
    	
    	// busquem el robot
    	Robot robot = howDYLService.getRobot(nameRobot);
    	// esborrem el robot
    	robot = howDYLService.removeRobot(robot);
    	
        return robot;
        
    }

    @RequestMapping(value = "/howdyl/createUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject createUnity(@RequestBody UnityKnowledgeWithRobotWrapper unityKnowledgeWithRobotWrapper) throws Exception {
    	
    	//UnityKnowledgeObject unity = new UnityKnowledgeObject(concept, description, image);
    	
    	UnityKnowledgeObject unity = unityKnowledgeWithRobotWrapper.getUnity();
    	String idRobot = unityKnowledgeWithRobotWrapper.getIdRobot();
    	
    	// creem la unity
    	unity = howDYLService.createUnity(idRobot, unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.POST)
    public void removeUnity(@RequestParam("id_robot") String idRobot, @RequestParam("concept") Object concept) throws Exception {
    	
    	//esborrem la unitat
    	howDYLService.removeUnity("id", idRobot, "concept", concept);
        
    }
    
    @RequestMapping(value = "/howdyl/createTag", method = RequestMethod.POST)
    public UnityKnowledgeObject createTag(@RequestBody TagWithUnityKnowlegdeWrapper tagWithUnityKnowlegdeWrapper) throws Exception {
    	
    	String idRobot = tagWithUnityKnowlegdeWrapper.getIdRobot();
    	Object concept = tagWithUnityKnowlegdeWrapper.getConcept();
    	String tag = tagWithUnityKnowlegdeWrapper.getTag();
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot("id", idRobot, "concept", concept);
    		
    	unity.addTag(tag);
    	
    	unity = howDYLService.updateUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.POST)
    public UnityKnowledgeObject removedTag(@RequestBody TagWithUnityKnowlegdeWrapper tagWithUnityKnowlegdeWrapper) throws Exception {
    	
    	String idRobot = tagWithUnityKnowlegdeWrapper.getIdRobot();
    	Object concept = tagWithUnityKnowlegdeWrapper.getConcept();
    	String tag = tagWithUnityKnowlegdeWrapper.getTag();
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot("id", idRobot, "concept", concept);
    		
    	unity.removeTag(tag);
    	
    	unity = howDYLService.updateUnity(unity);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/createRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject createRelation(@RequestParam("id_robot") String idRobot, @RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation) throws Exception {
    	
    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot("id", idRobot, "concept", concept);
    		
    	UnityKnowledgeObject unityRelation = howDYLService.getUnityKnowledgeInRobot("id", idRobot, "concept", conceptRelation);
	    	
	    unity.addUnity(unityRelation);
	    
	    unity = howDYLService.updateUnity(unity);
	    
    	return unity;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject removeRelation(@RequestParam("id_unity") String idUnity, @RequestParam("id_unity_relation") String idUnityRelation) throws Exception {
    	
//    	UnityKnowledgeObject unity = howDYLService.getUnityKnowledgeInRobot(idRobot, concept);
//		
//    	UnityKnowledgeObject unityRelation = howDYLService.getUnityKnowledgeInRobot(idRobot, conceptRelation);
//    	
//    	unity.removeRelation(unityRelation);
//    	
//	    unity = howDYLService.saveUnity(unity);
    	
    	howDYLService.deleteRelation("id", idUnity, "id", idUnityRelation);
//	    
//	    return unity;
    	
    	return null;
	    
    }

    @RequestMapping(value = "/howdyl/unity/{concept}")
    public UnityKnowledgeObject getUnity(@PathVariable String concept) throws Exception {
    	
    	// obtenim unity
    	return howDYLService.getUnity(concept);
    	   
    }
    
    @RequestMapping(value = "/howdyl/robot/{robot}")
    public Robot getRobot(@PathVariable String robot) throws Exception {
    	
    	// obtenim robot
    	return howDYLService.getRobot(robot);
    	   
    }

    @RequestMapping(value = "/howdyl/listUnities")
    public List<UnityKnowledgeObject> getListUnities() throws InterruptedException {
    	
    	final List<UnityKnowledgeObject> listUnities = new ArrayList<UnityKnowledgeObject>();
    	
        ResourceSubscriber<List<UnityKnowledgeObject>> subscriber = new ResourceSubscriber<List<UnityKnowledgeObject>>() {
        	
            @Override
            public void onNext(List<UnityKnowledgeObject> s) {
            	listUnities.addAll(s);
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

    	final List<Robot> listRobots = new ArrayList<Robot>();
    	
        ResourceSubscriber<List<Robot>> subscriber = new ResourceSubscriber<List<Robot>>() {
        	
            @Override
            public void onNext(List<Robot> s) {
            	listRobots.addAll(s);
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
