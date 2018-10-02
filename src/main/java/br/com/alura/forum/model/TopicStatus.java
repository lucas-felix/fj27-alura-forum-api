package br.com.alura.forum.model;

public enum TopicStatus implements TopicStateTransitions {

	NOT_ANSWERED {

		@Override
		public void makeNotAnswered(Topic topic) {
			throw new RuntimeException("Tópico já se encontra não respondido");
		}

		@Override
		public void makeNotSolved(Topic topic) {
			topic.setStatus(TopicStatus.NOT_SOLVED);
		}

		@Override
		public void makeSolved(Topic topic) {
			topic.setStatus(TopicStatus.SOLVED);
		}

		@Override
		public void makeClosed(Topic topic) {
			topic.setStatus(TopicStatus.CLOSED);
		}
	},
	
	NOT_SOLVED {

		@Override
		public void makeNotAnswered(Topic topic) {
			throw new RuntimeException("Tópico não pode voltar ao estado " + TopicStatus.NOT_ANSWERED);
		}

		@Override
		public void makeNotSolved(Topic topic) {
			throw new RuntimeException("Tópico já se encontra não solucionado");
		}

		@Override
		public void makeSolved(Topic topic) {
			topic.setStatus(TopicStatus.SOLVED);
		}

		@Override
		public void makeClosed(Topic topic) {
			topic.setStatus(TopicStatus.CLOSED);
		}
	},
	
	SOLVED {

		@Override
		public void makeNotAnswered(Topic topic) {
			throw new RuntimeException("Tópico não pode voltar ao estado " + TopicStatus.NOT_ANSWERED);
		}

		@Override
		public void makeNotSolved(Topic topic) {
			throw new RuntimeException("Tópico não pode voltar ao estado " + TopicStatus.NOT_SOLVED);
		}

		@Override
		public void makeSolved(Topic topic) {
			throw new RuntimeException("Tópico já se encontra solucionado");
		}

		@Override
		public void makeClosed(Topic topic) {
			throw new RuntimeException("Tópicos resolvidos não podem ser fechados");
		}
		
	},
	
	CLOSED {

		@Override
		public void makeNotAnswered(Topic topic) {
			throw new RuntimeException("O tópico se encontra fechado");
		}

		@Override
		public void makeNotSolved(Topic topic) {
			throw new RuntimeException("O tópico se encontra fechado");
		}

		@Override
		public void makeSolved(Topic topic) {
			throw new RuntimeException("O tópico se encontra fechado");
		}

		@Override
		public void makeClosed(Topic topic) {
			throw new RuntimeException("O tópico se encontra fechado");
		}
	};
}
