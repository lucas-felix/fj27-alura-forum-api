package br.com.alura.forum.controller.dto.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.CourseRepository;
import org.hibernate.validator.constraints.Length;

public class NewTopicInputDto {

	@NotEmpty
	private String shortDescription;
	
	@Length(min = 15)
	private String content;
	
	@NotNull
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
