package org.ibase4j.core.support.dubbo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DubboService {

	Class<?> interfaceClass() default void.class;

	String interfaceName() default "";

	String version() default "";

	String group() default "";

	String path() default "";

	boolean export() default false;

	String token() default "";

	boolean deprecated() default false;

	boolean dynamic() default false;

	String accesslog() default "";

	int executes() default 0;

	boolean register() default false;

	int weight() default 0;

	String document() default "";

	int delay() default 0;

	String local() default "";

	String stub() default "";

	String cluster() default "";

	String proxy() default "";

	int connections() default 0;

	int callbacks() default 0;

	String onconnect() default "";

	String ondisconnect() default "";

	String owner() default "";

	String layer() default "";

	int retries() default 0;

	String loadbalance() default "";

	boolean async() default false;

	int actives() default 0;

	boolean sent() default false;

	String mock() default "";

	String validation() default "";

	int timeout() default 0;

	String cache() default "";

	String[] filter() default {};

	String[] listener() default {};

	String[] parameters() default {};

	String application() default "";

	String module() default "";

	String provider() default "";

	String[] protocol() default {};

	String monitor() default "";

	String[] registry() default {};
}
