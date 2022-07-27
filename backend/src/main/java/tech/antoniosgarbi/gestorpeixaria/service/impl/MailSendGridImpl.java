package tech.antoniosgarbi.gestorpeixaria.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.service.contract.MailServiceStrategy;

import java.io.IOException;

@Service
@Primary
public class MailSendGridImpl implements MailServiceStrategy {
    private final SendGridAPI sendGridAPI;
    @Value("${personal.mail.fromMail}")
    private String fromEmail;
    @Value("${personal.mail.fromName}")
    private String fromName;

    public MailSendGridImpl(SendGridAPI sendGridAPI) {
        this.sendGridAPI = sendGridAPI;
    }

    @Override
    public void sendText(String to, String subject, String body) {
        Email from = new Email(fromEmail, fromName);
        Email toMail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, toMail, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridAPI.api(request);
            System.out.println(response.getBody());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendHTML(String to, String subject, String body) {

    }
}
