package sb.test.common;

import sb.test.common.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: LengShui on 2022-08-09 11:19
 **/
public class InvokeDemo {

	static Map<String, Method> methodMap = new HashMap<String, Method>();
	static String[] arr = new String[]{"STARTING", "RUNNING", "STOPPING"};

	static {
		Class<User> userClass = User.class;
		Method[] methods = userClass.getMethods();
		for (String s : arr) {
			for (Method method : methods) {
				if (method.getName().contains("set"+s)) {
					methodMap.put(s, method);
				}
			}
		}
	}


	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
		Map<String, Long> map = new HashMap<String, Long>();

		for (String s : arr) {
			map.put(s, 1L);
		}
		User user = new User();
		System.out.println(user.toString());

		for (String key : arr) {
			doInvoke(map.get(key), key, user);
		}
		System.out.println(user.toString());

	}

	private static void doInvoke(Long value, String s, User user) throws InvocationTargetException, IllegalAccessException, InstantiationException {
		Method method = methodMap.get(s);
		method.invoke(user, value);
	}
}
