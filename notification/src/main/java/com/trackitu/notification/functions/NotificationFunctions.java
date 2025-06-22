package com.trackitu.notification.functions;


import com.trackitu.notification.dto.AccountsNotificationDto;
import jakarta.mail.internet.MimeMessage;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class NotificationFunctions {

  private static final Logger logger = LoggerFactory.getLogger(NotificationFunctions.class);
  private final JavaMailSender javaMailSender;

  public NotificationFunctions(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Bean
  public Function<AccountsNotificationDto, AccountsNotificationDto> email() {
    return accDto -> {
      logger.info("Preparing to send email to: {}", accDto.email());
      try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(accDto.email());
        helper.setSubject("TrackItU - Detaliile contului");
        helper.setText(buildEmailContent(accDto), true);

        javaMailSender.send(message);
        logger.info("Email sent successfully to: {}", accDto.email());
      } catch (Exception exception) {
        logger.error("Failed to send email to: {}", accDto.email(), exception);
      }
      return accDto;
    };
  }

  private String buildEmailContent(AccountsNotificationDto accDto) {
    return """
            <html>
              <body>
                <h2>Bun venit, %s %s!</h2>
                <p>Ai fost inregistrat in aplicatia <strong>TrackItU</strong>.</p>
                <p>Acestea sunt datele pentru conectare:</p>
                <ul>
                  <li><strong>Username (email):</strong> %s</li>
                  <li><strong>Password:</strong> %s</li>
                </ul>
                <p>Vei schimba parola la prima conectare!</p>
                <br>
                <p>Multumim,<br/>Echipa TrackItU</p>
              </body>
            </html>
            """.formatted(accDto.firstName(), accDto.lastName(), accDto.email(), accDto.password());
  }
}
