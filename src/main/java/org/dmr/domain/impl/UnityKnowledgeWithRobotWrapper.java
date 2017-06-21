package org.dmr.domain.impl;

/**
 * Created by davidmartinezros on 18/06/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
public class UnityKnowledgeWithRobotWrapper {
	
	String idUser;
	
	String idRobot;
	
	UnityKnowledgeObject unity;
	
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

	public UnityKnowledgeObject getUnity() {
		return unity;
	}

	public void setUnity(UnityKnowledgeObject unity) {
		this.unity = unity;
	}
	
}
