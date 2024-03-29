package tech.antoniosgarbi.gestorpeixaria.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("mailgun")
@Configuration
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
        return this.mailGunAPIUsername;
    }

    @Bean
    public String mailGunAPIPassword() {
        return this.mailGunAPIPassword;
    }

    @Bean
    public String mailGunAPIBaseUrl() {
        return this.mailGunAPIBaseUrl;
    }

    @Bean
    public String mailGunAPIMessagesUrl() {
        return this.mailGunAPIMessagesUrl;
    }
}

