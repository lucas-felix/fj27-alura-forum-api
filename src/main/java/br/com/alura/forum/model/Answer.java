package br.com.alura.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Topic topic;

	/**
	 * @deprecated
	 */
	public Answer() {
	}

	public Answer(Topic topic) {
		this.topic = topic;
	}
	
	public Topic getTopic() {
		return topic;
	}
}
