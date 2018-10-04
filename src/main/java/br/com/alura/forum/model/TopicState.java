package br.com.alura.forum.model;

public interface TopicState {

    void registerNewReply(Topic topic, Answer newReply);
	void markAsSolved(Topic topic, Answer solution);
	void close(Topic topic);
}
