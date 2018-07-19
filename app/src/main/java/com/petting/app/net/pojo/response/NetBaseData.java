package com.petting.app.net.pojo.response;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuboyang on 18/7/18.
 */

public class NetBaseData implements Serializable {
    @Override
    public String toString() {
        Map<String,String> map =new HashMap<>();
        List<Field> fieldList = new ArrayList<>() ;
        fieldList.addAll(Arrays.asList(getClass().getDeclaredFields()));
        Class tempClass = getClass().getSuperclass();
        if (tempClass != null){
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields())); //获取父类的字段
        }
        for(Field field : fieldList){
            try {
                if (field.getName().equals("$change")) continue;
                if (Modifier.isStatic(field.getModifiers())) continue;
                field.setAccessible(true);
                Object value = field.get(this);
                if(!(value instanceof String)){
                    value = String.valueOf(value);
                }
                map.put(field.getName(), (String) value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map.toString();
    }
}
