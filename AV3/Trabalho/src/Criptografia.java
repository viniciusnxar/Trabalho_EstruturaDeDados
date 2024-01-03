import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
  public static String sha1(String texto) {
    try {
      MessageDigest mDigest = MessageDigest.getInstance("SHA1");
      byte[] resultado = mDigest.digest(texto.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < resultado.length; i++) {
        sb.append(
          Integer.toString((resultado[i] & 0xff) + 0x100, 16).substring(1)
        );
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException err) {
      throw new RuntimeException("Erro");
    }
  }
}
