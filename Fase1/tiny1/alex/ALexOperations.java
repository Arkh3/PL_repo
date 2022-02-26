package alex;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadNumEntero() {
		return new UnidadLexicaMultivaluada(alex.flex(), alex.columna(), ClaseLexica.NUMENTERO, alex.lexema());
	}

	public UnidadLexica unidadId() {
		return new UnidadLexicaMultivaluada(alex.flex(), alex.columna(), ClaseLexica.ID, alex.lexema());
	}

	public UnidadLexica unidadNumReal() {
		return new UnidadLexicaMultivaluada(alex.flex(), alex.columna(), ClaseLexica.NUMREAL, alex.lexema());
	}

	public UnidadLexica unidadBoolean() {
		return new UnidadLexicaMultivaluada(alex.flex(), alex.columna(), ClaseLexica.BOOLEAN, alex.lexema());
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexicaMultivaluada(alex.flex(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}

	public UnidadLexica unidadSeparador() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.SEPARADOR);
	}

	public UnidadLexica unidadInt() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.INT);
	}

	public UnidadLexica unidadReal() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.REAL);
	}

	public UnidadLexica unidadBool() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.BOOL);
	}

	public UnidadLexica unidadAsignacion() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.ASIG);
	}

	public UnidadLexica unidadPuntoComa() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PUNTOCOMA);
	}

	public UnidadLexica unidadParAbierto() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PARABIERTA);
	}

	public UnidadLexica unidadParCerrado() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PARCERRADA);
	}

	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.SUMA);
	}

	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.RESTA);
	}

	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.DIV);
	}

	public UnidadLexica unidadMult() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.MULT);
	}

	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.AND);
	}

	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.OR);
	}

	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.NOT);
	}

	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.MAYOR);
	}

	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.MENOR);
	}

	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.MAYORIGUAL);
	}

	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.MENORIGUAL);
	}

	public UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.IGUAL);
	}

	public UnidadLexica unidadDiferente() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.DIFERENTE);
	}

	public UnidadLexica unidadPorcentaje() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PORCENTAJE);
	}

	public UnidadLexica unidadCorcheteAbierto() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.CORCHETEABIERTO);
	}

	public UnidadLexica unidadCorcheteCerrado() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.CORCHETEABIERTO);
	}

	public UnidadLexica unidadLlaveAbierta() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.LLAVEABIERTO);
	}

	public UnidadLexica unidadLlaveCerrada() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.LLAVECERRADA);
	}

	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PUNTO);
	}

	public UnidadLexica unidadFlecha() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.FLECHA);
	}

	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.COMA);
	}

	public UnidadLexica unidadAmpersand() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.AMPERSAND);
	}

	public UnidadLexica unidadString() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.STRING);
	}

	public UnidadLexica unidadNull() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.NULL);
	}

	public UnidadLexica unidadProc() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.PROC);
	}

	public UnidadLexica unidadIf() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.IF);
	}

	public UnidadLexica unidadThen() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.THEN);
	}

	public UnidadLexica unidadElse() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.ELSE);
	}

	public UnidadLexica unidadEndif() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.ENDIF);
	}

	public UnidadLexica unidadWhile() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.WHILE);
	}

	public UnidadLexica unidadDo() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.DO);
	}

	public UnidadLexica unidadEndwhile() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.ENDWHILE);
	}

	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.CALL);
	}

	public UnidadLexica unidadRecord() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.RECORD);
	}

	public UnidadLexica unidadArray() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.ARRAY);
	}

	public UnidadLexica unidadOf() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.OF);
	}

	public UnidadLexica unidadPointer() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.POINTER);
	}

	public UnidadLexica unidadNew() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.NEW);
	}

	public UnidadLexica unidadDelete() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.DELETE);
	}

	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.READ);
	}

	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.WRITE);
	}

	public UnidadLexica unidadNl() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.NL);
	}

	public UnidadLexica unidadVar() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.VAR);
	}

	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.flex(), alex.columna(), ClaseLexica.TYPE);
	}

	public void error() {
		System.err.println("***" + alex.fila() + " Caracter inexperado: " + alex.lexema());
	}
}
