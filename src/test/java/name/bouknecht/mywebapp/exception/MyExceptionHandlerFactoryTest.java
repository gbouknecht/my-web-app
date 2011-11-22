package name.bouknecht.mywebapp.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.junit.Before;
import org.junit.Test;

public class MyExceptionHandlerFactoryTest {
    private ExceptionHandlerFactory myExceptionHandlerFactory;
    private ExceptionHandlerFactory wrappedExceptionHandlerFactory;
    private ExceptionHandler        wrappedExceptionHandler;

    @Before
    public void setUp() {
        wrappedExceptionHandlerFactory = mock(ExceptionHandlerFactory.class);
        wrappedExceptionHandler        = mock(ExceptionHandler.class);
        myExceptionHandlerFactory      = new MyExceptionHandlerFactory(wrappedExceptionHandlerFactory);
    }

    @Test
    public void shouldWrapGivenExceptionHandlerFactory() {
        assertThat(myExceptionHandlerFactory.getWrapped(), is(sameInstance(wrappedExceptionHandlerFactory)));
    }

    @Test
    public void shouldCreateMyExceptionHandlerWrappingExceptionHandlerOfWrappedExceptionHandlerFactory() {
        given(wrappedExceptionHandlerFactory.getExceptionHandler()).willReturn(wrappedExceptionHandler);

        MyExceptionHandler actualExceptionHandler = (MyExceptionHandler) myExceptionHandlerFactory.getExceptionHandler();

        assertThat(actualExceptionHandler.getWrapped(), is(sameInstance(wrappedExceptionHandler)));
    }
}
