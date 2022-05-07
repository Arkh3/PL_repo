package main;

import asint.AnalizadorSintacticoTiny.Prog;
import c_ast_asc.ConstructorAST;
import c_ast_desc.ConstrAST;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import procesamientos.Impresion;

public class Main {
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {

			System.out.println("Usage: <file> <asint_type>");
			return;
		}

		if (args[1].equalsIgnoreCase("desc")) {

			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			ConstrAST constructorAst = new ConstrAST(input);
			Prog prog = constructorAst.Sp();
			prog.procesa(new Impresion());
		} else if (args[1].equalsIgnoreCase("asc")) {

			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			ConstructorAST constructorast = new ConstructorAST(alex);
			Prog prog = (Prog) constructorast.parse().value;
			prog.procesa(new Impresion());
		} else {

			System.out.println("asint type must be either 'desc' or 'asc'.");
			return;
		}
	}
}