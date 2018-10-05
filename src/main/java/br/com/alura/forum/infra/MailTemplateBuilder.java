package br.com.alura.forum.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailTemplateBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(NewReplyMessageInfo messageInfo) {

        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("topicOwnerName", messageInfo.getTopicOwnerName());
        thymeleafContext.setVariable("topicShortDescription", messageInfo.getTopicShortDescription());

        thymeleafContext.setVariable("answerAuthor", messageInfo.getAnswerAuthor());
        thymeleafContext.setVariable("answerContent", messageInfo.getAnswerContent());
        thymeleafContext.setVariable("answerCreationInstant", messageInfo.getAnswerCreationInstant());

        return templateEngine.process("email-template", thymeleafContext);
    }
}
