package org.ibase4j.core.support.dubbo.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DubboReference {
	Class<?> interfaceClass() default void.class;

	String interfaceName() default "";

	String version() default "";

	String group() default "";

	String url() default "";

	String client() default "";

	boolean generic() default false;

	boolean injvm() default false;

	boolean check() default false;

	boolean init() default false;

	boolean lazy() default false;

	boolean stubevent() default false;

	String reconnect() default "";

	boolean sticky() default false;

	String proxy() default "";

	String stub() default "";

	String cluster() default "";

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

	String consumer() default "";

	String monitor() default "";

	String[] registry() default {};
}