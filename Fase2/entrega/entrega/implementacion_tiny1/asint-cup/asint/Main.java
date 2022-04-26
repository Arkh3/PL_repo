package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream("inputGrande.txt"));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTinyCup asint = new AnalizadorSintacticoTinyCup(alex);
	 //asint.setScanner(alex);
	 asint.parse();
	 System.out.println("OK");
 }
}