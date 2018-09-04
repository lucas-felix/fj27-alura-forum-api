package br.com.alura.forum.model;

public enum TopicStatus implements TopicStateTransitions {

	NOT_ANSWERED {

		@Override
		public void makeNotAnswered(Topic topic) {
			
		}

		@Override
		public void makeNotSolved(Topic topic) {
			
		}

		@Override
		public void makeSolved(Topic topic) {
			
		}

		@Override
		public void makeClosed(Topic topic) {
			
		}
	},
	
	NOT_SOLVED {

		@Override
		public void makeNotAnswered(Topic topic) {
			
		}

		@Override
		public void makeNotSolved(Topic topic) {
			
		}

		@Override
		public void makeSolved(Topic topic) {
			
		}

		@Override
		public void makeClosed(Topic topic) {
			
		}
	},
	
	SOLVED {

		@Override
		public void makeNotAnswered(Topic topic) {
			
		}

		@Override
		public void makeNotSolved(Topic topic) {
			
		}

		@Override
		public void makeSolved(Topic topic) {
			
		}

		@Override
		public void makeClosed(Topic topic) {
			
		}
		
	},
	
	CLOSED {

		@Override
		public void makeNotAnswered(Topic topic) {
			
		}

		@Override
		public void makeNotSolved(Topic topic) {
			
		}

		@Override
		public void makeSolved(Topic topic) {
			
		}

		@Override
		public void makeClosed(Topic topic) {
			
		}
	};
}
