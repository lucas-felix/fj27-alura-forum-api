package br.com.alura.forum.service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNewReplyEmail(User to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.getEmail());
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendNewReplyEmail(Topic topic, Answer answer) {

        String topicOwnerName = topic.getOwner().getName();
        String topicOwnerEmail = topic.getOwner().getEmail();
        String topicShortDescription = topic.getShortDescription();
        String answerOwnerName = answer.getOwner().getName();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(topicOwnerEmail);
        message.setSubject("Novo comentário em: " + topicShortDescription);

        message.setText("Olá " + topicOwnerName + "\n\n" +
                "Há uma nova mensagem no fórum! " + answerOwnerName +
                " comentou no tópico: " + topicShortDescription);

        mailSender.send(message);
    }
}
