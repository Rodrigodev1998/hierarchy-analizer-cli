# HierarchyAnalyzer

O `HierarchyAnalyzer` é uma aplicação Java que analisa frases e retorna contagens de termos encontrados em uma estrutura hierárquica de categorias. O projeto permite analisar a profundidade de termos na hierarquia fornecida e exibir resultados detalhados com base na análise de frases.

## Descrição

A aplicação carrega uma estrutura hierárquica de categorias a partir de um arquivo JSON e analisa frases fornecidas pelo usuário para identificar e contar as ocorrências de termos relevantes na hierarquia. Os resultados são exibidos com base na profundidade especificada na hierarquia.

## Estrutura do Projeto

1. **`HierarchyAnalyzerApplication`**: Classe principal que gerencia a execução da aplicação e processa os argumentos de linha de comando.
2. **`PhraseAnalyzer`**: Classe responsável pela análise das frases com base na hierarquia carregada.
3. **`HierarchyLoader`**: Classe utilitária para carregar a hierarquia a partir de um arquivo JSON.

## Requisitos

- Java 17 ou superior

## Estrutura do JSON

O arquivo JSON deve seguir a estrutura hierárquica abaixo:

```json
{
  "Animais": {
    "Mamíferos": {
      "Carnívoros": {
        "Felinos": ["Leões", "Tigres", "Jaguars", "Leopardos"]
      },
      "Herbívoros": {
        "Equídeos": ["Cavalos", "Zebras", "Asnos"],
        "Bovídeos": ["Bois", "Búfalos", "Antílopes", "Cabras"],
        "Primatas": ["Gorilas", "Chimpanzés", "Orangotangos"]
      }
    },
    "Aves": {
      "Rapinas": ["Águias", "Falcões", "Corujas", "Milhafres"],
      "Pássaros": ["Canários", "Papagaios", "Pardais", "Rouxinóis"]
    }
  }
}
```

## Execução da Aplicação

Para executar a aplicação, utilize o comando:

```sh
mvn clean package
```

```sh
java -jar target/hierarchy-analyzer-0.0.1-SNAPSHOT.jar analyze --depth <n> "<phrase>" [--verbose]
```
## Exemplos de retornos

### Profundidade 2 (Possui uma correspondência)

```sh
java -jar target/hierarchy-analyzer-0.0.1-SNAPSHOT.jar analyze --depth 2 "Eu amo papagaios" --verbose
```
```sh
Pássaros = 1
Tempo de carregamento dos parâmetros: 50ms
Tempo de verificação da frase: 10ms
```

### Profundidade 3 (Possui mais de uma correspondência)

```sh
java -jar target/hierarchy-analyzer-0.0.1-SNAPSHOT.jar analyze --depth 2 "Eu vi tigre e gorilas" --verbose
```
```sh
Felinos = 1
Primatas = 1
Tempo de carregamento dos parâmetros: 50ms
Tempo de verificação da frase: 10ms
```

### Não possui correspondência

```sh
java -jar target/hierarchy-analyzer-0.0.1-SNAPSHOT.jar analyze --depth 4 "Eu vi varios primatas" --verbose
```
```sh
Nenhum termo correspondente encontrado.
Tempo de carregamento dos parâmetros: 50ms
Tempo de verificação da frase: 10ms
```
