package com.test.hierarchy_analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class HierarchyLoader {
    public static Map<String, Object> loadHierarchy(Path path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String content = Files.readString(path);
            return mapper.readValue(content, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar a hierarquia: " + e.getMessage(), e);
        }
    }
}
