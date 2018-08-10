package br.com.alura.forum.model;

public class User {

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
