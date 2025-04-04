package com.trackitu.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts microservice REST API Documentation",
        description = "TrackItU Accounts microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Sorin Popteanu",
            email = "sorinpopteanu72@gmail.com",
            url = "https://github.com/SorinPopteanu"
        )
    )
)
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }

}
