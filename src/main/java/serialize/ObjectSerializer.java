package serialize;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectSerializer {
    private static String serialize(Object o){
        return objectSerializer(o,1);
    }

    public static String objectSerializer(Object o,int indentionLevel) {
        StringBuilder json = new StringBuilder();
        Class<?> oClazz = o.getClass();
        Field[] fields = oClazz.getDeclaredFields();
        json.append("{");
        boolean isFirstField = true;
        for(Field field : fields){
            if(isFirstField){
                isFirstField = false;
            }else{
                json.append(",");
            }

            json.append("\n");
            json.append("\t".repeat(indentionLevel));
            field.setAccessible(true);
            final Class<?> fieldType = field.getType();
            final Object fieldValue = ReflectionUtil.runFieldGetter(field,o);
                json.append(String.format("\"%s\" : ",field.getName()));
                final String serializedObject = serializeComponent(fieldType,fieldValue,indentionLevel + 1);
                json.append(serializedObject);
        }

        json.append("\n");
        json.append("\t".repeat(indentionLevel - 1));
        json.append("}");

        return json.toString();
    }


    public static String serializeComponent(Class<?> fieldType,Object fieldValue,int indentionLevel) {
        StringBuilder json = new StringBuilder();
        if(Character.class.equals(fieldType) || String.class.equals(fieldType)){
            json.append(String.format("\"%s\"",fieldValue));
        }else if(ReflectionUtil.isPrimitiveOrWrapper(fieldType)){
            json.append(String.format("%s",fieldValue));
        }else if(fieldType.isArray() || fieldType.isAssignableFrom(List.class)){
            json.append("[");
            boolean isFirstElement = true;
            for(Object element: (Iterable)fieldValue){
                if(isFirstElement){isFirstElement = false;}else{json.append(",");}
                json.append("\n");
                json.append("\t".repeat(indentionLevel));
                final String serializedElement = serializeComponent(element.getClass(),element,indentionLevel + 1);
                json.append(serializedElement);

            }
            json.append("\n");
            json.append("\t".repeat(indentionLevel - 1));
            json.append("]");
        }else{
            final String serializedObject = objectSerializer(fieldValue, indentionLevel + 1);
            json.append(serializedObject);
        }
        return json.toString();
    }
}
