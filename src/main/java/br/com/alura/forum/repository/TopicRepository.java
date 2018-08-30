package br.com.alura.forum.repository;

import java.util.List;

import br.com.alura.forum.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.TopicStatus;

public interface TopicRepository extends Repository<Topic, Long>, JpaSpecificationExecutor<Topic> {

	@Query("select t from Topic t")
	List<Topic> list();

	List<Topic> findAll();
}
