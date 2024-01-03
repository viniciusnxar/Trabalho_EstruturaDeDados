public class Pilha<T> {

  public class No {

    T valor;
    No proximo;

    public No(T valor) {
      this.valor = valor;
    }
  }

  private No topo;
  private int altura;

  public Pilha() {
    topo = null;
    altura = 0;
  }

  public T desempilhar() {
    if (topo == null) {
      throw new RuntimeException("Pilha vazia");
    }
    No noDesempilhado = topo;
    topo = topo.proximo;
    noDesempilhado.proximo = null;
    altura--;
    return noDesempilhado.valor;
  }

  public void empilhar(T valor) {
    No novoNo = new No(valor);
    if (topo == null) {
      topo = novoNo;
    } else {
      novoNo.proximo = topo;
      topo = novoNo;
    }
    altura++;
  }

  public T topo() {
    return topo.valor;
  }

  public int tamanho() {
    return altura;
  }
  }

