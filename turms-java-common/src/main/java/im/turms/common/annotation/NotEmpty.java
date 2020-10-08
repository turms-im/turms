package im.turms.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Should only be used in turms-client-java.
 * Don't use jakarta.validation-api because it uses
 * ElementType.TYPE_USE that was added in API level 26 -->
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(SOURCE)
public @interface NotEmpty {
}