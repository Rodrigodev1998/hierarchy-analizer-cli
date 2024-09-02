package com.test.hierarchy_analyzer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhraseAnalyzerTest {

    @Test
    public void testAnalyzePhrase() {
        Map<String, Object> hierarchy = new HashMap<>();
        hierarchy.put("Animais", Map.of(
                "Mamíferos", Map.of(
                        "Carnívoros", Map.of(
                                "Felinos", List.of("Leões", "Tigres", "Jaguars", "Leopardos")
                        )
                ),
                "Herbívoros", Map.of(
                        "Equídeos", List.of("Cavalos", "Zebras", "Asnos"),
                        "Bovídeos", List.of("Bois", "Búfalos", "Antílopes", "Cabras"),
                        "Primatas", List.of("Gorilas", "Chimpanzés", "Orangotangos")
                ),
                "Aves", Map.of(
                        "Rapinas", List.of("Águias", "Falcões", "Corujas", "Milhafres"),
                        "Pássaros", List.of("Canários", "Papagaios", "Pardais", "Rouxinóis")
                )
        ));

        PhraseAnalyzer analyzer = new PhraseAnalyzer(hierarchy);

        Map<String, Integer> result = analyzer.analyzePhrase("eu vi tigres e papagaios", 3);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Felinos", 1);

        assertEquals(expected, result);
    }
}
