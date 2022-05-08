package alex;

import java_cup.runtime.Symbol;
import asint.AnalizadorSintacticoTiny.StringLocalizado;
import c_ast_asc.ClaseLexica;

public class UnidadLexica extends Symbol {
   private int fila;
   private int col;
   public UnidadLexica(int fila, int col, int clase, String lexema) {
     super(clase,null);
	 this.fila = fila;
	 value = new StringLocalizado(lexema,fila,col);
   }
   public int clase () {return sym;}
   public StringLocalizado lexema() {return (StringLocalizado)value;}
   public String toString() {
       StringLocalizado lexema = (StringLocalizado)value;
       return "[clase="+clase2string(sym)+",lexema="+lexema+",fila="+lexema.fila()+",col="+lexema.col()+"]";
   }
   public int fila() {return fila;}
   public int columna() {return col;}
   private String clase2string(int clase) {
       switch(clase) {
       case ClaseLexica.MULT: return "MULT";
       case ClaseLexica.CORCHETE_CERRADO: return "CORCHETE_CERRADO";
       case ClaseLexica.DIFERENTE: return "DIFERENTE";
       case ClaseLexica.PORCENTAJE: return "PORCENTAJE";
       case ClaseLexica.INT: return "INT";
       case ClaseLexica.PUNTOCOMA: return "PUNTOCOMA";
       case ClaseLexica.ARRAY: return "ARRAY";
       case ClaseLexica.PAR_CERRADO: return "PAR_CERRADO";
       case ClaseLexica.SEPARADOR: return "SEPARADOR";
       case ClaseLexica.DELETE: return "DELETE";
       case ClaseLexica.WRITE: return "WRITE";
       case ClaseLexica.LLAVE_ABIERTA: return "LLAVE_ABIERTA";
       case ClaseLexica.IDENTIFICADOR: return "IDENTIFICADOR";
       case ClaseLexica.NOT: return "NOT";
       case ClaseLexica.AND: return "AND";
       case ClaseLexica.IGUAL: return "IGUAL";
       case ClaseLexica.RECORD: return "RECORD";
       case ClaseLexica.TYPE: return "TYPE";
       case ClaseLexica.OR: return "OR";
       case ClaseLexica.BOOL: return "BOOL";
       case ClaseLexica.CALL: return "CALL";
       case ClaseLexica.DIV: return "DIV";
       case ClaseLexica.IF: return "IF";
       case ClaseLexica.AMPERSAND: return "AMPERSAND";
       case ClaseLexica.ENDWHILE: return "ENDWHILE";
       case ClaseLexica.OF: return "OF";
       case ClaseLexica.BOOLEAN: return "BOOLEAN";
       case ClaseLexica.CORCHETE_ABIERTO: return "CORCHETE_ABIERTO";
       case ClaseLexica.EOF: return "EOF";
       case ClaseLexica.NEW: return "NEW";
       case ClaseLexica.error: return "error";
       case ClaseLexica.COMA: return "COMA";
       case ClaseLexica.CADENA: return "CADENA";
       case ClaseLexica.MENOS: return "MENOS";
       case ClaseLexica.NULL: return "NULL";
       case ClaseLexica.MENOR: return "MENOR";
       case ClaseLexica.PAR_ABIERTO: return "PAR_ABIERT";
       case ClaseLexica.ASIGNACION: return "ASIGNACION";
       case ClaseLexica.NUM_ENT: return "NUM_ENT";
       case ClaseLexica.REAL: return "REAL";
       case ClaseLexica.MAYOR: return "MAYOR";
       case ClaseLexica.FLECHA: return "FLECHA";
       case ClaseLexica.ENDIF: return "ENDIF";
       case ClaseLexica.NUM_REAL: return "NUM_REAL";
       case ClaseLexica.ELSE: return "ELSE";
       case ClaseLexica.PUNTO: return "PUNTO";
       case ClaseLexica.READ: return "READ";
       case ClaseLexica.NL: return "NL";
       case ClaseLexica.WHILE: return "WHILE";
       case ClaseLexica.MAYOR_IGUAL: return "MAYOR_IGUAL";
       case ClaseLexica.THEN: return "THEN";
       case ClaseLexica.PROC: return "PROC";
       case ClaseLexica.STRING: return "STRING";
       case ClaseLexica.LLAVE_CERRADA: return "LLAVE_CERRADA";
       case ClaseLexica.MENOR_IGUAL: return "MENOR_IGUAL";
       case ClaseLexica.POINTER: return "POINTER";
       case ClaseLexica.VAR: return "VA";
       case ClaseLexica.DO: return "DO";
       case ClaseLexica.MAS: return "MAS";
         default: return "?";               
       }
    }
}
