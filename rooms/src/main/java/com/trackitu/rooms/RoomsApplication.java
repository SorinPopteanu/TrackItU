package com.trackitu.rooms;

import com.trackitu.rooms.dto.RoomsContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = RoomsContactInfoDto.class)
public class RoomsApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoomsApplication.class, args);
  }

}
