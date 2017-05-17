package blog.common;

import mockit.Tested;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate a field in a test class which will hold a dependency
 * injected into the {@linkplain ObjectUnderTest object under test}.
 */
@Tested(fullyInitialized = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dependency {
}
