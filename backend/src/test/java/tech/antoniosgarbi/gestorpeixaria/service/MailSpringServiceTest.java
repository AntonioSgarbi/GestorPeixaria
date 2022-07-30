package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import tech.antoniosgarbi.gestorpeixaria.service.impl.MailSpringServiceImpl;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class MailSpringServiceTest {
    @Mock
    private JavaMailSender emailSender;
    @InjectMocks
    private MailSpringServiceImpl underTest;

    @Test
    @DisplayName("Should send email when receiving recipient, subject and message")
    void sendText0() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("to");
        message.setSubject("subject");
        message.setText("body");

        underTest.sendText("to", "subject", "body");
        verify(emailSender).send(message);
    }

}
