package br.com.alura.forum.infra;

public class NewReplyMessageInfo {

    private String topicOwnerName;
    private String topicOwnerEmail;
    private String topicShortDescription;
    private String answerAuthor;

    public NewReplyMessageInfo(String topicOwnerName, String topicOwnerEmail,
            String topicShortDescription, String answerAuthor) {

        this.topicOwnerName = topicOwnerName;
        this.topicOwnerEmail = topicOwnerEmail;
        this.topicShortDescription = topicShortDescription;
        this.answerAuthor = answerAuthor;
    }

    public String getTopicOwnerName() {
        return topicOwnerName;
    }

    public String getTopicOwnerEmail() {
        return topicOwnerEmail;
    }

    public String getTopicShortDescription() {
        return topicShortDescription;
    }

    public String getAnswerAuthor() {
        return answerAuthor;
    }
}
