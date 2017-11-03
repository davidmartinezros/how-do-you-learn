package org.dmr.services;

import java.util.Collection;

/**
 * Created by davidmartinezros on 01/11/2017.
 */
public interface TensorFlowService {
	
	public Collection<String> execute(String word) throws Exception;
	
	public Collection<String> train(String word) throws Exception;
    
}
