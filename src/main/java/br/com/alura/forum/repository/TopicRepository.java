package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.TopicStatus;

public interface TopicRepository extends Repository<Topic, Long> {

	@Query("select t from Topic t")
	List<Topic> list();
	
	List<Topic> findAll();

	List<Topic> findByStatus(TopicStatus status);
 
}
