package com.example.example130921.dao.repository;

import com.example.example130921.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractRepository {

    @Autowired
    @Qualifier("dataSource")
    protected DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected List<String> listAttributeName(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
    }

    protected String getSimpleNameTable(Class<?> clazz) {
        return StringUtils.camelCaseToSnakeCase(clazz.getSimpleName()).toLowerCase();
    }

    protected String attributeNamesForSelect(Class<?> clazz) {
        List<String> listAttribute = listAttributeName(clazz);
        if (listAttribute == null || listAttribute.isEmpty()) {
            return "";
        }
        String attributeNames = listAttribute.stream().map(StringUtils::camelCaseToSnakeCase)
                .collect(Collectors.joining(","));
        return attributeNames;
    }



}
