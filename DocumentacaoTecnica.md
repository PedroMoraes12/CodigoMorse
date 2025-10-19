# Diagrama da Árvore Morse

Raiz (' ')
- Esq(.) -> 'E'
  - Esq(.) -> 'I'
    - Esq(.) -> 'S'
      - Esq(.) -> 'H'
        - Esq(.) -> '5'
        - Dir(-) -> '4'
      - Dir(-) -> 'V'
        - Dir(-) -> '3'
    - Dir(-) -> 'U'
      - Esq(.) -> 'F'
      - Dir(-) -> '2'
  - Dir(-) -> 'A'
    - Esq(.) -> 'R'
      - Esq(.) -> 'L'
    - Dir(-) -> 'W'
      - Esq(.) -> 'P'
      - Dir(-) -> 'J'
        - Dir(-) -> '1'
- Dir(-) -> 'T'
  - Esq(.) -> 'N'
    - Esq(.) -> 'D'
      - Esq(.) -> 'B'
        - Esq(.) -> '6'
      - Dir(-) -> 'X'
    - Dir(-) -> 'K'
      - Esq(.) -> 'C'
      - Dir(-) -> 'Y'
  - Dir(-) -> 'M'
    - Esq(.) -> 'G'
      - Esq(.) -> 'Z'
        - Esq(.) -> '7'
      - Dir(-) -> 'Q'
    - Dir(-) -> 'O'
      - Esq(.) -> '8'
      - Dir(-) -> '9'
        - Dir(-) -> '0'

# Funcionalidades Implementadas

## 1. Inicialização Automática

- Insere todas as 26 letras do alfabeto (A-Z)
- Insere todos os 10 números (0-9)
- Segue padrão internacional do código Morse

## 2. Busca e Decodificação

- Busca individual de caracteres por código Morse
- Decodificação de mensagens completas
- Suporte a múltiplas palavras
- Validação de códigos inválidos

## 3. Inserção Personalizada

- Adição de novos caracteres personalizados
- Validação de formato do código Morse
- Preservação da estrutura da árvore

## 4. Remoção Segura

- Remoção de caracteres existentes
- Preservação da estrutura da árvore
- Tratamento de nós folha e intermediários

## 5. Exibição Hierárquica

- Visualização clara da estrutura da árvore
- Indentação por níveis
- Identificação de direções (Esq/Dir)

# Regras de Negócio Implementadas

Codificação Morse Padrão

1. Ponto (.) → Movimento para esquerda
2. Traço (-) → Movimento para direita
3. Espaço (' ') → Separador de caracteres
4. Barra (/) → Separador de palavras


# Validações

1. Códigos Morse devem conter apenas [.-]
2. Caracteres não encontrados retornam '?'
3. Inserções validam formato antes de adicionar

# Conformidade com Requisitos

Estruturas Permitidas Utilizadas

- String
- int
- char
- Scanner (para input)
- length() (apenas em Strings)

# Restrições Respeitadas

- StringBuilder (NÃO utilizado)
- Arrays (NÃO utilizado)
- List/Vector (NÃO utilizado)
- Try-catch (NÃO utilizado)
- Throws (NÃO utilizado)
- Integer (NÃO utilizado)
- Qualquer coleção (NÃO utilizado)

# Casos de Borda Tratados

- Código vazio retorna '?'
- Caracteres inválidos na entrada
- Caminho inexistente na árvore
- Remoção de nó intermediário
- Mensagem vazia retorna string vazia
- Códigos com caracteres não Morse são rejeitados
