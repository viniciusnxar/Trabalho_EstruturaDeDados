public class ArvoreAVL<T extends Comparable<T>> {

  class No {
    T valor;  
    int comprimentoDireito;  
    int comprimentoEsquerdo;  
    No esquerda;  
    No direita;  

    // Construtor do nó
    public No(T valor) {
      this.valor = valor;
    }
      
    // Compara este nó com outro nó
    public int comparar(No outroNo) {
      if (valor instanceof String && outroNo.valor instanceof String) {
        return ((String) valor).compareToIgnoreCase((String) outroNo.valor);
      } else {
        return valor.compareTo(outroNo.valor);
      }
    }

    // Atualiza os comprimentos dos ramos esquerdo e direito
    public void atualizarComprimentos() {
      comprimentoEsquerdo = (esquerda == null) ? 0 : esquerda.obterComprimentoMaior() + 1;
      comprimentoDireito = (direita == null) ? 0 : direita.obterComprimentoMaior() + 1;
    }

    // Retorna o comprimento do maior ramo
    public int obterComprimentoMaior() {
      return (comprimentoDireito > comprimentoEsquerdo) ? comprimentoDireito : comprimentoEsquerdo;
    }

    // Retorna a diferença entre os comprimentos dos ramos esquerdo e direito
    public int obterDiferencaComprimentos() {
      return comprimentoEsquerdo - comprimentoDireito;
    }

  

    // Retorna o hash do valor armazenado neste nó
    public String obterHash() {
      return Criptografia.sha1((String) valor);
    }
  }

  // Retorna o hash da árvore
  public String hashArvore() {
    return hashArvore(raiz);
  }

  No raiz;  // Raiz da árvore

  // Calcula o hash de um nó e seus descendentes
  private String hashArvore(No no) {
    if (no == null) {
        return "";
    }
    String valorNo = Criptografia.sha1((String) no.valor);
    String hashEsquerda = hashArvore(no.esquerda);
    String hashDireita = hashArvore(no.direita);
    
    if (no.esquerda == null && no.direita == null) {
        return valorNo;
    } else {
        return Criptografia.sha1(hashEsquerda + hashDireita + valorNo);
    }
}

  // Insere um valor na árvore
  public void inserir(T valor) {
    No novoNo = new No(valor);
    if (raiz == null) {
      raiz = novoNo;
    } else {
      inserir(raiz, novoNo);
    }
  }

// Insere um nó em um nó ou seus descendentes
  private void inserir(No no, No novoNo) {
    if (no.valor != novoNo.valor) {
      if (novoNo.comparar(no) < 0) {
        if (no.esquerda == null) {
          no.esquerda = novoNo;
        } else {
          inserir(no.esquerda, novoNo);
        }
      } else {
        if (no.direita == null) {
          no.direita = novoNo;
        } else {
          inserir(no.direita, novoNo);
        }
      }

      no.atualizarComprimentos();
      balancear(no);
    }
  }

// Remove um valor da árvore AVL
  public void remover(T valor) {
    No noComparacao = new No(valor);
    remover(raiz, noComparacao);
  }

// Procura e remover um nó da árvore
  private No remover(No no, No noComparacao) {
    if (no == null) return null;

    if (noComparacao.comparar(no) < 0) {
      no.esquerda = remover(no.esquerda, noComparacao);
    } else if (noComparacao.comparar(no) > 0) {
      no.direita = remover(no.direita, noComparacao);
    } else {
      if (no.esquerda == null && no.direita == null) {
        return null;
      } else if (no.esquerda != null && no.direita == null) {
        return no.esquerda;
      } else if (no.esquerda == null && no.direita != null) {
        return no.direita;
      } else {
        No sucessor = encontrarMinimoFilho(no.direita);
        no.valor = sucessor.valor;
        no.direita = remover(no.direita, sucessor);
      }
    }

    if (no != null) {
      no.atualizarComprimentos();
      balancear(no);
    }

    return no;
  }

// Balanceia a árvore após a inserção ou remoção de um nó
  private void balancear(No no) {
    if (no.obterDiferencaComprimentos() < -1) {
      if (no.direita != null && no.direita.obterDiferencaComprimentos() > 0) {
        rotacionarDireita(no.direita);
      }
      rotacionarEsquerda(no);
    }
    if (no.obterDiferencaComprimentos() > 1) {
      if (no.esquerda != null && no.esquerda.obterDiferencaComprimentos() < 0) {
        rotacionarEsquerda(no.esquerda);
      }
      rotacionarDireita(no);
    }
  }

// Verifica se a árvore contém um valor
  public boolean contem(T valor) {
    No noComparacao = new No(valor);
    return contem(raiz, noComparacao);
  }

// Verifica se um nó ou seus descendentes contêm um valor
  private boolean contem(No no, No noComparacao) {
    if (no != null) {
      if (no.comparar(noComparacao) == 0) {
        return true;
      }
      if (noComparacao.comparar(no) < 0) {
        return contem(no.esquerda, noComparacao);
      } else {
        return contem(no.direita, noComparacao);
      }
    }
    return false;
  }

// Exibe a árvore em ordem
  public void exibirOrdem() {
    exibirOrdem(raiz);
    System.out.println();
  }

// Exibe um nó e seus descendentes em ordem
  private void exibirOrdem(No no) {
    if (no != null) {
      exibirOrdem(no.esquerda);
      System.out.print(no.valor + " ");
      exibirOrdem(no.direita);
    }
  }

// Encontra o nó com o menor valor a partir de um nó específico
  private No encontrarMinimoFilho(No no) {
    while (no.esquerda != null) {
      no = no.esquerda;
    }
    return no;
  }

// Rotacionar um nó para a direita
  private void rotacionarDireita(No no) {
    No noAuxiliar = no.esquerda;

    no.esquerda = noAuxiliar.esquerda;
    noAuxiliar.esquerda = noAuxiliar.direita;
    noAuxiliar.direita = no.direita;
    no.direita = noAuxiliar;

    trocarValores(no, noAuxiliar);
    noAuxiliar.atualizarComprimentos();
    no.atualizarComprimentos();
  }
  
// Rotacionar um nó para a esquerda
  private void rotacionarEsquerda(No no) {
    No noAuxiliar = no.direita;

    no.direita = noAuxiliar.direita;
    noAuxiliar.direita = noAuxiliar.esquerda;
    noAuxiliar.esquerda = no.esquerda;
    no.esquerda = noAuxiliar;

    trocarValores(no, noAuxiliar);
    noAuxiliar.atualizarComprimentos();
    no.atualizarComprimentos();
  }

// Trocar os valores entre dois nós
  private void trocarValores(No noA, No noB) {
    T auxiliar = noA.valor;
    noA.valor = noB.valor;
    noB.valor = auxiliar;
  }
}
