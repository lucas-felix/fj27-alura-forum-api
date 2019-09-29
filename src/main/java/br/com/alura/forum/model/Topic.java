package br.com.alura.forum.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topic {

	private String shortDescription;
	private Instant lastUpdate = Instant.now();
	
	private User owner;
	private Course course;
	
	private List<Answer> answers = new ArrayList<>();
	
	/**
	 * @deprecated frameworks eyes only
	 */
	@Deprecated
	public Topic() {
		
	}

	public Topic(String shortDescription, User owner, Course course) {
		super();
		this.shortDescription = shortDescription;
		this.owner = owner;
		this.course = course;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
	}

	public User getOwner() {
		return owner;
	}

	public Course getCourse() {
		return course;
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(answers);
	}

}
