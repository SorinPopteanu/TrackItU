package com.trackitu.assets;

import com.trackitu.assets.dto.AssetsContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = AssetsContactInfoDto.class)
@OpenAPIDefinition(info = @Info(title = "Assets microservice REST API Documentation", description = "TrackItU Assets microservice REST API Documentation", version = "v1", contact = @Contact(name = "Sorin Popteanu", email = "sorinpopteanu72@gmail.com", url = "https://github.com/SorinPopteanu")))
public class AssetsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetsApplication.class, args);
  }

}
