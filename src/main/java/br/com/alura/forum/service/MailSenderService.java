package br.com.alura.forum.service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class MailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendNewReplyEmail(Topic topic, Answer answer) {

        String topicOwnerName = topic.getOwner().getName();
        String topicOwnerEmail = topic.getOwner().getEmail();
        String topicShortDescription = topic.getShortDescription();
        String answerOwnerName = answer.getOwner().getName();

        MimeMessagePreparator messagePreparator = getMessagePreparator(topicOwnerName,
                topicOwnerEmail, topicShortDescription, answerOwnerName);

        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            logger.error("Não foi possível enviar email para o usuário {} com email {}",
                    topicOwnerName, topicOwnerEmail);
        }
    }

    private MimeMessagePreparator getMessagePreparator(String topicOwnerName,
            String topicOwnerEmail, String topicShortDescription, String answerOwnerName) {

        return (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(topicOwnerEmail);
            messageHelper.setSubject("Novo comentário em: " + topicShortDescription);
            messageHelper.setText("Olá " + topicOwnerName + "\n\n" +
                    "Há uma nova mensagem no fórum! " + answerOwnerName +
                    " comentou no tópico: " + topicShortDescription);
        };
    }
}
