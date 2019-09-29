package br.com.alura.forum.model;

import java.util.Optional;

public class Category {

	private String name;
	private Category category;
	
	/**
	 * @deprecated frameworks eyes only
	 */
	@Deprecated
	Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(String name, Category category) {
		this(name);
		this.category = category; 
	}

	public String getName() {
		return name;
	}
	
	public Optional<Category> getCategory() {
		return Optional.ofNullable(this.category);
	}
}
