/**
 * 
 */
package com.eudemon.taurus.app.common;

import java.lang.reflect.Method;

/**
 * @author xiaoyang.zhang
 * 
 */
public class Controller {
	/**
	 * invoke a method of class specified by dispacher
	 * @param dispacher : interface specify the class and method name
	 * @param content : method parameter
	 * @return
	 * @throws Exception
	 */
	public static Object action(Dispacher dispacher, Object content) throws Exception {
		if (null == dispacher) {
			return null;
		}
		
		dispacher.diag(content);
		String clsName = dispacher.getClassName();
		String methodName = dispacher.getMethodName();
		if (null == clsName || clsName.equals("")) {
			throw new Exception("clsName is not found");
		}
		if (null == methodName || methodName.equals("")) {
			throw new Exception("methodName is not found");
		}
		
		Object parser = Class.forName(clsName).newInstance();
		Method method = parser.getClass().getMethod(methodName,
				new Class[] { dispacher.getMethodParamCls() });
		return method.invoke(parser, content);
	}
	
	/**
	 * invoke a method of class specified by parameter
	 * @param clsName : class name
	 * @param methodName : method name
	 * @param content : method parameter
	 * @return
	 * @throws Exception
	 */
	public static Object action(String clsName, String methodName,
			Object content) throws Exception {
		if (null == clsName || clsName.equals("") || null == methodName || methodName.equals("")) {
			return null;
		}
		
		Object parser = Class.forName(clsName).newInstance();
		Method method = parser.getClass().getMethod(methodName,
				new Class[] { Object.class });
		return method.invoke(parser, content);
	}
}
