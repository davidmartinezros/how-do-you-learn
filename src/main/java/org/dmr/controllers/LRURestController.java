package org.dmr.controllers;

import java.util.LinkedHashMap;

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
    private LinkedHashMap<Integer,UnityKnowledgeString> linkedHashMap;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LRURestController(CallUrlService lruService) {
    	
        this.lruService = lruService;
        this.linkedHashMap = new LinkedHashMap<>();
        
    }

    @RequestMapping(value = "/lru/add", method = RequestMethod.POST)
    public int addStringInCache(@RequestParam("concept") String string) {
    	
    	UnityKnowledgeString unity = new UnityKnowledgeString(string, null);
    	
        return lruService.addStringInLRU(unity);
        
    }

    @RequestMapping(value = "/lru/{key}")
    public String getStringByKey(@PathVariable int key) throws Exception {
    	
    	UnityKnowledgeString unity = lruService.getStringFromLRU(key);
    	
    	return unity.getValue();
    
    }

    @RequestMapping(value = "/lru/state")
    public LinkedHashMap<Integer,UnityKnowledgeString> getLRUState() throws InterruptedException {

        ResourceSubscriber<LinkedHashMap<Integer,UnityKnowledgeString>> subscriber = new ResourceSubscriber<LinkedHashMap<Integer,UnityKnowledgeString>>() {
            @Override
            public void onNext(LinkedHashMap<Integer,UnityKnowledgeString> s) {
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
