package codigomorse;

import java.util.Scanner;

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
        
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Nodo(' ');
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new Nodo(' ');
                }
                atual = atual.direita;
            }
        }
        
        atual.caractere = caractere;
    }
    
    char buscarCaractere(String codigo) {
        for (int i = 0; i < codigo.length(); i++) {
            char c = codigo.charAt(i);
            if (c != '.' && c != '-') {
                return '?';
            }
        }
        
        Nodo atual = raiz;
        
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    return '?';
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    return '?';
                }
                atual = atual.direita;
            }
        }
        
        if (atual.caractere == ' ') {
            return '?';
        }
        
        return atual.caractere;
    }
    
    String buscar(String codigoMorse) {
        String resultado = "";
        String caractereAtual = "";
        
        for (int i = 0; i < codigoMorse.length(); i++) {
            char c = codigoMorse.charAt(i);
            
            if (c == ' ') {
                if (caractereAtual.length() > 0) {
                    char caractere = buscarCaractere(caractereAtual);
                    if (caractere != '?') {
                        resultado += caractere;
                    } else {
                        resultado += '?';
                    }
                    caractereAtual = "";
                }
            } else if (c == '/') {
                if (caractereAtual.length() > 0) {
                    char caractere = buscarCaractere(caractereAtual);
                    if (caractere != '?') {
                        resultado += caractere;
                    } else {
                        resultado += '?';
                    }
                    caractereAtual = "";
                }
                resultado += " ";
            } else if (c == '.' || c == '-') {
                caractereAtual += c;
            }
        }
        
        if (caractereAtual.length() > 0) {
            char caractere = buscarCaractere(caractereAtual);
            if (caractere != '?') {
                resultado += caractere;
            } else {
                resultado += '?';
            }
        }
        
        return resultado;
    }
    
    void remover(String codigo) {
        Nodo atual = raiz;
        Nodo anterior = null;
        char ultimoMovimento = ' ';
        
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            anterior = atual;
            
            if (simbolo == '.') {
                atual = atual.esquerda;
                ultimoMovimento = '.';
            } else if (simbolo == '-') {
                atual = atual.direita;
                ultimoMovimento = '-';
            }
            
            if (atual == null) {
                return;
            }
        }
        
        if (atual.esquerda == null && atual.direita == null) {
            if (ultimoMovimento == '.' && anterior != null) {
                anterior.esquerda = null;
            } else if (ultimoMovimento == '-' && anterior != null) {
                anterior.direita = null;
            }
        } else {
            atual.caractere = ' ';
        }
    }
    
    void exibir() {
        System.out.println("Arvore Binaria do Codigo Morse:");
        exibirRecursivo(raiz, 0, "Raiz");
    }
    
    void exibirRecursivo(Nodo nodo, int nivel, String direcao) {
        if (nodo == null) {
            return;
        }
        
        String espacos = "";
        for (int i = 0; i < nivel; i++) {
            espacos += "  ";
        }
        
        System.out.println(espacos + direcao + ": '" + nodo.caractere + "'");
        
        exibirRecursivo(nodo.esquerda, nivel + 1, "Esq(.)");
        exibirRecursivo(nodo.direita, nivel + 1, "Dir(-)");
    }
}

public class CodigoMorse {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE CODIGO MORSE ===");
        
        boolean executando = true;
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
                case "1":
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
                    System.out.print("Digite o codigo Morse: ");
                    String novoCodigo = scanner.nextLine();
                    System.out.print("Digite o caractere: ");
                    String charInput = scanner.nextLine();
                    if (charInput.length() > 0) {
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
                    System.out.print("Digite o codigo Morse a remover: ");
                    String codigoRemover = scanner.nextLine();
                    arvore.remover(codigoRemover);
                    System.out.println("Remocao concluida!");
                    break;
                    
                case "5":
                    arvore.exibir();
                    break;
                    
                case "6":
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        
        scanner.close();
    }
}
