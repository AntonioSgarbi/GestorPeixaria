package tech.antoniosgarbi.gestorpeixaria.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.yaml")
public class MailGunConfig {

    @Value("${mailgun.api.username}")
    private String mailGunAPIUsername;
    @Value("${mailgun.api.password}")
    private String mailGunAPIPassword;
    @Value("${mailgun.api.base.url}")
    private String mailGunAPIBaseUrl;
    @Value("${mailgun.api.messages.url}")
    private String mailGunAPIMessagesUrl;

    @Bean
    public String mailGunAPIUsername() {
        System.out.println("mailGunAPIUsername: " + mailGunAPIUsername);
        return this.mailGunAPIUsername;
    }

    @Bean
    public String mailGunAPIPassword() {
        System.out.println("mailGunAPIPassword: " + mailGunAPIPassword);
        return this.mailGunAPIPassword;
    }

    @Bean
    public String mailGunAPIBaseUrl() {
        System.out.println("mailGunAPIBaseUrl: " + mailGunAPIBaseUrl);
        return this.mailGunAPIBaseUrl;
    }

    @Bean
    public String mailGunAPIMessagesUrl() {
        System.out.println("mailGunAPIMessagesUrl: " + mailGunAPIMessagesUrl);
        return this.mailGunAPIMessagesUrl;
    }
}

