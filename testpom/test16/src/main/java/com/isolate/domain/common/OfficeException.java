package com.isolate.domain.common;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/10/18 14:38
 */
public class OfficeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OfficeException(String message) {
        super(message);
    }

    public OfficeException(String message, Throwable cause) {
        super(message, cause);
    }
}
