package tech.antoniosgarbi.gestorpeixaria.service.contract;

public interface MailService {
        void sendSimpleMessage(String to, String subject, String text);

}