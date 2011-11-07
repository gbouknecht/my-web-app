package name.bouknecht.mywebapp.resolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Locale;

import javax.el.ELContext;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;

public class MySpringBeanFacesELResolverTest {
    private MySpringBeanFacesELResolver elResolver;
    private ELContext                   elContext;
    private FacesContext                facesContext;
    private UIViewRoot                  uiViewRoot;
    private MessageSource               messages;

    @Before
    public void setUp() {
        elResolver   = spy(new MySpringBeanFacesELResolver());
        elContext    = mock(ELContext.class);
        facesContext = mock(FacesContext.class);
        uiViewRoot   = mock(UIViewRoot.class);
        messages     = mock(MessageSource.class);

        FacesContextTestUtils.setCurrentInstance(facesContext);
    }

    @Test
    public void shouldGetValueFromMessageSourceWhenBaseIsMessageSourceAndPropertyIsString() {
        given(facesContext.getViewRoot()).willReturn(uiViewRoot);
        given(uiViewRoot.getLocale()).willReturn(Locale.CHINA);
        given(messages.getMessage("key", null, "key", Locale.CHINA)).willReturn("value");
        Object value = elResolver.getValue(elContext, messages, "key");
        verify(elContext).setPropertyResolved(true);
        assertThat((String) value, is("value"));
    }

    @Test
    public void shouldGetValueFromSuperWhenBaseIsMessageSourceButPropertyIsNull() {
        verifyThatValueIsGotFromSuper(messages, null);
    }

    @Test
    public void shouldGetValueFromSuperWhenBaseIsMessageSourceButPropertyIsNotString() {
        verifyThatValueIsGotFromSuper(messages, new Object());
    }

    @Test
    public void shouldGetValueFromSuperWhenBaseIsNullAndPropertyIsMessageSource() {
        verifyThatValueIsGotFromSuper(null, messages);
    }

    @Test
    public void shouldGetValueFromSuperWhenBaseIsNotMessageSourceAndPropertyIsString() {
        verifyThatValueIsGotFromSuper(new Object(), "key");
    }

    private void verifyThatValueIsGotFromSuper(Object base, Object property) {
        doReturn("valueFromSuper").when(elResolver).getValueFromSuper(elContext, base, property);
        Object value = elResolver.getValue(elContext, base, property);
        verify(elContext, never()).setPropertyResolved(anyBoolean());
        verify(messages, never()).getMessage(anyString(), any(Object[].class), anyString(), any(Locale.class));
        assertThat((String) value, is("valueFromSuper"));
    }

    private static abstract class FacesContextTestUtils extends FacesContext {
        protected static void setCurrentInstance(FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }
}
