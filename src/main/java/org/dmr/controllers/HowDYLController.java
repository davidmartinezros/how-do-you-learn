package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.RobotWrapper;
import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.TagWithUnityKnowlegdeWrapper;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.dmr.domain.impl.UnityKnowledgeWithRobotWrapper;
import org.dmr.domain.impl.UnityRelationWithRobotWrapper;
import org.dmr.domain.impl.User;
import org.dmr.domain.impl.UserWrapper;
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
    
    @RequestMapping(value = "/howdyl/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) throws Exception {
    	
    	User result = howDYLService.createUser(user);
    	
    	if(result == null) {
    		result =  new User();
    		result.setState("KO");
    		result.setMessage("Error al crear l'usuari");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUser", method = RequestMethod.GET)
    public User removeUser(@RequestParam("id_user") String idUser) throws Exception {
    	
    	User result = howDYLService.removeUser(idUser);
    	
    	if(result == null) {
    		result =  new User();
    		result.setState("KO");
    		result.setMessage("Error a l'eliminar l'usuari");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
        
    }
    
    @RequestMapping(value = "/howdyl/createRobot", method = RequestMethod.POST)
    public Robot createRobot(@RequestBody RobotWrapper robotWrapper) throws Exception {
    	
    	String idUser = robotWrapper.getIdUser();
    	Robot robot = robotWrapper.getRobot();
    	
    	Robot result = howDYLService.createRobot(robot);
    	
    	if(result == null) {
    		result =  new Robot();
    		result.setState("KO");
    		result.setMessage("Error al crear el robot");
    	} else {
    		result.setState("OK");
    	}
    	
    	User user = howDYLService.getUser("id", idUser);
    	
    	user.addRobot(result);
    	
    	user = howDYLService.updateUser(user);
        
    	return result;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRobot", method = RequestMethod.GET)
    public Robot removeRobot(@RequestParam("id_robot") String idRobot) throws Exception {
    	
    	Robot result = howDYLService.removeRobot(idRobot);
    	
    	if(result == null) {
    		result =  new Robot();
    		result.setState("KO");
    		result.setMessage("Error a l'esborrar el robot");
    	} else {
    		result.setState("OK");
    	}
        
    	return result;
        
    }

    @RequestMapping(value = "/howdyl/createUnity", method = RequestMethod.POST)
    public UnityKnowledgeObject createUnity(@RequestBody UnityKnowledgeWithRobotWrapper unityKnowledgeWithRobotWrapper) throws Exception {
    	
    	String idUser = unityKnowledgeWithRobotWrapper.getIdUser();
    	String idRobot = unityKnowledgeWithRobotWrapper.getIdRobot();
    	UnityKnowledgeObject unity = unityKnowledgeWithRobotWrapper.getUnity();
    	
    	// creem la unity
    	UnityKnowledgeObject result = howDYLService.createUnity(idUser, idRobot, unity);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error al crear la unitat");
    	} else {
    		result.setState("OK");
    	}
        
    	return result;
        
    }
    
    @RequestMapping(value = "/howdyl/removeUnity", method = RequestMethod.GET)
    public UnityKnowledgeObject removeUnity(@RequestParam("id_unity") String idUnity) throws Exception {
    	
    	UnityKnowledgeObject result = howDYLService.removeUnity(idUnity);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error a l'esborrar la unitat");
    	} else {
    		result.setState("OK");
    	}
        
    	return result;
        
    }
    
    @RequestMapping(value = "/howdyl/createTag", method = RequestMethod.POST)
    public Tag createTag(@RequestBody TagWithUnityKnowlegdeWrapper tagWithUnityKnowlegdeWrapper) throws Exception {
    	
    	String idUser = tagWithUnityKnowlegdeWrapper.getIdUser();
    	String idRobot = tagWithUnityKnowlegdeWrapper.getIdRobot();
    	String idUnity = tagWithUnityKnowlegdeWrapper.getIdUnity();
    	Tag tag = tagWithUnityKnowlegdeWrapper.getTag();
    	
    	UnityKnowledgeObject unity = howDYLService.getUnity("id", idUser, "id", idRobot, "id", idUnity);
    	
    	Tag result = howDYLService.createTag(unity, tag);
    	
    	if(result == null) {
    		result =  new Tag();
    		result.setState("KO");
    		result.setMessage("Error al crear el tag");
    	} else {
    		result.setState("OK");
    	}
        
    	return result;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeTag", method = RequestMethod.GET)
    public Tag removeTag(@RequestParam("id_tag") String idTag) throws Exception {
    	
    	Tag result = howDYLService.removeTag(idTag);
    	
    	if(result == null) {
    		result =  new Tag();
    		result.setState("KO");
    		result.setMessage("Error a l'esborrar el tag");
    	} else {
    		result.setState("OK");
    	}
        
    	return result;
    	
    }
    
    @RequestMapping(value = "/howdyl/createRelation", method = RequestMethod.POST)
    public UnityKnowledgeObject createRelation(@RequestBody UnityRelationWithRobotWrapper unityRelationWithRobotWrapper) throws Exception {
    	
    	String idUser = unityRelationWithRobotWrapper.getIdUser();
    	String idRobot = unityRelationWithRobotWrapper.getIdRobot();
    	String idUnity = unityRelationWithRobotWrapper.getIdUnity();
    	UnityKnowledgeObject unityRelation = unityRelationWithRobotWrapper.getUnityRelation();
    	
    	UnityKnowledgeObject result = howDYLService.createUnity(unityRelation);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error al crear la unitat de relacio");
    		return result;
    	} else {
    		result.setState("OK");
    	}
        
    	UnityKnowledgeObject unity = howDYLService.getUnity("id", idUser, "id", idRobot, "id", idUnity);
	    	
	    unity.addUnity(result);
	    
	    unity = howDYLService.updateUnity(unity);
	    
    	return result;
    	
    }
    
    @RequestMapping(value = "/howdyl/removeRelation", method = RequestMethod.GET)
    public UnityKnowledgeObject removeRelation(@RequestParam("id_unity") String idUnity, @RequestParam("id_unity_relation") String idUnityRelation) throws Exception {

    	UnityKnowledgeObject result = howDYLService.removeRelation(idUnityRelation);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error a l'esborrar la unitat de relacio");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
	    
    }
    
    @RequestMapping(value = "/howdyl/user/{idUser}", method = RequestMethod.GET)
    public User getUser(@PathVariable String idUser) throws Exception {
    	
    	User result = howDYLService.getUser("id", idUser);
    	
    	if(result == null) {
    		result =  new User();
    		result.setState("KO");
    		result.setMessage("Error al consultar l'usuari");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	   
    }
    
    @RequestMapping(value = "/howdyl/userByNick/{nick}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String nick) throws Exception {
    	
    	User result = howDYLService.getUserByNick(nick);
    	
    	if(result == null) {
    		result =  new User();
    		result.setState("KO");
    		result.setMessage("Error al consultar l'usuari");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	   
    }
    
    @RequestMapping(value = "/howdyl/validateUser", method = RequestMethod.POST)
    public User validateUser(@RequestBody UserWrapper userWrapper) throws Exception {
    	
    	String user = userWrapper.getUser();
    	String password = userWrapper.getPassword();
    	
    	User result = howDYLService.validateUser(user, password);
    	
    	if(result == null) {
    		result =  new User();
    		result.setState("KO");
    		result.setMessage("Error al validar l'usuari");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	
    }
    
    @RequestMapping(value = "/howdyl/robot/{idUser}/{idRobot}", method = RequestMethod.GET)
    public Robot getRobot(@PathVariable String idUser, @PathVariable String idRobot) throws Exception {
    	
    	Robot result = howDYLService.getRobot("id", idUser, "id", idRobot);
    	
    	if(result == null) {
    		result =  new Robot();
    		result.setState("KO");
    		result.setMessage("Error al consultar el robot");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	   
    }
    
    @RequestMapping(value = "/howdyl/robotByName/{robot}", method = RequestMethod.GET)
    public Robot getRobotByName(@PathVariable String robot) throws Exception {
    	
    	Robot result = howDYLService.getRobotByName(robot);
    	
    	if(result == null) {
    		result =  new Robot();
    		result.setState("KO");
    		result.setMessage("Error al consultar el robot");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	
    }

    @RequestMapping(value = "/howdyl/unity/{idUser}/{idRobot}/{idUnity}", method = RequestMethod.GET)
    public UnityKnowledgeObject getUnity(@PathVariable String idUser, @PathVariable String idRobot, @PathVariable String idUnity) throws Exception {
    	
    	UnityKnowledgeObject result = howDYLService.getUnity("id", idUser, "id", idRobot, "id", idUnity);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error al consultar la unitat");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	   
    }
    
    @RequestMapping(value = "/howdyl/unityByConcept/{idUser}/{idRobot}/{concept}", method = RequestMethod.GET)
    public UnityKnowledgeObject getUnityByConcept(@PathVariable String idUser, @PathVariable String idRobot, @PathVariable String concept) throws Exception {
    	
    	UnityKnowledgeObject result = howDYLService.getUnity("id", idUser, "id", idRobot, "concept", concept);
    	
    	if(result == null) {
    		result =  new UnityKnowledgeObject();
    		result.setState("KO");
    		result.setMessage("Error al consultar la unitat");
    	} else {
    		result.setState("OK");
    	}
    	
    	return result;
    	   
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
    public List<Robot> getListRobots(@RequestParam("id_user") String idUser) throws InterruptedException {

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
        compositeDisposable.add(Flowable.just(howDYLService.getListRobots(idUser)).subscribeWith(subscriber));

        return listRobots;
        
    }
    
    @RequestMapping(value = "/howdyl/state", method = RequestMethod.GET)
    public String getState() throws Exception {
    	
    	return "OK";
    	
    }
    
}
