package org.apache.http;

import org.apache.http.util.ExceptionUtils;

@Deprecated
public class HttpException extends Exception {
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message);
        ExceptionUtils.initCause(this, cause);
    }
}
