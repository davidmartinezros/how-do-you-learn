package org.dmr.services.impl;

import org.dmr.learningmachine.Word2VecUptrainingExample;
import org.dmr.services.HowDYLService;
import org.dmr.services.TensorFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Service
public class TensorFlowServiceImpl implements TensorFlowService {
	
	@Autowired
	HowDYLService howDYLService;

    public TensorFlowServiceImpl() {
        
    }
    
    public void aaa() throws Exception {
    	
    	Word2VecUptrainingExample.main(null);
    
    } 
    	
}
