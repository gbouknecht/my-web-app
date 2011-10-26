package name.bouknecht.mywebapp;

import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
public class HomeBean {
    private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);

    public HomeBean() {
        logger.info("Constructing " + HomeBean.class);
    }

    public String getMessage() {
        return String.format("Hello, from %s!", HomeBean.class);
    }
}
