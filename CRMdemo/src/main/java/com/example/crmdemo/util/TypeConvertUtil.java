package com.example.crmdemo.util;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeConvertUtil {
    /**
     * List<Map<String, Object>> 到 List<T> 数据转换
     */
    public static <T> List<T> setList(List<Map<String, Object>> srcList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        srcList.forEach(x -> {
            try {
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {
                        //设置对象的访问权限，保证对private的属性的访问
                        field.setAccessible(true);
                        //读取配置转换字段名，并从map中取出数据
                        Object v = x.get(field.getName());
                        field.set(t, convert(v, field.getType()));
                    }
                }
                list.add(t);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        return list;
    }

    /**
     * Field类型转换
     */
    private static <T> T convert(Object obj, Class<T> type) {
        if (obj != null && StrUtil.isNotBlank(obj.toString())) {
            if (type.equals(String.class)) {
                return (T) obj.toString();
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            }
            //其他类型转换......
        }
        return null;
    }
}
