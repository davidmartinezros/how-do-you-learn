package org.dmr.services.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.dmr.services.ConstructPhrasesService;
import org.dmr.services.HowDYLService;
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
public class ConstructPhrasesServiceImpl implements ConstructPhrasesService {
	
	@Autowired
	HowDYLService howDYLService;

    public ConstructPhrasesServiceImpl() {
        
    }
    
    @Override
    public void constructPhrase(String theme, String phrase) throws Exception {
    	
    	String path = "/deeplearning4j/" + theme + "_PHRASES_FILE.txt";
    	
    	String phraseToWrite = "";
    	
    	Path p = Paths.get(path);
    	File file = p.toFile();
    	if(!file.exists()) {
    		file.createNewFile();
    	} else {
    		phraseToWrite = System.lineSeparator();
    	}
    	
    	phraseToWrite += phrase;
    	
    	try (BufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {
    	    writer.write(phraseToWrite);
    	} catch (IOException ioe) {
    	    System.err.format("IOException: %s%n", ioe);
    	}
    
    }
    	
}
