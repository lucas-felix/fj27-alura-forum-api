package br.com.alura.forum.model;

public class Course {

	private String name;
	private Subcategory subcategory;
	
	/**
	 * @deprecated
	 */
	public Course() {}

	public Course(String name, Subcategory subcategory) {
		super();
		this.name = name;
		this.subcategory = subcategory;
	}

	public String getName() {
		return name;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}
}
