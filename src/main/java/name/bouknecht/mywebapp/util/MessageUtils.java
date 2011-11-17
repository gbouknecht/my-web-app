package name.bouknecht.mywebapp.util;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.jsf.FacesContextUtils;

public final class MessageUtils {
    private MessageUtils() {
        /* This class should not be instantiated. */
    }

    public static FacesMessage createFacesMessage(String messageId) {
        assert messageId != null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);
            if (applicationContext != null) {
                MessageSource messages = (MessageSource) applicationContext.getBean("messages");
                UIViewRoot viewRoot = facesContext.getViewRoot();
                Locale locale = (viewRoot != null) ? viewRoot.getLocale() : Locale.getDefault();
                return new FacesMessage(messages.getMessage(messageId, null, messageId, locale));
            }
        }
        return new FacesMessage(messageId);
    }
}
