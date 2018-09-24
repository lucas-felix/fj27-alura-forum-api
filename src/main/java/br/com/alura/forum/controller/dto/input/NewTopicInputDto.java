package br.com.alura.forum.controller.dto.input;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.CourseRepository;

public class NewTopicInputDto {

	private String shortDescription;
	private String content;
	private Long courseId;

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Topic build(User owner, CourseRepository courseRepository) {
		
		Course course = courseRepository.findById(this.courseId);
		return new Topic(this.shortDescription, this.content, owner, course);
	}

}
