package br.com.alura.forum.model;

import java.util.Optional;

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

	public String getCategoryName() {
		Optional<Category> possibleCategory = this.subcategory.getCategory();
		
		return possibleCategory
				.orElseThrow(() -> new IllegalStateException("Esta já é uma categoria mãe"))
				.getName(); 
	}
}
