package tech.antoniosgarbi.gestorpeixaria.service.contract;

public interface MailServiceStrategy {
        void sendText(String to, String subject, String body);

}