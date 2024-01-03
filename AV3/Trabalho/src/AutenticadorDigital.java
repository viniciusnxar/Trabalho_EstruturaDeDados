public class AutenticadorDigital {

    // Este método processa um arquivo e imprime os hashes das árvores AVL criadas a partir das linhas do arquivo
    public static void gerarHashes(String nomeArquivo) throws Exception {
        String conteudoArquivo = EntradaArquivo.lerConteudo(nomeArquivo);
        Pilha<ArvoreAVL<String>> pilhaArvores = new Pilha<>();
        
        for (String linha : conteudoArquivo.split("\\n")) {
            Pilha<String> pilhaPalavras = criarPilhaPalavras(linha);
            ArvoreAVL<String> arvore = criarArvore(pilhaPalavras);
            pilhaArvores.empilhar(arvore);
        }
        imprimirHashes(pilhaArvores);
    }

// Cria uma pilha para armazenar as árvores AVL
// Processa cada linha do arquivo
// Cria uma pilha de palavras a partir da linha
// Cria uma árvore AVL a partir da pilha de palavras
// Empilha a árvore AVL na pilha de árvores



    // Este método cria uma pilha de palavras a partir de uma linha de texto
    private static Pilha<String> criarPilhaPalavras(String linha) {
        Pilha<String> pilhaPalavras = new Pilha<>();
    
        for (String palavra : linha.split("\\s+")) {
            pilhaPalavras.empilhar(palavra);
        }
        return pilhaPalavras;
    }

// Cria uma nova pilha de palavras
// Adiciona cada palavra da linha à pilha de palavras
// Retorna a pilha de palavras






    // Este método cria uma árvore AVL a partir de uma pilha de palavras
    private static ArvoreAVL<String> criarArvore(Pilha<String> pilhaPalavras) {
        ArvoreAVL<String> arvore = new ArvoreAVL<>();
        
        while (pilhaPalavras.tamanho() > 0) {
            String palavraDesempilhada = pilhaPalavras.desempilhar();
            arvore.inserir(palavraDesempilhada);
        }
        
        return arvore;
    }

// Cria uma nova árvore AVL
// Insere cada palavra da pilha de palavras na árvore AVL
// Retorna a árvore AVL



    // Este método imprime os hashes das árvores AVL
    private static void imprimirHashes(Pilha<ArvoreAVL<String>> pilhaArvores) {
        
        while (pilhaArvores.tamanho() > 0) {
            String hash = pilhaArvores.desempilhar().hashArvore();
            System.out.println(hash);
        }
    }

// Desempilha cada árvore AVL e imprime seu hash

    public static void main(String[] args) {
        try {
            gerarHashes("Entrada.txt");
        } catch (Exception e) {
            System.err.println("Erro:" + e.getMessage());
        }
    }
}
