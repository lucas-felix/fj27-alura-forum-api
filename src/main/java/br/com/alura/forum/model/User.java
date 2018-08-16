package br.com.alura.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	/**
	 * @deprecated
	 */
	public User() {	}
	
	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
