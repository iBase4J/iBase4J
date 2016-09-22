package org.ibase4j.core.support.fastdfs;

import java.io.Serializable;

public interface Config extends Serializable {
    public static final String FILE_DEFAULT_WIDTH = "120";
    public static final String FILE_DEFAULT_HEIGHT = "120";
    public static final String FILE_DEFAULT_AUTHOR = "iBase4J";

    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_PORT = "8080";
}
