# Gestor de Peixaria
Repositório para a disciplina de Modelos, Métodos e Técnicas de Engenharia de Software 

Atividade prática de Visão e análise de projeto


[![Github Actions Status for AntonioSgarbi/gestor_peixaria](https://github.com/AntonioSgarbi/gestor_peixaria/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/AntonioSgarbi/gestor_peixaria/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_gestor_peixaria&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AntonioSgarbi_gestor_peixaria)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_gestor_peixaria&metric=coverage)](https://sonarcloud.io/component_measures?id=AntonioSgarbi_gestor_peixaria&metric=coverage)


## Utiliza 3 ambientes:
- dev - Desenvolvimento
- test - Homologação
- prod - Produção Pipeline
<hr>

- dev - Compilação
- test - Compilação, Testes, Análise Código, Cobertura Código
- prod - Empacotamento
<hr>

- Utiliza o Apache Maven para a automatização da construção.<br>
- A pasta test contêm os testes unitários do projeto utilizando JUnit 4.<br>
- A cobertura do código é realizada através do JaCoCo.<br>
