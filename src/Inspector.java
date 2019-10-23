import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	//set up how far to indent tabs
    	String tabs = "";
    	for(int i=0; i<depth; i++) {
    		tabs = tabs + "\t";
    	}
    	
    	//print class name
    	String className = c.getName();
    	System.out.println(tabs + "Class Name: " + className);
    	
    	
		//get each constructor and use it to get and print name, parameters, and modifier
		Constructor[] classConstructor = c.getConstructors();
		for(Constructor constructor : classConstructor) {
			if(Modifier.isPublic(constructor.getModifiers())) {
				constructor.setAccessible(true);
			}
			System.out.println(tabs + " Constructor Name: " + constructor.getName());
			Class[] parameters = constructor.getParameterTypes();
			for(Class parameter : parameters) {
				System.out.println(tabs + "  Parameter Type: " + parameter.getName());
			}
			System.out.println(tabs + "  Modifier: " + Modifier.toString(constructor.getModifiers()));
		}
		
    	
    	
    	
    	
    	
//    	if(recursive)
//    		inspectClass(c, obj, recursive, depth);
    }
    
    
    public static void main(String[] args){
    	Object apple = new Fruit("Apple", 18);
    	new Inspector().inspect(apple, true);
    	
    }
}