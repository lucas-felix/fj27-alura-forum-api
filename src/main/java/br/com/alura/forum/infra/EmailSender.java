package br.com.alura.forum.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNewReplyEmail(NewReplyMessageInfo messageInfo) {

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(messageInfo.getTopicOwnerEmail());
            messageHelper.setSubject("Novo comentário em: " + messageInfo.getTopicShortDescription());
            messageHelper.setText("Olá " + messageInfo.getTopicOwnerName() + "\n\n" +
                    "Há uma nova mensagem no fórum! " + messageInfo.getAnswerAuthor() +
                    " comentou no tópico: " + messageInfo.getTopicShortDescription());
        };

        javaMailSender.send(messagePreparator);
    }
}
