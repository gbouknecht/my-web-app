package name.bouknecht.mywebapp.resolver;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

/*
 * TODO: Check if this class is needed.
 *
 * According to the Spring Framework Reference (version 3.0.x), the
 * faces-config.xml may have a <message-bundle> element:
 *
 * <faces-config>
 *     <application>
 *         <variable-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</variable-resolver>
 *         <message-bundle>messages</message-bundle>
 *     </application>
 * </faces-config
 */
public class MySpringBeanFacesELResolver extends SpringBeanFacesELResolver {

    @Override
    public Object getValue(ELContext elContext, Object base, Object property) throws ELException {
        if ((base instanceof MessageSource) && (property instanceof String)) {
            elContext.setPropertyResolved(true);
            return ((MessageSource) base).getMessage((String) property, null, (String) property, getLocale());
        }
        return getValueFromSuper(elContext, base, property);
    }

    /* package-private for unit test */
    Object getValueFromSuper(ELContext elContext, Object base, Object property) {
        return super.getValue(elContext, base, property);
    }

    private Locale getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
}
