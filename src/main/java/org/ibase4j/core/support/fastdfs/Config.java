package org.ibase4j.core.support.fastdfs;

import java.io.Serializable;

public interface Config extends Serializable {
	static final String FILE_DEFAULT_WIDTH = "120";
	static final String FILE_DEFAULT_HEIGHT = "120";
	static final String FILE_DEFAULT_AUTHOR = "iBase4J";

	static final String PROTOCOL = "http://";
	static final String SEPARATOR = "/";

	static final String TRACKER_NGNIX_PORT = "8080";
}
