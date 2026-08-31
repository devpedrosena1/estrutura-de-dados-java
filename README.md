# Guia Completo: Estruturas de Dados e Java Collections Framework

Material de estudos prático e aprofundado cobrindo os fundamentos de estruturas de dados, tipagem homogênea e heterogênea, ordenação, processamento matricial e toda a arquitetura do **Java Collections Framework (JCF)**.

---

## Sumário

- [1. Fundamentos de Estruturas de Dados](#1-fundamentos-de-estruturas-de-dados)
- [2. Homogeneidade e Tipagem](#2-homogeneidade-e-tipagem)
- [3. Comparativo: Array vs ArrayList](#3-comparativo-array-vs-arraylist)
- [4. Ordenação com Collections.sort()](#4-ordenação-com-collectionssort)
- [5. Processamento Matricial (Estudo de Caso)](#5-processamento-matricial-estudo-de-caso)
- [6. Arquitetura do Java Collections Framework](#6-arquitetura-do-java-collections-framework)
- [7. Genéricos (Generics)](#7-genéricos-generics)
- [8. Aprofundamento nas Interfaces de Coleções](#8-aprofundamento-nas-interfaces-de-coleções)
  - [8.1. Interface List (ArrayList)](#81-interface-list-arraylist)
  - [8.2. Interface Set (HashSet)](#82-interface-set-hashset)
  - [8.3. Interface Queue (ArrayDeque)](#83-interface-queue-arraydeque)
  - [8.4. Interface Map (HashMap)](#84-interface-map-hashmap)
- [9. Tabela Comparativa Geral](#9-tabela-comparativa-geral)
- [10. Referências e Links Úteis](#10-referências-e-links-úteis)

---

## 1. Fundamentos de Estruturas de Dados

As estruturas de dados fornecem formas organizadas de armazenar, acessar e manipular informações eficientemente na memória:

* **Array (Vetor):** Organiza os elementos em posições sequenciais e de tamanho fixo, oferecendo acesso direto instantâneo ($O(1)$) através de índices numéricos.
* **Matriz:** Estrutura bidimensional baseada em linhas e colunas (vetor de vetores), ideal para representar tabelas, grades e coordenadas cartesianas.
* **Lista (`List`):** Mantém elementos em uma sequência ordenada, com redimensionamento dinâmico conforme novas inserções ou remoções ocorrem.
* **Conjunto (`Set`):** Estrutura focada em unicidade que assegura que nenhum elemento seja armazenado em duplicidade.
* **Fila (`Queue`):** Coleção baseada na regra FIFO (*First In, First Out*), onde o primeiro elemento inserido é o primeiro a ser processado.
* **Mapa (`Map`):** Estrutura associativa baseada no padrão par chave-valor (`Key -> Value`), onde cada chave única aponta para um valor correspondente.

---

## 2. Homogeneidade e Tipagem

### Estruturas Homogêneas
Armazenam exclusivamente dados de um mesmo tipo.

```java
// Armazena unicamente objetos do tipo String
List<String> nomes = new ArrayList<>();
nomes.add("Alice");
nomes.add("Bob");
```

### Estruturas Heterogêneas
Agrupam múltiplos tipos de dados sob uma mesma estrutura ou classe de domínio.

```java
public class Usuario {
    private int idade;
    private String nome;
    private boolean ativo;

    public Usuario(int idade, String nome, boolean ativo) {
        this.idade = idade;
        this.nome = nome;
        this.ativo = ativo;
    }
}
```

---

## 3. Comparativo: Array vs ArrayList

| Critério | Array Nativo | ArrayList |
| :--- | :--- | :--- |
| **Tamanho** | Fixo (estático) | Dinâmico (variável) |
| **Tipos Primitivos** | Aceita diretamente (`int[]`, etc.) | Requer classes empacotadoras (`Integer`, etc.) |
| **Acesso por Índice** | Sim | Sim |
| **Inserção Pronta** | Não (requer realocação manual) | Sim (`.add()`) |
| **Remoção Pronta** | Não | Sim (`.remove()`) |
| **Propriedade de Tamanho** | `.length` (atributo) | `.size()` (método) |
| **Sintaxe de Acesso** | `array[i]` | `lista.get(i)` |
| **Sintaxe de Alteração** | `array[i] = valor` | `lista.set(i, valor)` |

> **Critério de Decisão:** O tutorial oficial recomenda considerar `ArrayList` quando o programa precisa percorrer elementos com frequência ou manipulá-los por métodos utilitários. Use `Array` nativo quando a quantidade de elementos for estritamente estática e o controle de overhead de memória for prioritário.

---

## 4. Ordenação com Collections.sort()

A ordenação de listas em Java é realizada através da classe utilitária `java.util.Collections`.

### Ordem Crescente (Padrão)

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

List<Integer> valores = new ArrayList<>();
valores.add(30);
valores.add(10);
valores.add(20);

Collections.sort(valores);

// Resultado: [10, 20, 30]
```

### Ordem Decrescente

```java
Collections.sort(valores, Collections.reverseOrder());

// Resultado: [30, 20, 10]
```

---

## 5. Processamento Matricial (Estudo de Caso)

O algoritmo abaixo percorre uma matriz bidimensional de vendas (`vendas[linha][coluna]`), onde as **linhas** representam os dias da semana e as **colunas** representam os produtos.

### Visão Geral do Fluxo

```
[Passo 1] Exibir Matriz
    ├── [Passo 2] Total por Produto ──> [Passo 3] Produto Mais Vendido
    └── [Passo 4] Total por Dia     ──> [Passo 5] Dia de Maior Movimento
```

### Implementação em Java

```java
public class ProcessamentoVendas {
    public static void main(String[] args) {
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        String[] produtos = {"Notebook", "Smartphone", "Monitor"};
        
        int[][] vendas = {
            {10, 15, 5},
            {12, 18, 7},
            {8, 20, 6},
            {14, 22, 9},
            {20, 25, 12}
        };

        // Passo 1 — Exibir a Matriz
        for (int linha = 0; linha < vendas.length; linha++) {
            System.out.println(dias[linha]);
            for (int coluna = 0; coluna < vendas[linha].length; coluna++) {
                System.out.printf("  %s: %d%n", produtos[coluna], vendas[linha][coluna]);
            }
        }

        // Passo 2 — Calcular Total por Produto (acumulação por coluna)
        int[] totalPorProduto = new int[produtos.length];
        for (int linha = 0; linha < vendas.length; linha++) {
            for (int coluna = 0; coluna < vendas[linha].length; coluna++) {
                totalPorProduto[coluna] += vendas[linha][coluna];
            }
        }

        // Passo 3 — Localizar Produto Mais Vendido
        int indiceMaisVendido = 0;
        for (int i = 1; i < totalPorProduto.length; i++) {
            if (totalPorProduto[i] > totalPorProduto[indiceMaisVendido]) {
                indiceMaisVendido = i;
            }
        }

        // Passo 4 — Calcular Total por Dia (acumulação por linha)
        int[] totalPorDia = new int[dias.length];
        for (int linha = 0; linha < vendas.length; linha++) {
            for (int coluna = 0; coluna < vendas[linha].length; coluna++) {
                totalPorDia[linha] += vendas[linha][coluna];
            }
        }

        // Passo 5 — Localizar Dia de Maior Movimento
        int indiceDiaMaisMovimentado = 0;
        for (int i = 1; i < totalPorDia.length; i++) {
            if (totalPorDia[i] > totalPorDia[indiceDiaMaisMovimentado]) {
                indiceDiaMaisMovimentado = i;
            }
        }
    }
}
```

---

## 6. Arquitetura do Java Collections Framework

O **Java Collections Framework (JCF)** disponibiliza uma arquitetura unificada para representar e manipular coleções de objetos de forma padronizada.

```
             ┌─────────────────┐
             │   Collection    │ (Interface Raiz)
             └────────┬────────┘
        ┌─────────────┼─────────────┐
        │             │             │
 ┌──────┴──────┐ ┌────┴─────┐ ┌─────┴─────┐      ┌───────────┐
 │    List     │ │   Set    │ │   Queue   │      │    Map    │ (Não estende
 └─────────────┘ └──────────┘ └───────────┘      └───────────┘  Collection)
        │             │             │                  │
 ┌──────┴──────┐ ┌────┴─────┐ ┌─────┴─────┐      ┌─────┴─────┐
 │  ArrayList  │ │ HashSet  │ │ArrayDeque │      │  HashMap  │
 └─────────────┘ └──────────┘ └───────────┘      └───────────┘
```

* **`Collection`:** Interface base da qual derivam diretamente `List`, `Set` e `Queue`.
* **`Map`:** Integra o Framework de Coleções, mas **não** herda da interface `Collection`, pois opera na estrutura de pares associativos (`Map<K, V>`).

---

## 7. Genéricos (Generics)

Introduzidos no Java 5, os **Generics** (`< >`) permitem definir, no momento da instanciação, qual tipo de dado a coleção irá armazenar.

### Vantagens
* **Segurança em tempo de compilação (*Type Safety*):** Evita que objetos de tipos incompatíveis sejam inseridos.
* **Eliminação de coerção explícita (*Type Casting*):** Elimina a necessidade de converter manualmente o tipo de dado ao recuperar elementos.

```java
// Garante que a lista aceite estritamente instâncias de String
List<String> produtos = new ArrayList<>();
produtos.add("Café"); // Válido

// A instrução abaixo gera erro de compilação:
// produtos.add(10);
```

---

## 8. Aprofundamento nas Interfaces de Coleções

### 8.1. Interface List (ArrayList)

Representa uma sequência ordenada de elementos que preserva a ordem de inserção e permite duplicatas.

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

List<String> pedidos = new ArrayList<>();

// Operações básicas
pedidos.add("Café");               // Inserção no final
pedidos.add(1, "Sanduíche");       // Inserção em posição específica
String primeiro = pedidos.get(0);  // Acesso por índice
pedidos.set(1, "Suco de Laranja"); // Substituição por índice
pedidos.remove("Bolo");            // Remoção por objeto
pedidos.remove(0);                 // Remoção por índice
boolean existe = pedidos.contains("Café"); // Verificação de existência
int total = pedidos.size();        // Quantidade de itens
boolean vazia = pedidos.isEmpty(); // Verificação de lista vazia
pedidos.clear();                   // Limpeza total
```

#### Formas de Percorrer uma List
```java
// 1. For tradicional (com índice)
for (int i = 0; i < pedidos.size(); i++) {
    System.out.println(pedidos.get(i));
}

// 2. For-each (iteração simplificada)
for (String pedido : pedidos) {
    System.out.println(pedido);
}

// 3. Iterator (controle de iteração)
Iterator<String> iterador = pedidos.iterator();
while (iterador.hasNext()) {
    System.out.println(iterador.next());
}
```

---

### 8.2. Interface Set (HashSet)

Estrutura voltada para cenários onde a **unicidade dos dados** é fundamental.

* Não permite duplicatas (o método `.add()` retorna `false` se o item já existir).
* Não mantém ordem posicional e não oferece acesso por índice.

```java
import java.util.HashSet;
import java.util.Set;

Set<String> codigosUtilizados = new HashSet<>();
codigosUtilizados.add("PROMO10");
codigosUtilizados.add("CLIENTE20");

// Tentativa de duplicata:
boolean inserido = codigosUtilizados.add("PROMO10"); // Retorna false

boolean utilizado = codigosUtilizados.contains("PROMO10");
codigosUtilizados.remove("PROMO10");
```

---

### 8.3. Interface Queue (ArrayDeque)

Estrutura baseada no princípio **FIFO (*First In, First Out*)**: o primeiro a entrar é o primeiro a sair.

```java
import java.util.ArrayDeque;
import java.util.Queue;

Queue<String> filaPedidos = new ArrayDeque<>();

// Inserção
filaPedidos.offer("Pedido 101");
filaPedidos.offer("Pedido 102");
filaPedidos.offer("Pedido 103");

// Consulta da cabeça da fila sem remover
String proximo = filaPedidos.peek(); // "Pedido 101"

// Remoção e atendimento da cabeça da fila
String atendido = filaPedidos.poll(); // Retorna e remove "Pedido 101"
```

---

### 8.4. Interface Map (HashMap)

Mapeia relações entre **chave e valor** (`Key -> Value`), garantindo consultas diretas de alta performance pela chave.

* As chaves são obrigatoriamente únicas.
* Associar um valor a uma chave já existente sobrescreve o valor anterior.

```java
import java.util.HashMap;
import java.util.Map;

Map<Integer, String> produtos = new HashMap<>();

// Inserção e Sobrescrita
produtos.put(101, "Café");
produtos.put(205, "Suco");
produtos.put(310, "Bolo");
produtos.put(205, "Suco de Laranja"); // Substitui "Suco"

// Recuperação e Verificação
String produto = produtos.get(205);                  // Retorna "Suco de Laranja"
boolean temCodigo = produtos.containsKey(205);       // true
boolean temProduto = produtos.containsValue("Bolo"); // true
produtos.remove(310);                                // Remove o par da chave 310
```

#### Formas de Percorrer um Map
```java
// 1. Percorrer apenas as chaves
for (Integer codigo : produtos.keySet()) {
    System.out.println("Código: " + codigo);
}

// 2. Percorrer apenas os valores
for (String prod : produtos.values()) {
    System.out.println("Produto: " + prod);
}

// 3. Percorrer pares chave-valor completos
for (Map.Entry<Integer, String> entrada : produtos.entrySet()) {
    System.out.printf("Código: %d | Produto: %s%n", entrada.getKey(), entrada.getValue());
}
```

---

## 9. Tabela Comparativa Geral

| Estrutura / Interface | Implementação Padrão | Duplicados? | Ordenação / Política | Acesso por Índice |
| :--- | :--- | :--- | :--- | :--- |
| **`Array`** | Nativo | Sim | Posição fixa | Sim (`array[i]`) |
| **`List`** | `ArrayList` | Sim | Ordem de inserção | Sim (`lista.get(i)`) |
| **`Set`** | `HashSet` | Não | Sem garantia de ordem | Não |
| **`Queue`** | `ArrayDeque` | Sim | FIFO (Fila) | Não (Acesso pela ponta) |
| **`Map`** | `HashMap` | Chaves: Não / Valores: Sim | Associativo (Chave $	o$ Valor) | Não (Acesso por Chave) |

---

## 10. Referências e Links Úteis

- [Documentação Oficial - Oracle Java Collections Framework](https://docs.oracle.com/en/java/javase/21/core/java-collections-framework.html)
- [Dev.java - Iterating over the Elements of a Collection](https://dev.java/learn/api/collections-framework/iterating/)
