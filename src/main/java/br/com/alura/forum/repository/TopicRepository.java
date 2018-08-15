package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.Topic;

public interface TopicRepository extends Repository<Topic, Long> {

	List<Topic> list(); // ???
 
}
