package name.bouknecht.mywebapp.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class MyExceptionHandlerFactory extends ExceptionHandlerFactory {
    private final ExceptionHandlerFactory wrappedExceptionHandlerFactory;

    public MyExceptionHandlerFactory(ExceptionHandlerFactory wrappedExceptionHandlerFactory) {
        this.wrappedExceptionHandlerFactory = wrappedExceptionHandlerFactory;
    }

    @Override
    public ExceptionHandlerFactory getWrapped() {
        return wrappedExceptionHandlerFactory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new MyExceptionHandler(wrappedExceptionHandlerFactory.getExceptionHandler());
    }
}
