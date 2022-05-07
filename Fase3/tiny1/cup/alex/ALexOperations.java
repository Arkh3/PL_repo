package alex;

import c_ast.ClaseLexica;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadNumEntero() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_ENT, alex.lexema());
	}

	public UnidadLexica unidadId() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}

	public UnidadLexica unidadNumReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_REAL, alex.lexema());
	}

	public UnidadLexica unidadBoolean() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOLEAN, alex.lexema());
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}

	public UnidadLexica unidadSeparador() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SEPARADOR, "&&");
    }

    public UnidadLexica unidadInt() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "int");
    }

    public UnidadLexica unidadReal() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, "real");
    }

    public UnidadLexica unidadBool() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "bool");
    }

    public UnidadLexica unidadAsignacion() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "=");
    }

	public UnidadLexica unidadPuntoComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA, ";");
	}

	public UnidadLexica unidadParAbierto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAR_ABIERTO, "(");
	}

	public UnidadLexica unidadParCerrado() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAR_CERRADO, ")");
	}

	public UnidadLexica unidadSuma() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
	}

	public UnidadLexica unidadResta() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
	}

	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/");
	}

	public UnidadLexica unidadMult() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MULT, "*");
	}

	public UnidadLexica unidadAnd() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "and");
	}

	public UnidadLexica unidadOr() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "or");
	}

	public UnidadLexica unidadNot() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "not");
	}

	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
	}

	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
	}

	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL, ">=");
	}

	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL, "<=");
	}

	public UnidadLexica unidadIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "==");
	}

	public UnidadLexica unidadDiferente() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIFERENTE, "!=");
	}

	public UnidadLexica unidadPorcentaje() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PORCENTAJE, "%");
	}

	public UnidadLexica unidadCorcheteAbierto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_ABIERTO, "[");
	}

	public UnidadLexica unidadCorcheteCerrado() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CERRADO, "]");
	}

	public UnidadLexica unidadLlaveAbierta() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_ABIERTA, "{");
	}

	public UnidadLexica unidadLlaveCerrada() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CERRADA, "}");
	}

	public UnidadLexica unidadPunto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}

	public UnidadLexica unidadFlecha() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLECHA, "->");
	}

	public UnidadLexica unidadComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}

	public UnidadLexica unidadAmpersand() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND, "&");
	}

	public UnidadLexica unidadString() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "string");
	}

	public UnidadLexica unidadNull() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "null");
	}

	public UnidadLexica unidadProc() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC, "proc");
	}

	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "if");
	}

	public UnidadLexica unidadThen() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.THEN, "then");
	}

	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "else");
	}

	public UnidadLexica unidadEndif() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDIF, "endif");
	}

	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "while");
	}

	public UnidadLexica unidadDo() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DO, "do");
	}

	public UnidadLexica unidadEndwhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDWHILE, "endwhile");
	}

	public UnidadLexica unidadCall() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL, "call");
	}

	public UnidadLexica unidadRecord() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RECORD, "record");
	}

	public UnidadLexica unidadArray() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARRAY, "array");
	}

	public UnidadLexica unidadOf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OF, "of");
	}

	public UnidadLexica unidadPointer() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POINTER, "pointer");
	}

	public UnidadLexica unidadNew() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "new");
	}

	public UnidadLexica unidadDelete() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE, "delete");
	}

	public UnidadLexica unidadRead() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ, "read");
	}

	public UnidadLexica unidadWrite() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "write");
	}

	public UnidadLexica unidadNl() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL, "nl");
	}

	public UnidadLexica unidadVar() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VAR, "var");
	}

	public UnidadLexica unidadType() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE, "type");
	}
	
	public UnidadLexica unidadEof() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EOF, "<EOF>"); 
	  }
}
