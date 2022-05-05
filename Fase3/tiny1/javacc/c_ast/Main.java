package c_ast;

import asint.AnalizadorSintacticoTiny.Prog;
import asint.ProcesamientoPorDefecto;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import procesamientos.Impresion;

public class Main{
   public static void main(String[] args) throws Exception {
	  Reader input = new InputStreamReader(new FileInputStream(args[0]));
	  ConstrAST constructorAst = new ConstrAST(input);
	  Prog prog = constructorAst.Sp();
	  prog.procesa(new Impresion());
   }
}