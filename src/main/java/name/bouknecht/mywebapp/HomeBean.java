package name.bouknecht.mywebapp;

import name.bouknecht.mywebapp.annotation.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component @RequestScoped
public class HomeBean {
    private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);

    public HomeBean() {
        logger.info("Constructing " + this);
    }

    public String getMessage() {
        return String.format("Hello, from %s!", this);
    }
}
