package ru.job4j.generator;

import java.util.Map;

public class MyGenerator implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) {
        for (String temp : args.keySet()) {
            if (template.contains(temp)) {
                template = template.replace("${" + temp + "}", args.get(temp));
            } else {
                throw new IllegalArgumentException("string doesn't this key word");
            }
        }
        if (template.contains("$")) {
            throw new IllegalArgumentException("Key is absent");
        }
        return template;
    }
}
