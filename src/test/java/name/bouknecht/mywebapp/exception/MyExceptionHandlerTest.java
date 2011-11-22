package name.bouknecht.mywebapp.exception;

import static name.bouknecht.mywebapp.exception.MyExceptionHandler.UNEXPECTED_ERROR_PAGE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import name.bouknecht.mywebapp.test.util.FacesContextTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MyExceptionHandlerTest {
    private static final String CONTEXT_PATH = "/my-test-context";

    private MyExceptionHandler myExceptionHandler;

    private @Mock FacesContext               facesContext;
    private @Mock ExternalContext            externalContext;
    private @Mock ExceptionHandler           wrappedExceptionHandler;
    private       List<ExceptionQueuedEvent> unhandledExceptionQueuedEvents;
    private       Exception                  exception1;
    private       Exception                  exception2;

    @Before
    public void setUp() {
        initMocks(this);

        unhandledExceptionQueuedEvents = new ArrayList<ExceptionQueuedEvent>();
        exception1                     = new Exception();
        exception2                     = new Exception();
        myExceptionHandler             = spy(new MyExceptionHandler(wrappedExceptionHandler));

        FacesContextTestUtils.setCurrentInstance(facesContext);
        given(facesContext.getExternalContext()).willReturn(externalContext);
        given(externalContext.getRequestContextPath()).willReturn(CONTEXT_PATH);
    }

    @Test
    public void shouldWrapGivenExceptionHandler() {
        assertThat(myExceptionHandler.getWrapped(), is(sameInstance(wrappedExceptionHandler)));
    }

    @Test
    public void shouldHandleAllUnhandledExceptions() {
        given(wrappedExceptionHandler.getUnhandledExceptionQueuedEvents()).willReturn(unhandledExceptionQueuedEvents);
        unhandledExceptionQueuedEvents.add(createExceptionQueuedEvent(exception1));
        unhandledExceptionQueuedEvents.add(createExceptionQueuedEvent(exception2));

        myExceptionHandler.handle();

        verify(myExceptionHandler).handleUnhandledException(exception1);
        verify(myExceptionHandler).handleUnhandledException(exception2);
        assertTrue(unhandledExceptionQueuedEvents.isEmpty());
    }

    @Test
    public void shouldRedirectToUnexpectedErrorPageWhenThereWereUnhandledExceptions() throws IOException {
        given(wrappedExceptionHandler.getUnhandledExceptionQueuedEvents()).willReturn(unhandledExceptionQueuedEvents);
        unhandledExceptionQueuedEvents.add(createExceptionQueuedEvent(exception1));

        myExceptionHandler.handle();

        verify(externalContext).redirect(CONTEXT_PATH + UNEXPECTED_ERROR_PAGE_URL);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenRedirectToUnexpectedErrorPageThrowsIOException() throws IOException {
        given(wrappedExceptionHandler.getUnhandledExceptionQueuedEvents()).willReturn(unhandledExceptionQueuedEvents);
        unhandledExceptionQueuedEvents.add(createExceptionQueuedEvent(exception1));
        doThrow(new IOException()).when(externalContext).redirect(anyString());

        myExceptionHandler.handle();
    }

    @Test
    public void shouldNotRedirectWhenThereWereNoUnhandledExceptions() throws IOException {
        given(wrappedExceptionHandler.getUnhandledExceptionQueuedEvents()).willReturn(unhandledExceptionQueuedEvents);

        myExceptionHandler.handle();

        verify(externalContext, never()).redirect(anyString());
    }

    private ExceptionQueuedEvent createExceptionQueuedEvent(Exception exception) {
        return new ExceptionQueuedEvent(new ExceptionQueuedEventContext(facesContext, exception));
    }
}
