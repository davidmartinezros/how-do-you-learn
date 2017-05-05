package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.Robot;
import org.dmr.domain.impl.UnityKnowledgeObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface RobotRepository extends MongoRepository<Robot, String> {

	public List<Robot> findAll();
	
    public Robot findByName(String name);
    
    @Query(value = "{ $and: [ { id: ?0 } , { unities.concept: ?1 } ] }")
    public UnityKnowledgeObject findUnityKnowledgeInRobot(String idRobot, Object concept);

}