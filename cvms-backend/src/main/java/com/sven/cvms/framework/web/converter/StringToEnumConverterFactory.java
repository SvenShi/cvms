package com.sven.cvms.framework.web.converter;

import com.sven.cvms.common.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

public class StringToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class<? extends BaseEnum>, Converter<String, ? extends BaseEnum>> CONVERTER_MAP = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, ? extends BaseEnum> converter = CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return (Converter<String, T>) converter;
    }
}
