import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EntradaArquivo {
    public static String lerConteudo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
        return conteudo.toString();
    }

    public static void main(String[] args) {
        String nomeDoArquivo = "Entrada.txt";
        String conteudoDoArquivo = lerConteudo(nomeDoArquivo);
        System.out.println("Contedo do arquivo:\n" + conteudoDoArquivo);
    }
}
