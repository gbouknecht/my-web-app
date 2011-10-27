package name.bouknecht.mywebapp;

import javax.inject.Named;

import name.bouknecht.mywebapp.annotation.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class HomeBean {
    private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);

    public HomeBean() {
        logger.info("Constructing " + this);
    }

    public String getMessage() {
        return String.format("Hello, from %s!", this);
    }
}
