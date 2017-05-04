package org.dmr.domain;

import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UnityKnowledgeType<T> extends UnityKnowledgeTypeBase<T> {

	void addTag(String tag);
	
	void removeTag(String tag);

	void addRelation(UnityKnowledgeObject unity);
	
	void removeRelation(UnityKnowledgeObject unity);

	void modifyDescription(String description);
	
	void modifyImage(byte[] image);

}
