package com.test.hierarchy_analyzer;

import java.util.*;
import java.util.stream.Collectors;

public class PhraseAnalyzer {

    private final Map<String, Object> hierarchy;

    public PhraseAnalyzer(Map<String, Object> hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Map<String, Integer> analyzePhrase(String phrase, int depth) {
        Map<String, Integer> result = new HashMap<>();
        String[] words = phrase.split("\\s+");

        searchHierarchy(hierarchy, words, 0, depth, result);

        return result;
    }

    private void searchHierarchy(Map<String, Object> hierarchy, String[] words, int currentDepth, int targetDepth, Map<String, Integer> result) {
        if (currentDepth == targetDepth) {
            countWordsAtDepth(hierarchy, words, result);
            return;
        }

        for (Map.Entry<String, Object> entry : hierarchy.entrySet()) {
            if (entry.getValue() instanceof Map) {
                searchHierarchy((Map<String, Object>) entry.getValue(), words, currentDepth + 1, targetDepth, result);
            }
        }
    }

    private void countWordsAtDepth(Map<String, Object> hierarchy, String[] words, Map<String, Integer> result) {
        for (Map.Entry<String, Object> entry : hierarchy.entrySet()) {
            if (entry.getValue() instanceof List) {
                List<String> items = (List<String>) entry.getValue();
                Set<String> wordsSet = Arrays.stream(words)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());
                long count = items.stream()
                        .map(String::toLowerCase)
                        .filter(itemsWord -> wordsSet.contains(itemsWord))
                        .count();
                if (count > 0) {
                    result.put(entry.getKey(), (int) count);
                }
            }
        }
    }
}
