package tech.antoniosgarbi.gestorpeixaria;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.nativex.hint.TypeHint;

@TypeHint(types = PostgreSQL10Dialect.class, typeNames = "org.hibernate.dialect.PostgreSQL10Dialect")
@SpringBootApplication
public class GestorPeixariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestorPeixariaApplication.class, args);
    }

}
