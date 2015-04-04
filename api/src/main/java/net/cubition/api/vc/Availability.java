package net.cubition.api.vc;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.CLASS)
public @interface Availability {
	Versions client() default Versions.CLIENT_NEVER;

	Versions server() default Versions.SERVER_NEVER;

	Versions server_deprecated() default Versions.SERVER_NEVER;

	Versions client_deprecated() default Versions.CLIENT_NEVER;
}
