package br.com.alura.forum.controller.dto.output;

public class CategoryOutputDto {

	private String categoryName;
	private String[] subcategories;

	public CategoryOutputDto(String categoryName, String... subcategoryNames) {
		this.categoryName = categoryName;
		this.subcategories = subcategoryNames;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String[] getSubcategories() {
		return subcategories;
	}

}
