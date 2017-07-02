package org.dmr.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by davidmartinezros on 18/06/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@Document
public class User {

	// id de la base de dades mongoDB
	@Id
	private String id;
	// nick  de l'usuari
	private String nick;
	// contrasenya  de l'usuari
	private String password;
	// nom  de l'usuari
	private String firstName;
	// cognoms  de l'usuari
	private String lastName;
	// email de l'usuari
	private String email;
	// telefon de l'usuari
	private String telephone;
	// estat login
	@Transient
	private String state;
	// missatge login
	@Transient
	private String message;
	
	// llistats de robots que te l'usuari
	@DBRef
	private List<Robot> robots;
	
	public User() {
		
		robots = new ArrayList<Robot>();
		
	}
	
	public User(String nick, String password) {
		
		this();
		
		this.nick = nick;
		this.password = password;
		
	}
	
	public User(String nick, String password, String firstName, String lastName, String email, String telephone) {
		
		this(nick, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		
	}
	
	public User(String nick, String password, String firstName, String lastName, String email, String telephone, String state, String message) {
		
		this(nick, password, firstName, lastName, email, telephone);
		this.state = state;
		this.message = message;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Robot> getRobots() {
		return robots;
	}

	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}
	
	public void addRobot(Robot robot) {
        
    	if(!this.robots.contains(robot)) {
    		
    		this.robots.add(robot);
    		
    	}
    
    }
    
    public void removeRobot(Robot robot) {
        
    	if(this.robots.contains(robot)) {
    		
    		this.robots.remove(robot);
    		
    	}
    
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
