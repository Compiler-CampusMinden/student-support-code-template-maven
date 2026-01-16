import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
  static void main(String... args) throws IOException, URISyntaxException {
    IO.println("Hello World!");

    // Einlesen über Konsole/Prompt
    String input = IO.readln("expr?> ");

    HelloLexer lexer = new HelloLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HelloParser parser = new HelloParser(tokens);

    ParseTree tree = parser.start(); // Start-Regel
    IO.println(tree.toStringTree(parser));

    // Einlesen über den Classpath (für Ressourcen)
    IO.readln("next?> ");
    try (InputStream in = Main.class.getResourceAsStream("/cpp/Makefile")) {
      String text = new String(in.readAllBytes(), StandardCharsets.UTF_8);
      IO.println("\n\n/cpp/Makefile");
      IO.println(text);
    }

    // Einlesen direkt über Dateisystem (nur für nicht-Ressourcen!)
    IO.readln("next?> ");
    URL url = Main.class.getResource("/cpp/Makefile");
    String txt = Files.readString(Path.of(url.toURI()), StandardCharsets.UTF_8);
    IO.println("\n\n/cpp/Makefile");
    IO.println(txt);
  }
}
