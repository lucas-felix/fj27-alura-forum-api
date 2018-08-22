package br.com.alura.forum.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private Category category;
	
	/**
	 * @deprecated
	 */
	public Category() {}
	
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
