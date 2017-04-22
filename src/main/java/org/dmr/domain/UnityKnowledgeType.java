package org.dmr.domain;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public interface UnityKnowledgeType<K> extends UnityKnowledgeTypeBase {

	void addTag(String tag);

	void createRelation(UnityKnowledgeType<K> unity);

	void modifyDescription(String description);

}
