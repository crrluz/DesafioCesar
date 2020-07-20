# Plano de testes

 1. [Introdução](#1-introdu%c3%a7%c3%a3o)
 2. [Escopo](#2-escopo)
    1. [Funcionalidades a serem testadas](#21-funcionalidades-a-serem-testadas)
 3. [Estratégia de Teste](#3-estrat%c3%a9gia-de-teste)
    1. [Tipos de teste](#31-tipos-de-teste)
    2. [Ferramentas sugeridas](#32-ferramentas-sugeridas)
 4. [Cenários previstos](#4-cen%c3%a1rios-previstos)
 5. [Erros encontrados](#5-erros-encontrados)
 6. [Melhoria surgerida](#6-melhoria-surgerida)
 7. [Resultado](#7-resultado)

## 1. Introdução

Este documento tem por missão descrever o plano para o teste da plataforma **Banco de Séries** que possui os seguintes objetivos:

- Traçar o escopo de testes;
- Definir a estratégia de testes utilizada no projeto;
- Delinear cenários para algumas funcionalidades bases, que deverão ser melhor trabalhados e desenvolvidos no decorrer do projeto;
- Alguns erros encontrados durante a bateria de testes;
- Melhorias surgeridas a plataforma;
- Resultado da bateria de teste executada e parecer sobre a aplicação.

## 2. Escopo

Este plano de teste é relacionado apenas a plataforma WEB, sendo assim excluida a analise da plataforma mobile presente tanto no google play quanto na app store. Além disso, os testes que serão executados durante a bateria de testes para a analise serão apenas exploratórios. Serão excluidos testes mais triviais como Login e Cadastro, focando assim apenas nas funcionalidades principais da plataforma

#### 2.1 Funcionalidades a serem testadas

- Busca por series
- Ranking de episódios
- Informações das séries
- Dashboard Home
- Grade de séries

## 3. Estratégia de Teste

Neste tópico abordaremos como a plataforma será testada, descrevendo os tipos de teste que serão empregados bem como sugestão de ferramentas a serem utilizadas.

#### 3.1 Tipos de teste

 - Testes funcionais
 - Testes de serviço
 - Testes unitários
  
  Os testes de carga, segurança, desempenho e regressão visual não serão cobertos por este plano de testes, dado os requisitos iniciais, Posteriormente este plano pode ser atualizado com determinados tipos de teste conforme requisitos necessários.

#### 3.2 Ferramentas sugeridas

Para os testes serão surgeridas as seguintes ferramentas para automação:

- Testes funcionais: Selenium com java, utilizando cucumber para a descrição dos cenários e casos, somando a library opensource Serenity para melhor organização de design da automação.
- Testes de serviço: RestAssured com Serenity, para melhor report e organização de design da automação.
- Testes unitários: PhpUnit, por já possuir funcionalidades para verificação de cobertura de código.

## 4. Cenários previstos

Este tópico identifica os itens que foram identificados como alvos do teste. Detalhes sobre cada teste serão determinados posteriormente e atualizados conforme mais casos de teste forem identificados e as automações desenvolvidas.

Os cenários serão descritos conforme funcionalidade.

 - Busca por series
   - Busca por nome existente na base
   - Busca por nome inexistente na base
   - Busca por numeros
   - Busca por nome acima de 256 carácteres
   - Busca utilizando carácteres especiais
 - Ranking de episódios
   - Verificar a integridade das maiores notas dos ultimos 15 dias (o que é demonstrado na página principal e na página do episódio)
   - Verificar a integridade da média de notas no ranking (o que é demonstrado na página principal e na página do episódio)
   - Verificar alterações no Top pessoal das notas dadas pelo usuário logado
   - Verificar integridade entre ano e todos os anos
   - Verificar integridade entre top 10 e top 100
 - Informações da Série
   - Marcar série como vista, verificar alteração no menu principal
   - Resetar série e marcar apenas um episódio como visto, verificar alteração no menu principal
   - Alterar Sinopse com carácteres especiais
   - Clicar em Editar Sinopse e Salvar sem alterar
   - Tentar alterar Sinopse com mais de 1024 cracteres
   - Alterar Sinopse apenas com carácteres invisíveis 
 - Grade de séries
   - Adicionar uma série a grade
   - Remover da grade
   - Remover da grade e adicionar novamente
 - Dashboard Home
   - Verificar últimas atualizações
   - Verificar rigor das notas
   - Marcar episódio como visto e Verificar Episódios vistos
   - Desmarcar episódio como visto e Verificar Episódios vistos
   - Marcar um episódio como visto porém não dar nota e verificar na Lista de episódios sem nota mas vistos
   - Dar nota a um episódio não visto e verificar na Lista de episódios com nota mas não vistos

#### 4.1 Descrição de cenários

- Cenário: Busca por nome existente na base
  - Dado que estou na página principal
  - Quando eu pesquisar por uma série existente na base
  - Então eu devo ver as informações da série pesquisada

- Cenário: Verificar a integridade das maiores notas dos últimos 15 dias
  - Dado que estou na página principal
  - Quando eu clicar na série que aparece na lista de maiores notas dos últimos 15 dias
  - Então a nota da série deve ser a mesma

- Cenário: Busca por nome acima de 256 carácteres
  - Dado que estou na página principal
  - Quando eu pesquisar por uma série que possui um nome maior que 256 carácteres
  - Então a busca deve reduzir o nome do filme para um tamanho limite

- Scenario: Click on edit synopsis and save without changing content
  - Given that I'm on a Series page
  - When I click on change synopsis
  - And click on the ok button
  - Then the information about the last editor should not change

- Scenario: Adding a Series to the grid
  - Given that I'm on a Series page
  - When I click on the add on my grid
  - Then the Series should be visible on my grid, at home page

## 5. Erros encontrados

Algumas falhas foram encontradas durante a execução da bateria de testes manual, seguem as mesmas:

- A nota da série no rank "Maiores notas dos ultimos 15 dias" apresentado na Home page difere da nota que a série possui em sua página própria. (Exemplo: série 'The Deuce' com nota 9.52 na Home page e com 9.5 em sua página)
- A nota da série no top 10/100 também difere da apresentada na página da série. (Exemplo: Série 'When They See Us' verificada no top 10 de 2018/2019 estava com nota 9.46 enquanto na página da mesma estava 9.48)
- A posição das séries que possuem mesma nota, trocam a cada vez que a página é recarregada no top 10 e top 100. (Exemplo Friends e Anne que estão em 6/7 e ficam intercambiando durante os "refresh's")
- O top 10 apresenta mais de 10 séries, assim como o top 100 apresenta mais de 100.
- Clicar em Editar sinópse, não alterar o conteúdo e finalizar a edição, sobrepoem a autoria sobre a sinopse.
- Clicar nas enquetes recentes presentes na home page não leva a página da enquete.
- A página principal possui uma quebra na parte inferior, quando vista na resolução 1920x1080, onde fica situado a timeline do Instagram.

## 6. Melhoria surgerida

Várias melhorias foram identificadas durante os testes:

- Ranking de mais populares na tela principal não possui legenda no topo, não deixando claro o que seria o número ao lado do nome da série.
- A página principal de um usuário logado é confusa, apresenta as informações de forma embaralhada e com pouco/nenhum relacionamento entre as mesmas.
- O calendário não possui opção de remover determinada série pela visão, o que seria um adicional interessante.
- A página "minhas séries" possui um foco mais voltado a estatísticas do que as séries escolhidas, propriamente ditas.
- Deixar os elementos na página principal com tamanhos padrão (O timeline do twitter e do instagram ficam com tamanho diferentes, com o do twitter menor, perdendo espaço enquanto o do instagram quebra o alinhamento proposto)

## 7. Resultado

Analisando o resultado dos testes, algumas funcionalidades seguem com falhas, porém nenhum delas afeta a funcionalidade principal do Banco de Séries, que seria a possibilidade de salvar e arquivar informações das séries que você e seus amigos acompanham, além pode compartilhar com a comunidade o que você pensa sobre aquela determinada série. A plataforma poderá crescer bastante com a correção dos erros encontrados e melhorias sugeridas, acarretando em uma melhor experiência do usuário, o que com certeza tratá mais colaboradores para a mesma. 