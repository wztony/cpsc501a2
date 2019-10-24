import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Inspector {
	
    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		//set up how far to indent tabs
		String tabs = setupTabs(depth);
		
		if(c.isArray()) {
			
		}
		Class superClass = c.getSuperclass();
		if(c != Object.class) {
			inspectClass(superClass, obj, recursive, depth + 1);
		}
		
		recInterface(c, obj, depth + 1);
		
		//print class name
		String className = c.getName();
		System.out.println(tabs + "Class Name: " + className);
		
		printConstructors(c, tabs);
		printMethods(c, tabs);		
		printFields(c, obj, tabs);
//		if(recursive)
//			inspectClass(c, obj, recursive, depth);
	}
    
    
    public void printConstructors(Class c, String tabs) {
    	//get each constructor and use it to get and print name, parameters, and modifier
		Constructor[] classConstructor = c.getDeclaredConstructors();
		for(Constructor constructor : classConstructor) {
			if(!Modifier.isPublic(constructor.getModifiers())) {
				constructor.setAccessible(true);
			}
			System.out.println(tabs + " Constructor Name: " + constructor.getName());
			Class[] constructorParameters = constructor.getParameterTypes();
			for(Class parameter : constructorParameters) {
				System.out.println(tabs + "  Parameter Type: " + parameter.getName());
			}
			System.out.println(tabs + "  Modifier: " + Modifier.toString(constructor.getModifiers()));
		}
    }
    
    
    public void printMethods(Class c, String tabs) {
    	Method[] classMethod = c.getDeclaredMethods();
		for(Method method : classMethod) {
			if(!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			System.out.println(tabs + " Method Name: " + method.getName());
			
			Class[] exceptionParameters = method.getExceptionTypes();
			for(Class parameter : exceptionParameters) {
				System.out.println(tabs + "  Exception Type: " + parameter.getName());
			}
			
			System.out.println(tabs + "  Return Type: " + method.getReturnType());
			
			Class[] methodParameters = method.getParameterTypes();
			for(Class parameter : methodParameters) {
				System.out.println(tabs + "  Parameter Type: " + parameter.getName());
			}
			System.out.println(tabs + "  Modifier: " + Modifier.toString(method.getModifiers()));
		}
    }
    
    
    public void printFields(Class c, Object obj, String tabs) {
    	Field[] classField = c.getDeclaredFields();
		for(Field field : classField) {
			if(!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
			}
			System.out.println(tabs + " Field Name: " + field.getName());
			
			System.out.println(tabs + "  Type: " + field.getType().getName());
			
			System.out.println(tabs + "  Modifier: " + Modifier.toString(field.getModifiers()));
			

			try {
				Object ob = field.get(obj);
				if(ob.getClass().isPrimitive() ||
						ob.getClass() == Integer.class ||
						ob.getClass() == Double.class ||
						ob.getClass() == Float.class ||
						ob.getClass() == Short.class ||
						ob.getClass() == Long.class ||
						ob.getClass() == Character.class ||
						ob.getClass() == Byte.class ||
						ob.getClass() == Boolean.class) {
					System.out.println(tabs + "  Value: " + ob);
				}
				else {
					System.out.println(tabs + "  Value: " + ob.getClass().getName() + "@" + ob.hashCode());
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    
    public void recInterface(Class c, Object obj, int depth) {
    	String tabs = setupTabs(depth);
    	Class[] interfaces = c.getInterfaces();
    	for(Class intf : interfaces) {
			Class[] superInterfaces = intf.getInterfaces();
			for(Class sintf : superInterfaces) {
				if(sintf.isInterface()) {
					recInterface(intf, obj, depth + 1);
				}
			}
			System.out.println(tabs + "Interface Name: " + intf.getName());
			printMethods(intf, tabs);
			printFields(intf, obj, tabs);
		}
    }
    
    
    public String setupTabs(int depth) {
    	String tabs = "";
    	for(int i=0; i<depth; i++) {
			tabs = tabs + "-------|";
		}
    	return tabs;
    }
    
    
    public static void main(String[] args){
    	Object apple = new MiniFruit("Apple", 18);
    	new Inspector().inspect(apple, true);
    	
    }
}
