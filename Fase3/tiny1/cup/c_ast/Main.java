package c_ast;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny.Prog;
import procesamientos.Impresion;

public class Main {
	public static void main(String[] args) throws Exception {

		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		ConstructorAST constructorast = new ConstructorAST(alex);
		Prog prog = (Prog) constructorast.parse().value;
        prog.procesa(new Impresion());   
	}
}
