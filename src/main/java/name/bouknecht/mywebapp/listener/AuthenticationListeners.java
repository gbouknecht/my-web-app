package name.bouknecht.mywebapp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Configuration
public class AuthenticationListeners {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationListeners.class);

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener() {
        return new ApplicationListener<AuthenticationSuccessEvent>() {
            public void onApplicationEvent(AuthenticationSuccessEvent event) {
                logger.info("Authentication success for \"{}\"", event.getAuthentication().getName());
            }
        };
    }

    @Bean
    public ApplicationListener<AbstractAuthenticationFailureEvent> authenticationFailureListener() {
        return new ApplicationListener<AbstractAuthenticationFailureEvent>() {
            public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
                logger.warn("Authentication failure for \"{}\"", event.getAuthentication().getName());
            }
        };
    }
}
