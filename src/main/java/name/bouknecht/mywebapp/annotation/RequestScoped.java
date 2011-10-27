package name.bouknecht.mywebapp.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

@Documented
@Retention(RUNTIME)
@Scope
@org.springframework.context.annotation.Scope("request")
public @interface RequestScoped {
}
