package org.dmr.domain;

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

	void addRelation(UnityKnowledgeType<T> unity);
	
	void removeRelation(UnityKnowledgeType<T> unity);

	void modifyDescription(String description);

}
