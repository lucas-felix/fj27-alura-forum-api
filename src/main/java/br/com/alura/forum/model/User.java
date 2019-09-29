package br.com.alura.forum.model;

public class User {

	private String name;

	/**
	 * @deprecated frameworks eyes only
	 */
	@Deprecated
	public User() {	}
	
	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
