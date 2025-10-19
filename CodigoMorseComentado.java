package codigomorse;

import java.util.Scanner;

// Classe que representa um nó da árvore binária
class Nodo {
    char caractere;
    Nodo esquerda;
    Nodo direita;
    
    Nodo(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.direita = null;
    }
}

// Classe que gerencia a árvore binária do código Morse
class ArvoreBinariaMorse {
    Nodo raiz;
    
    ArvoreBinariaMorse() {
        this.raiz = new Nodo(' ');
    }
    
    void inicializar() {
        inserir(".", 'E');
        inserir("-", 'T');
        inserir("..", 'I');
        inserir(".-", 'A');
        inserir("-.", 'N');
        inserir("--", 'M');
        inserir("...", 'S');
        inserir("..-", 'U');
        inserir(".-.", 'R');
        inserir(".--", 'W');
        inserir("-..", 'D');
        inserir("-.-", 'K');
        inserir("--.", 'G');
        inserir("---", 'O');
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
    
    void inserir(String codigo, char caractere) {
        Nodo atual = raiz;
        for (char simbolo : codigo.toCharArray()) {
            if (simbolo == '.') {
                if (atual.esquerda == null) atual.esquerda = new Nodo(' ');
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) atual.direita = new Nodo(' ');
                atual = atual.direita;
            }
        }
        atual.caractere = caractere;
    }
    
    char buscarCaractere(String codigo) {
        for (char c : codigo.toCharArray()) {
            if (c != '.' && c != '-') return '?';
        }
        Nodo atual = raiz;
        for (char simbolo : codigo.toCharArray()) {
            if (simbolo == '.') {
                if (atual.esquerda == null) return '?';
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) return '?';
                atual = atual.direita;
            }
        }
        return (atual.caractere == ' ') ? '?' : atual.caractere;
    }
    
    String buscar(String codigoMorse) {
        StringBuilder resultado = new StringBuilder();
        StringBuilder caractereAtual = new StringBuilder();
        
        for (char c : codigoMorse.toCharArray()) {
            if (c == ' ') {
                if (caractereAtual.length() > 0) {
                    resultado.append(buscarCaractere(caractereAtual.toString()));
                    caractereAtual.setLength(0);
                }
            } else if (c == '/') {
                if (caractereAtual.length() > 0) {
                    resultado.append(buscarCaractere(caractereAtual.toString()));
                    caractereAtual.setLength(0);
                }
                resultado.append(' ');
            } else if (c == '.' || c == '-') {
                caractereAtual.append(c);
            }
        }
        if (caractereAtual.length() > 0)
            resultado.append(buscarCaractere(caractereAtual.toString()));
        
        return resultado.toString();
    }
    
    void remover(String codigo) {
        Nodo atual = raiz, anterior = null;
        char ultimoMovimento = ' ';
        for (char simbolo : codigo.toCharArray()) {
            anterior = atual;
            if (simbolo == '.') {
                atual = atual.esquerda;
                ultimoMovimento = '.';
            } else if (simbolo == '-') {
                atual = atual.direita;
                ultimoMovimento = '-';
            }
            if (atual == null) return;
        }
        if (atual.esquerda == null && atual.direita == null) {
            if (ultimoMovimento == '.' && anterior != null) anterior.esquerda = null;
            else if (ultimoMovimento == '-' && anterior != null) anterior.direita = null;
        } else {
            atual.caractere = ' ';
        }
    }
    
    void exibir() {
        System.out.println("Arvore Binaria do Codigo Morse:");
        exibirRecursivo(raiz, 0, "Raiz");
    }
    
    void exibirRecursivo(Nodo nodo, int nivel, String direcao) {
        if (nodo == null) return;
        String espacos = "  ".repeat(nivel);
        System.out.println(espacos + direcao + ": '" + nodo.caractere + "'");
        exibirRecursivo(nodo.esquerda, nivel + 1, "Esq(.)");
        exibirRecursivo(nodo.direita, nivel + 1, "Dir(-)");
    }
}

// Classe principal
public class CodigoMorse {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        System.out.println("=== SISTEMA DE CODIGO MORSE ===");

        while (executando) {
            System.out.println("\nOpcoes:");
            System.out.println("1 - Buscar caractere por codigo Morse");
            System.out.println("2 - Decodificar mensagem Morse");
            System.out.println("3 - Inserir novo caractere");
            System.out.println("4 - Remover caractere");
            System.out.println("5 - Exibir arvore");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Digite o codigo Morse (ex: ...): ");
                    char resultado = arvore.buscarCaractere(scanner.nextLine());
                    System.out.println(resultado == '?' ? "Codigo invalido!" : "Caractere: '" + resultado + "'");
                }
                case "2" -> {
                    System.out.print("Digite a mensagem Morse (use ESPACO entre letras e / entre palavras): ");
                    String texto = arvore.buscar(scanner.nextLine());
                    System.out.println("Mensagem decodificada: " + texto);
                }
                case "3" -> {
                    System.out.print("Digite o codigo Morse: ");
                    String novoCodigo = scanner.nextLine();
                    System.out.print("Digite o caractere: ");
                    String charInput = scanner.nextLine();
                    if (!charInput.isEmpty()) {
                        boolean valido = novoCodigo.chars().allMatch(c -> c == '.' || c == '-');
                        if (valido) {
                            arvore.inserir(novoCodigo, charInput.charAt(0));
                            System.out.println("Caractere inserido com sucesso!");
                        } else System.out.println("Erro: apenas '.' e '-' permitidos!");
                    }
                }
                case "4" -> {
                    System.out.print("Digite o codigo Morse a remover: ");
                    arvore.remover(scanner.nextLine());
                    System.out.println("Remocao concluida!");
                }
                case "5" -> arvore.exibir();
                case "6" -> {
                    executando = false;
                    System.out.println("Saindo...");
                }
                default -> System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}
