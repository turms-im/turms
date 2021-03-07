package im.turms.server.common.property.metadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field marked with @GlobalProperty is considered a global property.
 * Otherwise, it's a local property (turms doesn't support mixed properties currently).
 * <p>
 * Both local properties and global properties can be immutable or mutable.
 * 1. For immutable local/global properties, they can only be applied on startup;
 * 2. For mutable local properties, they can only be updated via their own admin APIs
 * (so turms-gateway don't support mutable local properties yet);
 * 3. For mutable global properties, they are always synced with the ones in the config server,
 * and the properties in the config server can be updated via their own or others' admin APIs
 *
 * @author James Chen
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalProperty {
}