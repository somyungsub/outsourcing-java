package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ObjectUtils {

    public static <T> T convertToObject(Map<String, Object> map, Class<T> classType) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        TestClass test = new TestClass();

        Class clazz = classType;
        Object obj = (T) clazz.newInstance();
        clazz.hashCode();

        Class clazz2 = Class.forName("TestClass");
        Object obj2 = clazz2.newInstance();
        for (Field field : clazz.getDeclaredFields()) {

            // 접근제한자 판단
            if (map.containsKey(field.getName())) {
                map.get(field.getName());
            }
        }

        System.out.println("\n====Constructor List====");

        for(Constructor constructor : clazz.getConstructors()){

            System.out.print("개수  " + constructor.getParameterCount() + " = ");

            for(Class parameter : constructor.getParameterTypes()){
                System.out.print(parameter.getName() + " / ");
            }

            System.out.println();
        }

        System.out.println("\n====Method List====");

        for(Method method : classType.getMethods()){
            System.out.println(method.getName());
        }


        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}

