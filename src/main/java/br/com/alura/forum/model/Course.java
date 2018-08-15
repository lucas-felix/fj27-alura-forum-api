package br.com.alura.forum.model;

public class Course {

	private String name;
	private Category subcategory;
	
	/**
	 * @deprecated
	 */
	public Course() {}

	public Course(String name, Category subcategory) {
		super();
		this.name = name;
		this.subcategory = subcategory;
	}

	public String getName() {
		return name;
	}

	public Category getSubcategory() {
		return subcategory;
	}
}
