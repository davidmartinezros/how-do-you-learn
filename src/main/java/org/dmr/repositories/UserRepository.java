package org.dmr.repositories;

import java.util.List;

import org.dmr.domain.impl.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by davidmartinezros on 18/06/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findAll();
	
	public User findById(String id);
	
    public User findByNick(String nick);

}