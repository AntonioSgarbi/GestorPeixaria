package tech.antoniosgarbi.gestorpeixaria.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSpringConfig {

    @Value("${spring.mail.username}")
    private String mailSpringUsername;

    @Bean
    public String mailSpringUsername() {
        return mailSpringUsername;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        return new JavaMailSenderImpl();
    }

}
