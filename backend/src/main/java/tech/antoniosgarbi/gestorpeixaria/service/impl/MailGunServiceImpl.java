package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import tech.antoniosgarbi.gestorpeixaria.service.contract.MailServiceStrategy;
import tech.antoniosgarbi.gestorpeixaria.service.contract.RestClientService;

@Service
@Profile("heroku")
public class MailGunServiceImpl implements MailServiceStrategy {

    private final RestClientService restClient;
    private final String password;
    private final String messagesUrl;
    private final String username;
    private final String from;

    public MailGunServiceImpl(RestClientService restClient, String mailGunAPIMessagesUrl, String mailGunAPIUsername,
                              String mailGunAPIPassword) {
        this.restClient = restClient;
        this.username = mailGunAPIUsername;
        this.password = mailGunAPIPassword;
        this.messagesUrl = mailGunAPIMessagesUrl;
        this.from = "gestor.peixaria@outlook.com";
    }

    @Override
    public void sendText(String to, String subject, String body) {
        MultiValueMap<String, String> map = this.getPostRequestObject(from, to, subject);
        map.add("text", body);
        sendEmail(map);

    }

    @Override
    public void sendHTML(String to, String subject, String body) {
        MultiValueMap<String, String> map = this.getPostRequestObject(from, to, subject);
        map.add("html", body);
        sendEmail(map);
    }

    protected void sendEmail(MultiValueMap<String, String> map) {
        this.restClient.postWithBasicAuth(this.messagesUrl, map, this.username, this.password);
    }

    private MultiValueMap<String, String> getPostRequestObject(String from, String to, String subject) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", from);
        map.add("to", to);
        map.add("subject", subject);
        return map;
    }

}