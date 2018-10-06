package br.com.alura.forum.infra;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailTemplateBuilder {

    @Autowired
    private TemplateEngine templateEngine;
    private Map<String, String> mailInfo = new HashMap<>();

    public MailTemplateBuilder recipientName(String topicOwnerName) {
        mailInfo.put("topicOwnerName", topicOwnerName);
        return this;
    }

    public MailTemplateBuilder topic(String topicShortDescription) {
        mailInfo.put("topicShortDescription", topicShortDescription);
        return this;
    }

    public MailTemplateBuilder answerAuthor(String answerAuthor) {
        mailInfo.put("answerAuthor", answerAuthor);
        return this;
    }

    public MailTemplateBuilder answerInstant(Instant answerCreationTime) {
        String instant = DateTimeFormatter.ofPattern("kk:mm")
                .withZone(ZoneId.of("America/Sao_Paulo"))
                .format(answerCreationTime);

        mailInfo.put("answerCreationInstant", instant);
        return this;
    }

    public MailTemplateBuilder answer(String answerContent) {
        mailInfo.put("answerContent", answerContent);
        return this;
    }

    public String build() {
        Context thymeleafContext = new Context();

        mailInfo.keySet().stream().forEach(
                info -> thymeleafContext.setVariable(info, mailInfo.get(info))
        );
        return templateEngine.process("email-template", thymeleafContext);
    }
}
