package br.com.alura.forum.model;

public class Subcategory {

	private String name;
	private Category category;

	/**
	 * @deprecated
	 */
	public Subcategory() {}
	
	public Subcategory(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

}
