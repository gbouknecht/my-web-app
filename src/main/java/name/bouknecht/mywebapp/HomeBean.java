package name.bouknecht.mywebapp;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class HomeBean {
    private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);

    public HomeBean() {
        logger.info("Constructing " + this);
    }

    public String getMessage() {
        return String.format("Hello, from %s!", this);
    }
}
