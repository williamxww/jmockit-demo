package blog.common;

import mockit.Tested;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Tested(availableDuringSetup = true, fullyInitialized = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TestData {
}
