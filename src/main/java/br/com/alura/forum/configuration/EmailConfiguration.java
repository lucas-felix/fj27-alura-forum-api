package br.com.alura.forum.configuration;

import br.com.alura.forum.model.Answer;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Configuration
@Profile("dev")
public class EmailConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(EmailConfiguration.class);

    private GreenMail smtpServer;

    @Value("${spring.mail.host}")
    private String hostAddress;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @PostConstruct
    public void setup() {
        ServerSetup serverSetup = new ServerSetup(Integer.parseInt(this.port),
                this.hostAddress, "smtp");

        this.smtpServer = new GreenMail(serverSetup);
        this.smtpServer.setUser(username, username, password);
        this.smtpServer.start();
    }

    @EventListener
    public void verifyEmail(Answer answer) {
        MimeMessage receivedMessage = this.smtpServer.getReceivedMessages()[0];

        try {

            logger.info((String) receivedMessage.getContent());
            logger.info("Email enviado para {} a partir da resposta do(a) {}",
                    answer.getTopic().getOwnerName(), answer.getOwnerName());

        } catch (IOException | MessagingException e) {
            throw new RuntimeException("Não foi possível recuperar email");
        }
    }

    @PreDestroy
    public void destroy() {
        this.smtpServer.stop();
    }
}
