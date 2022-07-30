package tech.antoniosgarbi.gestorpeixaria.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import tech.antoniosgarbi.gestorpeixaria.service.contract.RestClientService;
import tech.antoniosgarbi.gestorpeixaria.service.impl.MailGunServiceImpl;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class MailGunServiceTest {
    @Mock
    private RestClientService restClientService;
    @InjectMocks
    private MailGunServiceImpl underTest;

    private static String mailGunAPIMessagesUrl;
    private static String mailGunAPIUsername;
    private static String mailGunAPIPassword;

    @Test
    @DisplayName("Should send email when receiving recipient, subject and message")
    void sendText0() {
        String from = "gestor.peixaria@outlook.com";
        String to = "to";
        String subject = "subject";
        String body = "body";

        underTest.sendText(to, subject, body);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", from);
        map.add("to", to);
        map.add("subject", subject);
        map.add("text", body);

        verify(restClientService).postWithBasicAuth(null, map, mailGunAPIUsername, mailGunAPIPassword);
    }

}
