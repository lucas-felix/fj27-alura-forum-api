package br.com.alura.forum.service;

import br.com.alura.forum.infra.EmailSender;
import br.com.alura.forum.infra.NewReplyMessageInfo;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class NewReplyMailService {

    private static final Logger logger = LoggerFactory.getLogger(NewReplyMailService.class);

    @Autowired
    private EmailSender emailSender;

    public void prepareAndSend(Topic topic, Answer answer) {

        final NewReplyMessageInfo messageInfo = new NewReplyMessageInfo(
                topic.getOwner().getName(),
                topic.getOwner().getEmail(),
                topic.getShortDescription(),
                answer.getOwnerName()
        );

        try {
            emailSender.sendNewReplyEmail(messageInfo);

        } catch (MailException e) {
            logger.error("Não foi possível notificar o usuário {} enviando email para {}",
                    topic.getOwner().getName(), topic.getOwner().getEmail());
        }
    }
}
