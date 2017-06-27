package org.dmr.domain.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by davidmartinezros on 18/06/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Document
public class Tag {
	
	// id de la base de dades mongoDB
	@Id
	private String id;

	public String tag;
	
	public Tag() {
		
	}
	
	public Tag(String tag) {
		
		this();
		// definicio tag
		this.tag = tag;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
