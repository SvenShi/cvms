package com.sven.cvms.framework.web.converter;

import com.sven.cvms.common.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Objects;

/**
 * 字符串转枚举类
 *
 * @author isven
 * @date 2023/12/07
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {
    private final HashMap<String, T> enumMap = new HashMap<>();

    public StringToEnumConverter(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        for (T e : enumConstants) {
            enumMap.put(e.getCode(), e);
        }
    }


    @Override
    public T convert(String source) {
        T t = enumMap.get(source);
        if (Objects.isNull(t)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return t;
    }
}
