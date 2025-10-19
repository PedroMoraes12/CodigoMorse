package codigomorse;

import java.util.Scanner;

// Classe que representa um nó da árvore binária
class Nodo {
    char caractere;      // Caractere armazenado no nó (letra, número ou espaço)
    Nodo esquerda;       // Ponteiro para o filho esquerdo (representa ponto '.')
    Nodo direita;        // Ponteiro para o filho direito (representa traço '-')
    
    // Construtor do nó
    Nodo(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;   // Inicialmente sem filhos
        this.direita = null;    // Inicialmente sem filhos
    }
}

// Classe que gerencia a árvore binária do código Morse
class ArvoreBinariaMorse {
    Nodo raiz;  // Raiz da árvore binária
    
    // Construtor da árvore - cria a raiz vazia
    ArvoreBinariaMorse() {
        this.raiz = new Nodo(' ');  // Raiz inicia com espaço (nó vazio)
    }
    
    // Método que inicializa a árvore com todos os caracteres Morse padrão
    void inicializar() {
        // Primeiro nível: E (ponto) e T (traço)
        inserir(".", 'E');
        inserir("-", 'T');
        
        // Segundo nível: I, A, N, M
        inserir("..", 'I');
        inserir(".-", 'A');
        inserir("-.", 'N');
        inserir("--", 'M');
        
        // Terceiro nível: S, U, R, W, D, K, G, O
        inserir("...", 'S');
        inserir("..-", 'U');
        inserir(".-.", 'R');
        inserir(".--", 'W');
        inserir("-..", 'D');
        inserir("-.-", 'K');
        inserir("--.", 'G');
        inserir("---", 'O');
        
        // Quarto nível: H, V, F, L, P, J, B, X, C, Y, Z, Q
        inserir("....", 'H');
        inserir("...-", 'V');
        inserir("..-.", 'F');
        inserir(".-..", 'L');
        inserir(".--.", 'P');
        inserir(".---", 'J');
        inserir("-...", 'B');
        inserir("-..-", 'X');
        inserir("-.-.", 'C');
        inserir("-.--", 'Y');
        inserir("--..", 'Z');
        inserir("--.-", 'Q');
        
        // Números: 0-9 (quinto nível)
        inserir(".....", '5');
        inserir("....-", '4');
        inserir("...--", '3');
        inserir("..---", '2');
        inserir(".----", '1');
        inserir("-....", '6');
        inserir("--...", '7');
        inserir("---..", '8');
        inserir("----.", '9');
        inserir("-----", '0');
    }
    
    // Método para inserir um caractere na árvore baseado no código Morse
    void inserir(String codigo, char caractere) {
        Nodo atual = raiz;  // Começa na raiz
        
        // Percorre cada símbolo do código Morse (ponto ou traço)
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);  // Pega o símbolo atual
            
            if (simbolo == '.') {
                // Se for ponto, vai para a esquerda
                if (atual.esquerda == null) {
                    // Se não existe nó à esquerda, cria um novo
                    atual.esquerda = new Nodo(' ');
                }
                atual = atual.esquerda;  // Move para o nó da esquerda
            } else if (simbolo == '-') {
                // Se for traço, vai para a direita
                if (atual.direita == null) {
                    // Se não existe nó à direita, cria um novo
                    atual.direita = new Nodo(' ');
                }
                atual = atual.direita;  // Move para o nó da direita
            }
        }
        
        // Ao final do caminho, coloca o caractere no nó
        atual.caractere = caractere;
    }
    
    // Método para buscar um caractere individual pelo código Morse
    char buscarCaractere(String codigo) {
        // Primeiro valida se o código contém apenas pontos e traços
        for (int i = 0; i < codigo.length(); i++) {
            char c = codigo.charAt(i);
            if (c != '.' && c != '-') {
                return '?';  // Retorna '?' se caractere inválido
            }
        }
        
        Nodo atual = raiz;  // Começa na raiz
        
        // Percorre a árvore seguindo o código Morse
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);  // Pega o símbolo atual
            
            if (simbolo == '.') {
                // Se for ponto, vai para a esquerda
                if (atual.esquerda == null) {
                    return '?';  // Caminho não existe
                }
                atual = atual.esquerda;  // Move para esquerda
            } else if (simbolo == '-') {
                // Se for traço, vai para a direita
                if (atual.direita == null) {
                    return '?';  // Caminho não existe
                }
                atual = atual.direita;  // Move para direita
            }
        }
        
        // Verifica se o nó final tem um caractere definido
        if (atual.caractere == ' ') {
            return '?';  // Caminho existe mas não tem caractere
        }
        
        return atual.caractere;  // Retorna o caractere encontrado
    }
    
    // Método para decodificar uma mensagem Morse completa
    String buscar(String codigoMorse) {
        String resultado = "";       // String para armazenar o resultado final
        String caractereAtual = "";  // String para acumular o código do caractere atual
        
        // Percorre cada caractere da mensagem Morse
        for (int i = 0; i < codigoMorse.length(); i++) {
            char c = codigoMorse.charAt(i);  // Pega o caractere atual
            
            if (c == ' ') {
                // Espaço indica fim de um caractere Morse
                if (caractereAtual.length() > 0) {
                    // Se tem código acumulado, busca o caractere
                    char caractere = buscarCaractere(caractereAtual);
                    if (caractere != '?') {
                        resultado += caractere;  // Adiciona caractere válido
                    } else {
                        resultado += '?';  // Adiciona '?' para caractere inválido
                    }
                    caractereAtual = "";  // Reseta o código acumulado
                }
            } else if (c == '/') {
                // Barra indica separação entre palavras
                if (caractereAtual.length() > 0) {
                    // Processa último caractere antes da barra
                    char caractere = buscarCaractere(caractereAtual);
                    if (caractere != '?') {
                        resultado += caractere;
                    } else {
                        resultado += '?';
                    }
                    caractereAtual = "";
                }
                resultado += " ";  // Adiciona espaço entre palavras
            } else if (c == '.' || c == '-') {
                // Ponto ou traço - acumula no código atual
                caractereAtual += c;
            }
        }
        
        // Processa o último caractere se ainda houver código acumulado
        if (caractereAtual.length() > 0) {
            char caractere = buscarCaractere(caractereAtual);
            if (caractere != '?') {
                resultado += caractere;
            } else {
                resultado += '?';
            }
        }
        
        return resultado;  // Retorna a mensagem decodificada
    }
    
    // Método para remover um caractere da árvore
    void remover(String codigo) {
        Nodo atual = raiz;        // Nó atual durante a navegação
        Nodo anterior = null;     // Nó anterior (pai do atual)
        char ultimoMovimento = ' ';  // Última direção tomada ('.' ou '-')
        
        // Navega até o nó a ser removido
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            anterior = atual;  // Guarda o nó anterior
            
            if (simbolo == '.') {
                atual = atual.esquerda;      // Vai para esquerda
                ultimoMovimento = '.';       // Registra movimento
            } else if (simbolo == '-') {
                atual = atual.direita;       // Vai para direita
                ultimoMovimento = '-';       // Registra movimento
            }
            
            // Se chegou a um nó nulo, o código não existe
            if (atual == null) {
                return;
            }
        }
        
        // Verifica se é um nó folha (sem filhos)
        if (atual.esquerda == null && atual.direita == null) {
            // Remove o nó folha
            if (ultimoMovimento == '.' && anterior != null) {
                anterior.esquerda = null;  // Remove da esquerda do pai
            } else if (ultimoMovimento == '-' && anterior != null) {
                anterior.direita = null;   // Remove da direita do pai
            }
        } else {
            // Se não é folha, apenas marca como vazio (mantém estrutura)
            atual.caractere = ' ';
        }
    }
    
    // Método para exibir a árvore completa
    void exibir() {
        System.out.println("Arvore Binaria do Codigo Morse:");
        exibirRecursivo(raiz, 0, "Raiz");  // Inicia exibição recursiva
    }
    
    // Método recursivo para exibir a árvore de forma hierárquica
    void exibirRecursivo(Nodo nodo, int nivel, String direcao) {
        if (nodo == null) {
            return;  // Caso base: nó nulo, para recursão
        }
        
        // Cria indentação baseada no nível
        String espacos = "";
        for (int i = 0; i < nivel; i++) {
            espacos += "  ";  // Dois espaços por nível
        }
        
        // Exibe o nó atual com indentação
        System.out.println(espacos + direcao + ": '" + nodo.caractere + "'");
        
        // Recursão para subárvore esquerda
        exibirRecursivo(nodo.esquerda, nivel + 1, "Esq(.)");
        // Recursão para subárvore direita
        exibirRecursivo(nodo.direita, nivel + 1, "Dir(-)");
    }
}

// Classe principal com o método main e interface do usuário
public class CodigoMorse {
    public static void main(String[] args) {
        // Cria e inicializa a árvore Morse
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();  // Insere todos os caracteres padrão
        
        Scanner scanner = new Scanner(System.in);  // Scanner para entrada do usuário
        
        System.out.println("=== SISTEMA DE CODIGO MORSE ===");
        
        boolean executando = true;  // Controla o loop do menu
        
        // Loop principal do programa
        while (executando) {
            // Exibe menu de opções
            System.out.println("\nOpcoes:");
            System.out.println("1 - Buscar caractere por codigo Morse");
            System.out.println("2 - Decodificar mensagem Morse");
            System.out.println("3 - Inserir novo caractere");
            System.out.println("4 - Remover caractere");
            System.out.println("5 - Exibir arvore");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            
            String opcao = scanner.nextLine();  // Lê opção do usuário
            
            // Processa a opção escolhida
            switch (opcao) {
                case "1":
                    // Busca caractere individual
                    System.out.print("Digite o codigo Morse (ex: ...): ");
                    String codigo = scanner.nextLine();
                    char resultado = arvore.buscarCaractere(codigo);
                    if (resultado == '?') {
                        System.out.println("Erro: Codigo Morse invalido ou caractere nao encontrado!");
                    } else {
                        System.out.println("Caractere: '" + resultado + "'");
                    }
                    break;
                    
                case "2":
                    // Decodifica mensagem completa
                    System.out.print("Digite a mensagem Morse (use ESPACO entre letras e / entre palavras): ");
                    String mensagem = scanner.nextLine();
                    String texto = arvore.buscar(mensagem);
                    if (texto.contains("?")) {
                        System.out.println("Mensagem decodificada: " + texto);
                        System.out.println("Atencao: '?' indica caracteres nao reconhecidos!");
                    } else {
                        System.out.println("Mensagem decodificada: " + texto);
                    }
                    break;
                    
                case "3":
                    // Insere novo caractere personalizado
                    System.out.print("Digite o codigo Morse: ");
                    String novoCodigo = scanner.nextLine();
                    System.out.print("Digite o caractere: ");
                    String charInput = scanner.nextLine();
                    if (charInput.length() > 0) {
                        // Valida se o código contém apenas pontos e traços
                        boolean codigoValido = true;
                        for (int i = 0; i < novoCodigo.length(); i++) {
                            char c = novoCodigo.charAt(i);
                            if (c != '.' && c != '-') {
                                codigoValido = false;
                                break;
                            }
                        }
                        
                        if (codigoValido) {
                            arvore.inserir(novoCodigo, charInput.charAt(0));
                            System.out.println("Caractere inserido com sucesso!");
                        } else {
                            System.out.println("Erro: O codigo Morse deve conter apenas pontos (.) e tracos (-)!");
                        }
                    }
                    break;
                    
                case "4":
                    // Remove caractere
                    System.out.print("Digite o codigo Morse a remover: ");
                    String codigoRemover = scanner.nextLine();
                    arvore.remover(codigoRemover);
                    System.out.println("Remocao concluida!");
                    break;
                    
                case "5":
                    // Exibe estrutura da árvore
                    arvore.exibir();
                    break;
                    
                case "6":
                    // Sai do programa
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    // Opção inválida
                    System.out.println("Opcao invalida!");
            }
        }
        
        scanner.close();  // Fecha o scanner
    }
}
