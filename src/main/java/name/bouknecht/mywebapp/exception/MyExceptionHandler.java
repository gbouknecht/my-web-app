package name.bouknecht.mywebapp.exception;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyExceptionHandler extends ExceptionHandlerWrapper {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    public static final String UNEXPECTED_ERROR_PAGE_URL = "/unexpected-error";

    private final ExceptionHandler wrappedExceptionHandler;

    public MyExceptionHandler(ExceptionHandler wrappedExceptionHandler) {
        this.wrappedExceptionHandler = wrappedExceptionHandler;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrappedExceptionHandler;
    }

    @Override
    public void handle() {
        if (thereAreUnhandledExceptions()) {
            handleUnhandledExceptionsAndRedirectToUnexpectedErrorPage();
        }
    }

    private boolean thereAreUnhandledExceptions() {
        return getUnhandledExceptionQueuedEvents().iterator().hasNext();
    }

    private void handleUnhandledExceptionsAndRedirectToUnexpectedErrorPage() {
        handleUnhandledExceptions();
        redirectToUnexpectedErrorPage();
    }

    private void handleUnhandledExceptions() {
        for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext(); ) {
            handleUnhandledException(iter.next().getContext().getException());
            iter.remove();
        }
    }

    /* package-private for unit test */
    void handleUnhandledException(Throwable t) {
        logger.error(t.getMessage(), t);
    }

    private void redirectToUnexpectedErrorPage() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String url = externalContext.getRequestContextPath() + UNEXPECTED_ERROR_PAGE_URL;
        try {
            externalContext.redirect(url);
        } catch (IOException e) {
            throw new RuntimeException("Could not redirect to " + url, e);
        }
    }
}
