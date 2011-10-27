package name.bouknecht.mywebapp.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import org.springframework.context.annotation.Scope;

@Documented
@Retention(RUNTIME)
@Scope("request")
public @interface RequestScoped {
}
