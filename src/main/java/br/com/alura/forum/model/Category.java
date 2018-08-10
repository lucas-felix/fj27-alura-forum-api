package br.com.alura.forum.model;

public class Category {

	private String name;
	
	/**
	 * @deprecated
	 */
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
