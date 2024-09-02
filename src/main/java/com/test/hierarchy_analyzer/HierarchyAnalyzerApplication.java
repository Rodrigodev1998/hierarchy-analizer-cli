package com.test.hierarchy_analyzer;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.Map;

@SpringBootApplication
public class HierarchyAnalyzerApplication {

	public static void main(String[] args) {
		if (args.length < 4 || !args[0].equals("analyze") || !args[1].equals("--depth")) {
			System.out.println("Usage: java -jar cli.jar analyze --depth <n> \"{phrase}\" [--verbose]");
			return;
		}

		int depth = Integer.parseInt(args[2]);
		String phrase = args[3];
		boolean verbose = args.length > 4 && args[4].equals("--verbose");

		Timer timer = new Timer();

		timer.start();
		Map<String, Object> hierarchy = HierarchyLoader.loadHierarchy(Path.of("src/main/resources/dicts/hierarchy.json"));
		long loadTime = timer.stop();


		PhraseAnalyzer analyzer = new PhraseAnalyzer(hierarchy);
		timer.start();
		Map<String, Integer> result = analyzer.analyzePhrase(phrase, depth);
		long analyzeTime = timer.stop();


		if (result.isEmpty()) {
			System.out.println("Nenhum termo correspondente encontrado.");
		} else {
			result.forEach((category, count) -> System.out.println(category + " = " + count));
		}

		if (verbose) {
			System.out.println("Tempo de carregamento dos parâmetros: " + loadTime + "ms");
			System.out.println("Tempo de verificação da frase: " + analyzeTime + "ms");
		}
	}

}
