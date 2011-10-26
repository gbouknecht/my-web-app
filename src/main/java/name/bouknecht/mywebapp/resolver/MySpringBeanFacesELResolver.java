package name.bouknecht.mywebapp.resolver;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class MySpringBeanFacesELResolver extends SpringBeanFacesELResolver {

    @Override
    public Object getValue(ELContext elContext, Object base, Object property) throws ELException {
        if ((base instanceof MessageSource) && (property instanceof String)) {
            elContext.setPropertyResolved(true);
            return ((MessageSource) base).getMessage((String) property, null, (String) property, getLocale());
        }
        return super.getValue(elContext, base, property);
    }

    private Locale getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
}
