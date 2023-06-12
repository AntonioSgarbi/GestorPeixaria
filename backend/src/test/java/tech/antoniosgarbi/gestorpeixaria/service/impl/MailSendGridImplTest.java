package tech.antoniosgarbi.gestorpeixaria.service.impl;

import com.sendgrid.Request;
import com.sendgrid.SendGridAPI;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MailSendGridImplTest {

    @Mock
    private SendGridAPI sendGridAPI;

    @InjectMocks
    private MailSendGridImpl underTest;

    @Test
    void sendText() throws IOException {
        ReflectionTestUtils.setField(underTest, "fromEmail", "antonio@test.com");
        ReflectionTestUtils.setField(underTest, "fromName", "antonio");

        this.underTest.sendText("to", "subject", "body");

        verify(sendGridAPI).api(any(Request.class));
    }

    @Test
    void sendText2() throws IOException {
        ReflectionTestUtils.setField(underTest, "fromEmail", "antonio@test.com");
        ReflectionTestUtils.setField(underTest, "fromName", "antonio");

        when(this.sendGridAPI.api(any(Request.class))).thenThrow(new IOException("ex"));

        assertDoesNotThrow(() -> this.underTest.sendText("to", "subject", "body"));
    }
}