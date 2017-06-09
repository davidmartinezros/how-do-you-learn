package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface TagRepository extends MongoRepository<Tag, String> {

	public List<Tag> findAll();
	
	public Tag findById(String id);
	
    public Tag findByTag(String tag);

}