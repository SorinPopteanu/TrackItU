package com.trackitu.assets.audit;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attrs == null) {
      return Optional.empty();
    }
    HttpServletRequest request = attrs.getRequest();
    String username = request.getHeader("X-User-Name");
    return Optional.ofNullable(username);
  }
}
