package org.dmr.domain.impl;

/**
 * Created by davidmartinezros on 18/06/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public class UnityRelationWithRobotWrapper {
	
	String idUser;
	
	String idRobot;
	
	String idUnity;

	UnityKnowledgeObject unityRelation;
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public String getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}
	
	public String getIdUnity() {
		return idUnity;
	}

	public void setIdUnity(String idUnity) {
		this.idUnity = idUnity;
	}

	public UnityKnowledgeObject getUnityRelation() {
		return unityRelation;
	}

	public void setUnityRelation(UnityKnowledgeObject unityRelation) {
		this.unityRelation = unityRelation;
	}
	
}
