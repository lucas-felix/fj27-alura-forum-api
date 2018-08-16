package br.com.alura.forum.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String shortDescription;
	private Instant lastUpdate = Instant.now();
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Course course;
	
	@OneToMany
	private List<Answer> answers = new ArrayList<>();
	
	/**
	 * @deprecated
	 */
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
