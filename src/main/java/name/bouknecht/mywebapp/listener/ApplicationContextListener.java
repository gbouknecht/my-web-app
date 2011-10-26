package name.bouknecht.mywebapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class ApplicationContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        SLF4JBridgeHandler.install();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
