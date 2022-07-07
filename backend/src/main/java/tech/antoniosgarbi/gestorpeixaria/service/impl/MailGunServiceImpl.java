package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import tech.antoniosgarbi.gestorpeixaria.service.contract.MailServiceAdapter;
import tech.antoniosgarbi.gestorpeixaria.service.contract.RestClientService;

@Service
@Primary
@Profile("heroku")
public class MailGunServiceImpl implements MailServiceAdapter {

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
        MultiValueMap<String, String> map = getPostRequestObject(from, to, subject);
        map.add("text", body);
        sendEmail(map);

    }

    @Override
    public void sendHTML(String to, String subject, String body) {
        MultiValueMap<String, String> map = getPostRequestObject(from, to, subject);
        map.add("html", body);
        sendEmail(map);
    }

    private void sendEmail(MultiValueMap<String, String> map) {
        this.restClient.post(this.messagesUrl, map, this.username, this.password);
    }

    private MultiValueMap<String, String> getPostRequestObject(String from, String to, String subject) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", from);
        map.add("to", to);
        map.add("subject", subject);
        return map;
    }

}