package org.dmr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.dmr.domain.impl.UnityKnowledgeString;
import org.dmr.services.CallUrlService;
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
public class LRURestController {
    private CallUrlService lruService;
    private List<UnityKnowledgeString> linkedHashMap;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LRURestController(CallUrlService lruService) {
    	
        this.lruService = lruService;
        this.linkedHashMap = new ArrayList<>();
        
    }

    @RequestMapping(value = "/lru/add", method = RequestMethod.POST)
    public UnityKnowledgeString addUnityKnowledgeInCache(@RequestParam("concept") String concept, @RequestParam("description") String description, @RequestParam("image") byte[] image) throws Exception {
    	
    	UnityKnowledgeString unity = new UnityKnowledgeString(concept, description, image);
    	
    	unity = lruService.addUnityKnowledgeStringInLRU(concept, unity);
    	
        return unity;
        
    }
    
    @RequestMapping(value = "/lru/addTag", method = RequestMethod.POST)
    public UnityKnowledgeString addRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("tag") String tag) throws Exception {
    	
    	UnityKnowledgeString unity = lruService.getUnityKnowledgeStringFromLRU(concept);
    	
    	unity.addTag(tag);
    	
    	return unity;
    	
    }
    
    @RequestMapping(value = "/lru/addRelation", method = RequestMethod.POST)
    public UnityKnowledgeString addRelationInUnityKnowledge(@RequestParam("concept") String concept, @RequestParam("concept_relation") String conceptRelation, @RequestParam("description_relation") String descriptionRelation, @RequestParam("image_relation") byte[] imageRelation) throws Exception {
    	
    	UnityKnowledgeString unity = lruService.getUnityKnowledgeStringFromLRU(concept);
    	
    	UnityKnowledgeString unityRelation = new UnityKnowledgeString(conceptRelation, descriptionRelation, imageRelation);
    	
    	unity.createRelation(unityRelation);
    	
    	return unity;
    	
    }

    @RequestMapping(value = "/lru/{key}")
    public UnityKnowledgeString getStringByKey(@PathVariable String key) throws Exception {
    	
    	UnityKnowledgeString unity = lruService.getUnityKnowledgeStringFromLRU(key);
    	
    	return unity;
    
    }

    @RequestMapping(value = "/lru/state")
    public List<UnityKnowledgeString> getLRUState() throws InterruptedException {

        ResourceSubscriber<List<UnityKnowledgeString>> subscriber = new ResourceSubscriber<List<UnityKnowledgeString>>() {
            @Override
            public void onNext(List<UnityKnowledgeString> s) {
                linkedHashMap = s;
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
        compositeDisposable.add(Flowable.just(lruService.getLRUState()).subscribeWith(subscriber));

        Thread.sleep(5000);

        return linkedHashMap;
        
    }
    
}
