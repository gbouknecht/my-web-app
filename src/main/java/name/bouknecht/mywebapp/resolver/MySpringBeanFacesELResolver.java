package name.bouknecht.mywebapp.resolver;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

/**
 * A {@link SpringBeanFacesELResolver} that takes a {@link MessageSource} into
 * account.
 *
 * <p>According to the Spring Framework Reference (version 3.0.x), the
 * {@code <application>} element of the <code>faces-config.xml</code> may have
 * a {@code <message-bundle>} element. Unfortunately, this will not resolve EL
 * expressions like <code>#{messages.home}</code>. For that, this resolver was
 * created. The resolver can be configured in <code>faces-config.xml</code>:
 *
 * <pre>
 * {@code
 * <faces-config>
 *     <application>
 *         <el-resolver>name.bouknecht.mywebapp.resolver.MySpringBeanFacesELResolver</el-resolver>
 *     </application>
 * </faces-config>
 * }
 * </pre>
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
