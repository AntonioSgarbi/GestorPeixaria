package tech.antoniosgarbi.gestorpeixaria.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class ApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Fish Stock")
                        .description("Fish Stock API")
                        .version("1.0.0")
                        .contact(new Contact().name("Antonio Sgarbi").email("antonio.sgarbi@hotmail.com"))
                        .license(new License().name("GPLv3")));
    }

}
