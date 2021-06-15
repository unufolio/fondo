package com.unufolio.fondo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/31
 */
public class Response {

    private final static ObjectMapper OBJECT_MAPPER;

    private final static String APPLICATION_JSON_VALUE = "application/json";

    static {
        OBJECT_MAPPER = new Jackson2ObjectMapperBuilder()
                .build();
    }

    public static void json(HttpServletResponse response,
                            int status) {
        response.setStatus(status);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

    public static void json(HttpServletResponse response,
                            int status,
                            Object content) throws IOException {
        response.setStatus(status);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = response.getWriter()) {
            writer.write(OBJECT_MAPPER.writeValueAsString(content));
        }
    }

    public static void ok(HttpServletResponse response,
                          Object content) throws IOException {
        json(response, HttpServletResponse.SC_OK, content);
    }
}
