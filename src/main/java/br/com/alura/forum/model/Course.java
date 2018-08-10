package br.com.alura.forum.model;

public class Course {

	private String nome;
	private Subcategory subcategory;
	
	/**
	 * @deprecated
	 */
	public Course() {}

	public Course(String nome, Subcategory subcategory) {
		super();
		this.nome = nome;
		this.subcategory = subcategory;
	}

	public String getNome() {
		return nome;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}
}
