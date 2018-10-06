package br.com.alura.forum.service.infra;

import br.com.alura.forum.infra.MailTemplateBuilder;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NewReplyMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailTemplateBuilder mailTemplateBuilder;

    @Async
    public void send(Answer answer) {
        Topic answeredTopic = answer.getTopic();

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(answeredTopic.getOwnerEmail());
            messageHelper.setSubject("Novo comentário em: " + answeredTopic.getShortDescription());

            String messageContent = getMessageContent(answer);
            messageHelper.setText(messageContent, true);
        };

        try {
            mailSender.send(messagePreparator);

        } catch (MailException e) {
            throw new MailServiceException("Não foi possível enviar email.");
        }
    }

    private String getMessageContent(Answer answer) {

        Topic answeredTopic = answer.getTopic();

        return mailTemplateBuilder
                .recipientName(answeredTopic.getOwnerName())
                .topic(answeredTopic.getShortDescription())
                .answerAuthor(answer.getOwnerName())
                .answerInstant(answer.getCreationTime())
                .answer(answer.getContent())
                .build();
    }

}
