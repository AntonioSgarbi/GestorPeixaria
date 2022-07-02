package tech.antoniosgarbi.gestorpeixaria.service.contract;

public interface MailService {

        public void sendUserPassword(String to, String subject, String body);

}
