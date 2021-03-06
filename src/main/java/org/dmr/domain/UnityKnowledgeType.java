package org.dmr.domain;

import org.dmr.domain.impl.Tag;
import org.dmr.domain.impl.UnityKnowledgeObject;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UnityKnowledgeType<T> extends UnityKnowledgeTypeBase<T> {

	void addTag(Tag tag);
	
	void removeTag(Tag tag);

	void addUnity(UnityKnowledgeObject unity);
	
	void removeUnity(UnityKnowledgeObject unity);

	void modifyDescription(String description);
	
	void modifyImage(byte[] image);

}
