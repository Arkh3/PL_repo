package asint;

import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;

public class Main {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {

			System.out.println("Usage: <file> <asint_type>");
			return;
		}

		if (args[1].equalsIgnoreCase("desc")) {

			AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(args[0]));
			asint.Sp();
		} else if (args[1].equalsIgnoreCase("asc")) {
			
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			AnalizadorSintacticoTinyCup asint2 = new AnalizadorSintacticoTinyCup(alex);
			// asint.setScanner(alex);
			asint2.parse();
		} else {
			
			System.out.println("asint type must be either 'desc' or 'asc'.");
			return;
		}
		System.out.println("OK");
	}
}