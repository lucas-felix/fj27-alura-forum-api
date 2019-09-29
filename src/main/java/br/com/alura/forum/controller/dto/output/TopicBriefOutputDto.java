package br.com.alura.forum.controller.dto.output;

import br.com.alura.forum.model.Topic;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class TopicBriefOutputDto {

	private String shortDescription;
	private long secondsSinceLastUpdate;
	private String ownerName;
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	private int numberOfResponses;
	
	private TopicBriefOutputDto(Topic topic) {
		this.shortDescription = topic.getShortDescription();
		this.secondsSinceLastUpdate = getSecondsSince(topic.getLastUpdate());
		this.ownerName = topic.getOwner().getName();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.numberOfResponses = topic.getAnswers().size();
	}

	private TopicBriefOutputDto() {}

	private long getSecondsSince(Instant lastUpdate) {
		return Duration.between(lastUpdate, Instant.now())
				.get(ChronoUnit.SECONDS);
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public long getSecondsSinceLastUpdate() {
		return secondsSinceLastUpdate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getNumberOfResponses() {
		return numberOfResponses;
	}

	public static List<TopicBriefOutputDto> fromTopics(Topic... topics) {
		return stream(topics)
				.map(TopicBriefOutputDto::new)
				.collect(Collectors.toList());
	}

	public static List<TopicBriefOutputDto> fromTopics(List<Topic> topics) {
		return fromTopics(topics.toArray(Topic[]::new));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TopicBriefOutputDto that = (TopicBriefOutputDto) o;
		return secondsSinceLastUpdate == that.secondsSinceLastUpdate &&
				numberOfResponses == that.numberOfResponses &&
				Objects.equals(shortDescription, that.shortDescription) &&
				Objects.equals(ownerName, that.ownerName) &&
				Objects.equals(courseName, that.courseName) &&
				Objects.equals(subcategoryName, that.subcategoryName) &&
				Objects.equals(categoryName, that.categoryName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(shortDescription, secondsSinceLastUpdate, ownerName, courseName, subcategoryName, categoryName, numberOfResponses);
	}
}
