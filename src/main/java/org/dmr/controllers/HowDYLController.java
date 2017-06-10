package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.TagWithUnityKnowlegdeWrapper;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.domain.impl.UnityKnowledgeWithRobotWrapper;
import org.dmr.domain.impl.UnityRelationWithRobotWrapper;
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
    	
    	robot = howDYLService.createRobot(robot);
    	
        return robot;
        
    }
    
    @RequestMapping(value = "/howdyl/removeRobot", method = RequestMethod.GET)
    public Robot removeRobot(@RequestParam("id_robot") String idRobot) throws Exception {
    	
    	return howDYLService.removeRobot(idRobot);
        
    }

    @RequestMapping(value = "/howdyl/createUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject createUnity(@RequestBody UnityKnowledgeWithRobotWrapper unityKnowledgeWithRobotWrapper) throws Exception {
    	
    	String idRobot = unityKnowledgeWithRobotWrapper.getIdRobot();
    	UnityKnowledgeObject unity = unityKnowledgeWithRobotWrapper.getUnity();
    	
    	// creem la unity
    	unity = howDYLService.createUnity(idRobot, unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.GET)
    public void removeUnity(@RequestParam("id_unity") String idUnity) throws Exception {
    	
    	howDYLService.removeUnity(idUnity);
        
    }
    
    @RequestMapping(value = "/howdyl/createTag", method = RequestMethod.POST)
    public Tag createTag(@RequestBody TagWithUnityKnowlegdeWrapper tagWithUnityKnowlegdeWrapper) throws Exception {
    	
    	String idRobot = tagWithUnityKnowlegdeWrapper.getIdRobot();
    	String idUnity = tagWithUnityKnowlegdeWrapper.getIdUnity();
    	Tag tag = tagWithUnityKnowlegdeWrapper.getTag();
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity("id", idRobot, "id", idUnity);
    	
    	tag = howDYLService.createTag(unity, tag);
    	
    	return tag;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.GET)
    public Tag removeTag(@RequestParam("id_tag") String idTag) throws Exception {
    	
    	return howDYLService.removeTag(idTag);
    	
    }
    
    @RequestMapping(value = "/howdyl/createRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject createRelation(@RequestBody UnityRelationWithRobotWrapper unityRelationWithRobotWrapper) throws Exception {
    	
    	String idRobot = unityRelationWithRobotWrapper.getIdRobot();
    	String idUnity = unityRelationWithRobotWrapper.getIdUnity();
    	UnityKnowledgeObject unityRelation = unityRelationWithRobotWrapper.getUnityRelation();
    	
    	unityRelation = howDYLService.createUnity(unityRelation);
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity("id", idRobot, "id", idUnity);
	    	
	    unity.addUnity(unityRelation);
	    
	    unity = howDYLService.updateUnity(unity);
	    
    	return unityRelation;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.GET)
    public UnityKnowledgeObject removeRelation(@RequestParam("id_unity") String idUnity, @RequestParam("id_unity_relation") String idUnityRelation) throws Exception {

    	return howDYLService.removeRelation(idUnityRelation);
	    
    }

    @RequestMapping(value = "/howdyl/unity/{idRobot}/{idUnity}", method = RequestMethod.GET)
    public UnityKnowledgeObject getUnity(@PathVariable String idRobot, @PathVariable String idUnity) throws Exception {
    	
    	return howDYLService.getUnity("id", idRobot, "id", idUnity);
    	   
    }
    
    @RequestMapping(value = "/howdyl/unityByConcept/{idRobot}/{concept}", method = RequestMethod.GET)
    public UnityKnowledgeObject getUnityByConcept(@PathVariable String idRobot, @PathVariable String concept) throws Exception {
    	
    	return howDYLService.getUnity("id", idRobot, "concept", concept);
    	   
    }
    
    @RequestMapping(value = "/howdyl/robot/{idRobot}", method = RequestMethod.GET)
    public Robot getRobot(@PathVariable String idRobot) throws Exception {
    	
    	return howDYLService.getRobot(idRobot);
    	   
    }
    
    @RequestMapping(value = "/howdyl/robotByName/{robot}", method = RequestMethod.GET)
    public Robot getRobotByName(@PathVariable String robot) throws Exception {
    	
    	return howDYLService.getRobotByName(robot);
    	   
    }

    @RequestMapping(value = "/howdyl/listUnities/{idRobot}", method = RequestMethod.GET)
    public List<UnityKnowledgeObject> getListUnities(@PathVariable String idRobot) throws InterruptedException {
    	
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
        compositeDisposable.add(Flowable.just(howDYLService.getListUnities("id", idRobot)).subscribeWith(subscriber));

        return listUnities;
        
    }
    
    @RequestMapping(value = "/howdyl/listRobots", method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/howdyl/state", method = RequestMethod.GET)
    public String getState() throws Exception {
    	
    	return "OK";
    	
    }
    
}
