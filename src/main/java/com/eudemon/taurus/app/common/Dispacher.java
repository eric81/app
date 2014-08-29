package com.eudemon.taurus.app.common;

public interface Dispacher {
	public void diag(Object content);
	public String getClassName();
	public String getMethodName();
	@SuppressWarnings({"rawtypes" })
	public Class getMethodParamCls();
}
