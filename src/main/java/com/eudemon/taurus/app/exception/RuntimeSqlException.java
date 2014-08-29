package com.eudemon.taurus.app.exception;

public class RuntimeSqlException extends RuntimeException {
	private static final long serialVersionUID = 1968675076178341580L;

	public RuntimeSqlException() {
		super();
	}

	public RuntimeSqlException(String message) {
		super(message);
	}

	public RuntimeSqlException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeSqlException(Throwable cause) {
		super(cause);
	}
}