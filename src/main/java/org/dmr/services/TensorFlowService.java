package org.dmr.services;

import java.util.Collection;

/**
 * Created by davidmartinezros on 01/11/2017.
 */
public interface TensorFlowService {
	
	public Collection<String> execute(String word, String theme, String version, String data) throws Exception;
	
	public Collection<String> train(String word, String theme, String version, String data) throws Exception;
    
}
