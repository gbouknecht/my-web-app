package name.bouknecht.mywebapp.util;

import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.JAPAN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.web.context.WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import name.bouknecht.mywebapp.test.util.FacesContextTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;

public class MessageUtilsTest {
    private FacesContext          facesContext;
    private ExternalContext       externalContext;
    private Map<String, Object>   applicationMap;
    private WebApplicationContext webApplicationContext;
    private MessageSource         messages;
    private UIViewRoot            viewRoot;

    @Before
    public void setUp() {
        facesContext          = mock(FacesContext.class);
        externalContext       = mock(ExternalContext.class);
        webApplicationContext = mock(WebApplicationContext.class);
        applicationMap        = new HashMap<String, Object>();
        messages              = mock(MessageSource.class);
        viewRoot              = mock(UIViewRoot.class);

        FacesContextTestUtils.setCurrentInstance(facesContext);
        given(facesContext.getExternalContext()).willReturn(externalContext);
        given(externalContext.getApplicationMap()).willReturn(applicationMap);
        applicationMap.put(ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
        Locale.setDefault(ENGLISH);
    }

    @Test
    public void getFacesMessageShouldReturnWithMessageIdAsSummaryWhenThereIsNoFacesContext() {
        FacesContextTestUtils.setCurrentInstance(null);

        FacesMessage facesMessage = MessageUtils.createFacesMessage("key");

        assertThat(facesMessage.getSummary(), is("key"));
    }

    @Test
    public void getFacesMessageShouldReturnWithMessageIdAsSummaryWhenThereIsNoWebApplicationContext() {
        applicationMap.remove(ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        FacesMessage facesMessage = MessageUtils.createFacesMessage("key");

        assertThat(facesMessage.getSummary(), is("key"));
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void getFacesMessageShouldThrowNoSuchBeanDefinitionExceptionWhenThereIsNoMessagesBean() {
        given(webApplicationContext.getBean("messages")).willThrow(new NoSuchBeanDefinitionException("messages"));

        MessageUtils.createFacesMessage("key");
    }

    @Test
    public void getFacesMessageShouldReturnWithSummaryFromMessagesWhenAllContextsAreThereAndMessagesBeanExists() {
        verifyGetFacesMessageReturnSummaryFromMessagesUsingGivenLocale(ENGLISH);
    }

    @Test
    public void getFacesMessageShouldUseDefaultLocaleWhenThereIsNoViewRoot() {
        Locale.setDefault(JAPAN);
        verifyGetFacesMessageReturnSummaryFromMessagesUsingGivenLocale(JAPAN);
    }

    @Test
    public void getFacesMessageShouldUseLocaleFromViewRootWhenThereIsAViewRoot() {
        given(facesContext.getViewRoot()).willReturn(viewRoot);
        given(viewRoot.getLocale()).willReturn(CHINA);
        verifyGetFacesMessageReturnSummaryFromMessagesUsingGivenLocale(CHINA);
    }

    private void verifyGetFacesMessageReturnSummaryFromMessagesUsingGivenLocale(Locale locale) {
        given(webApplicationContext.getBean("messages")).willReturn(messages);
        given(messages.getMessage("key", null, "key", locale)).willReturn("value");

        FacesMessage facesMessage = MessageUtils.createFacesMessage("key");

        assertThat(facesMessage.getSummary(), is("value"));
    }
}
