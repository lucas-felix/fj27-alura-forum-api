package br.com.alura.forum.controller.dto.output;

public class TopicDashboardOutputDto {

	private String categoryName;
	private String[] subcategories;
	private int allTopics;
	private int lastWeekTopics;
	private int unansweredTopics;
	
	public TopicDashboardOutputDto(CategoryOutputDto categoryDto, int allTopics, int lastWeekTopics,
			int unansweredTopics) {
		this.categoryName = categoryDto.getCategoryName();
		this.subcategories = categoryDto.getSubcategories();
		this.allTopics = allTopics;
		this.lastWeekTopics = lastWeekTopics;
		this.unansweredTopics = unansweredTopics;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String[] getSubcategories() {
		return subcategories;
	}

	public int getAllTopics() {
		return allTopics;
	}

	public int getLastWeekTopics() {
		return lastWeekTopics;
	}

	public int getUnansweredTopics() {
		return unansweredTopics;
	}

}
