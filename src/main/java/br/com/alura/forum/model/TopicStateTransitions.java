package br.com.alura.forum.model;

public interface TopicStateTransitions {

	void makeNotAnswered(Topic topic);
	void makeNotSolved(Topic topic);
	void makeSolved(Topic topic);
	void makeClosed(Topic topic);
}
