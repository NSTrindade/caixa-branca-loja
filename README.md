# Seminário QTS — Teste de Caixa Branca (Grupo 3)

Projeto de demonstração em **Java + JUnit 5 + JaCoCo** usando como sistema de exemplo uma **loja virtual**
(desconto por tipo de cliente, carrinho de compras, parcelamento e fechamento de pedido).

## O que tem no projeto

| Arquivo | O que demonstra |
|---|---|
| `DescontoService` | **Decisões aninhadas** (`if / else if / else`) → cobertura de decisões/ramos |
| `ParcelamentoService` | **Laço `while`** com duas condições de parada → 0, 1, várias iterações e limite |
| `Carrinho` | **Laço `for`** com `if` e `continue` → cobertura de caminhos |
| `Item` | Validações (exceções) e fronteira de estoque |
| `PedidoService` | Integra tudo: subtotal → desconto → total → parcelas |
| `src/test/java/br/etec/loja/` | **Suíte COMPLETA** (todos os ramos testados) |
| `src/test/java/br/etec/loja/parcial/` | **Suíte PARCIAL de propósito** (para mostrar o que a cobertura revela) |

## Pré-requisitos

- **JDK 17 ou superior** (`java -version`)
- **Maven 3.8 ou superior** (`mvn -version`)
- Internet na primeira execução (o Maven baixa JUnit e JaCoCo)

> Sem Maven? Instale em https://maven.apache.org/download.cgi (ou, no Windows: `winget install Apache.Maven`).
> Alternativa: abra a pasta no **IntelliJ IDEA** (File > Open > selecione o `pom.xml` > Open as Project), que já traz Maven embutido.

## Passo a passo

### 1. Extrair e entrar na pasta
```
unzip caixa-branca-loja.zip
cd caixa-branca-loja
```
(No Windows: botão direito no .zip > Extrair tudo; depois abra o terminal dentro da pasta.)

### 2. Rodar o programa de demonstração (opcional)
```
mvn -q compile exec:java -Dexec.mainClass=br.etec.loja.Main
```
Saída esperada:
```
Subtotal : R$ 380.00
Desconto : R$ 38.00
Total    : R$ 342.00
Parcelas : ate 6x
```
(O Monitor, sem estoque, é ignorado no subtotal — é o caminho do `continue`.)

### 3. Rodar a SUÍTE PARCIAL e ver a cobertura baixa (demonstração do problema)
```
mvn clean test -Dtest="*Parcial*"
```
Depois abra no navegador:
```
target/site/jacoco/index.html
```
Clique em `br.etec.loja` > `DescontoService`. Linhas **vermelhas/amarelas** = código ou ramos que nenhum teste exercitou
(VIP < 500, REGULAR entre 200 e 499,99, REGULAR < 200, tipo nulo, valor negativo).
Os losangos coloridos na margem mostram os **ramos** (`2 of 4 branches missed`, por exemplo).

### 4. Rodar a SUÍTE COMPLETA e ver a cobertura subir
```
mvn clean test
```
Abra novamente `target/site/jacoco/index.html`. Os ramos de `DescontoService`, `ParcelamentoService`, `Carrinho`,
`Item` e `PedidoService` devem aparecer **verdes**.

### 5. Ler o relatório do JaCoCo (o que explicar na apresentação)

| Coluna | Significado |
|---|---|
| **Missed Instructions / Cov.** | Cobertura de **instruções** (bytecode) |
| **Missed Branches / Cov.** | Cobertura de **decisões/ramos** (cada `if` tem 2 ramos: verdadeiro e falso) |
| **Missed Lines** | Linhas de código nunca executadas |
| Verde | Executado e todos os ramos testados |
| Amarelo | Linha executada, mas **só parte dos ramos** foi testada |
| Vermelho | Linha **nunca executada** |

### 6. Rodar um único teste ou classe
```
mvn test -Dtest=DescontoServiceTest
mvn test -Dtest=DescontoServiceTest#vipCompraAlta
```

## Mapa dos casos de teste (CT) → caminho do código

**DescontoService**
| CT | Entrada | Esperado | Caminho coberto |
|---|---|---|---|
| D01 | VIP, 600 | 20% | VIP → ≥ 500 |
| D02 | VIP, 100 | 10% | VIP → < 500 |
| D03 | REGULAR, 800 | 10% | REGULAR → ≥ 500 |
| D04 | REGULAR, 300 | 5% | REGULAR → 200–499,99 |
| D05 | REGULAR, 50 | 0% | REGULAR → < 200 |
| D06 | VISITANTE, 1000 | 0% | else (visitante) |
| D07 | VIP, 500 | 20% | fronteira `>=` |
| D08 | VIP, 499,99 | 10% | fronteira |
| D09 | REGULAR, 200 | 5% | fronteira `>=` |
| D10 | REGULAR, 199,99 | 0% | fronteira |
| D11 | tipo nulo | exceção | validação |
| D12 | valor −1 | exceção | validação |

**ParcelamentoService** (laço)
| CT | Valor | Parcelas | Situação do laço |
|---|---|---|---|
| P01 | 40 | 1 | zero iterações |
| P02 | 99,99 | 1 | zero iterações (fronteira) |
| P03 | 100 | 2 | uma iteração |
| P04 | 150 | 3 | várias iterações |
| P05 | 1000 | 12 | para pelo limite de 12 |
| P06 | 600 | 12 | fronteira (600 / 12 = 50) |
| P07/P08 | 0 / −10 | exceção | validação |

## Problemas comuns

- **`release version 17 not supported`** → seu JDK é anterior ao 17. Instale JDK 17+ ou 21.
- **`mvn` não é reconhecido** → Maven não está no PATH; instale ou use o IntelliJ.
- **Relatório não aparece em `target/site/jacoco`** → rode `mvn clean test` (o relatório é gerado na fase `test`).
- **Falha ao baixar dependências** → verifique a internet/proxy da rede; rode de novo.
- **No Java 25+** pode ser necessário atualizar a versão do JaCoCo no `pom.xml` para a mais recente.
