/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTiny {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;

	public AnalizadorSintacticoTiny(Reader input) throws IOException {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input);
		alex.fijaGestionErrores(errores);
		sigToken();
	}

	public void SAux() {
		S();
		empareja(ClaseLexica.EOF);
	}

	public void S() {
		SecDec();
		empareja(ClaseLexica.SEPARADOR);
		SecInstr();
	}

	public void SecDec() {
		Declaracion();
		SecDecAux();
	}

	public void SecDecAux() {
		switch (anticipo.clase()) {
		case PUNTOCOMA:
			empareja(ClaseLexica.PUNTOCOMA);
			Declaracion();
			SecDecAux();
			break;
		case SEPARADOR:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PUNTOCOMA,
					ClaseLexica.SEPARADOR);
		}
	}

	public void Declaracion() {
		Tipo();
		empareja(ClaseLexica.IDENTIFICADOR);
	}

	public void Tipo() {

		switch (anticipo.clase()) {
		case BOOL:
			empareja(ClaseLexica.BOOL);
			break;
		case REAL:
			empareja(ClaseLexica.REAL);
			break;
		case INT:
			empareja(ClaseLexica.INT);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
		}
	}

	public void SecInstr() {
		Instruccion();
		SecInstrAux();
	}

	public void SecInstrAux() {

		switch (anticipo.clase()) {
		case PUNTOCOMA:
			empareja(ClaseLexica.PUNTOCOMA);
			Instruccion();
			SecInstrAux();
			break;
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PUNTOCOMA,
					ClaseLexica.EOF);
		}
	}

	public void Instruccion() {
		empareja(ClaseLexica.IDENTIFICADOR);
		empareja(ClaseLexica.ASIGNACION);
		E0();
	}

	public void E0() {
		E1();
		E0_AUX();
		/*
		 * switch (anticipo.clase()) { case NOT: case NUM_REAL: case RESTA: case
		 * NUM_ENT: case IDENTIFICADOR: case BOOLEAN: case PARABIERTO: E1(); E0_AUX();
		 * break; default: errores.errorSintactico(anticipo.fila(), anticipo.columna(),
		 * anticipo.clase(), ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.RESTA,
		 * ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR, ClaseLexica.BOOLEAN,
		 * ClaseLexica.PARABIERTO); }
		 */
	}

	public void E0_AUX() {
		switch (anticipo.clase()) {
		case SUMA:
			empareja(ClaseLexica.SUMA);
			E0();
			break;
		case RESTA:
			empareja(ClaseLexica.RESTA);
			E1();
			break;
		case PUNTOCOMA:
		case PARCERRADO:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SUMA,
					ClaseLexica.RESTA, ClaseLexica.PUNTOCOMA, ClaseLexica.PARCERRADO, ClaseLexica.EOF);
		}
	}

	public void E1() {
		E2();
		E1_AUX();
		/*
		 * switch (anticipo.clase()) { case NOT: case NUM_REAL: case RESTA: case
		 * NUM_ENT: case IDENTIFICADOR: case BOOLEAN: case PARABIERTO: E2(); E1_AUX();
		 * break; default: errores.errorSintactico(anticipo.fila(), anticipo.columna(),
		 * anticipo.clase(), ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.RESTA,
		 * ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR, ClaseLexica.BOOLEAN,
		 * ClaseLexica.PARABIERTO); }
		 */
	}

	public void E1_AUX() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			Op1();
			E2();
			E1_AUX();

		case EOF:
		case RESTA:
		case PUNTOCOMA:
		case SUMA:
		case PARCERRADO:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR, ClaseLexica.EOF, ClaseLexica.RESTA, ClaseLexica.PUNTOCOMA, ClaseLexica.SUMA,
					ClaseLexica.PARCERRADO);
		}
	}

	public void E2() {
		E3();
		E2_AUX();
		/*
		 * switch (anticipo.clase()) { case NOT: case NUM_REAL : case RESTA : case
		 * NUM_ENT : case IDENTIFICADOR : case BOOLEAN : case PARABIERTO: E3();
		 * E2_AUX(); break; default: errores.errorSintactico(anticipo.fila(),
		 * anticipo.columna(), anticipo.clase(), ClaseLexica.NOT, ClaseLexica.RESTA,
		 * ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR, ClaseLexica.BOOLEAN,
		 * ClaseLexica.PARABIERTO); }
		 */
	}

	public void E2_AUX() {

		switch (anticipo.clase()) {
		case MENORIGUAL:
		case DIFERENTE:
		case MAYOR:
		case MAYORIGUAL:
		case MENOR:
		case IGUAL:
			Op2();
			E3();
			E2_AUX();
			break;
		case OR:
		case EOF:
		case AND:
		case RESTA:
		case PUNTOCOMA:
		case SUMA:
		case PARCERRADO:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MENORIGUAL,
					ClaseLexica.DIFERENTE, ClaseLexica.MAYOR, ClaseLexica.MAYORIGUAL, ClaseLexica.MENOR,
					ClaseLexica.IGUAL, ClaseLexica.OR, ClaseLexica.EOF, ClaseLexica.AND, ClaseLexica.RESTA,
					ClaseLexica.PUNTOCOMA, ClaseLexica.SUMA, ClaseLexica.PARCERRADO);
		}
	}

	public void E3() {
		E4();
		E3_AUX();
		/*switch (anticipo.clase()) {
		case NOT:
		case NUM_REAL:
		case RESTA:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			E4();
			E3_AUX();	
			break;
		default:

			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NOT,
					ClaseLexica.NUM_REAL, ClaseLexica.RESTA, ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR,
					ClaseLexica.BOOLEAN, ClaseLexica.PARABIERTO);
		}*/
	}

	public void E3_AUX() {

		switch (anticipo.clase()) {
		case MULT:
		case DIV:
			Op3();
			E4();
			break;
		case OR:
		case MENOR:
		case IGUAL:
		case EOF:
		case RESTA:
		case MENORIGUAL:
		case DIFERENTE:
		case MAYOR:
		case MAYORIGUAL:
		case AND:
		case PUNTOCOMA:
		case SUMA:
		case PARCERRADO:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MULT,
					ClaseLexica.DIV, ClaseLexica.MULT, ClaseLexica.OR, ClaseLexica.MENOR, ClaseLexica.IGUAL,
					ClaseLexica.EOF, ClaseLexica.RESTA, ClaseLexica.MENORIGUAL, ClaseLexica.DIFERENTE,
					ClaseLexica.MAYOR, ClaseLexica.MAYORIGUAL, ClaseLexica.AND, ClaseLexica.PUNTOCOMA);
		}
	}

	public void E4() {

		switch (anticipo.clase()) {
		case RESTA:
			empareja(ClaseLexica.RESTA);
			E5();
			break;
		case NOT:
			empareja(ClaseLexica.NOT);
			E4();
			break;
		case NUM_REAL:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			E5();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENT,
					ClaseLexica.NUM_REAL, ClaseLexica.IDENTIFICADOR, ClaseLexica.PARABIERTO, ClaseLexica.BOOLEAN,
					ClaseLexica.RESTA, ClaseLexica.NOT);
		}
	}

	public void E5() {

		switch (anticipo.clase()) {
		case NUM_ENT:
			empareja(ClaseLexica.NUM_ENT);
			break;
		case NUM_REAL:
			empareja(ClaseLexica.NUM_REAL);
			break;
		case IDENTIFICADOR:
			empareja(ClaseLexica.IDENTIFICADOR);
			break;
		case PARABIERTO:
			empareja(ClaseLexica.PARABIERTO);
			E0();
			empareja(ClaseLexica.PARCERRADO);
			break;
		case BOOLEAN:
			empareja(ClaseLexica.BOOLEAN);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENT,
					ClaseLexica.NUM_REAL, ClaseLexica.IDENTIFICADOR, ClaseLexica.PARABIERTO, ClaseLexica.BOOLEAN);
		}
	}

	public void Op1() {

		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			break;
		case OR:
			empareja(ClaseLexica.OR);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
		}
	}

	public void Op2() {

		switch (anticipo.clase()) {
		case MENOR:
			empareja(ClaseLexica.MENOR);
			break;
		case MAYOR:
			empareja(ClaseLexica.MAYOR);
			break;
		case MENORIGUAL:
			empareja(ClaseLexica.MENORIGUAL);
			break;
		case MAYORIGUAL:
			empareja(ClaseLexica.MAYORIGUAL);
			break;
		case DIFERENTE:
			empareja(ClaseLexica.DIFERENTE);
			break;
		case IGUAL:
			empareja(ClaseLexica.IGUAL);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MENOR,
					ClaseLexica.MAYOR, ClaseLexica.MENORIGUAL, ClaseLexica.MAYORIGUAL, ClaseLexica.DIFERENTE, ClaseLexica.IGUAL);
		}
	}

	public void Op3() {

		switch (anticipo.clase()) {
		case MULT:
			empareja(ClaseLexica.MULT);
			break;
		case DIV:
			empareja(ClaseLexica.DIV);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MULT,
					ClaseLexica.DIV);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), claseEsperada);
	}

	private void sigToken() {
		try {
			anticipo = alex.sigToken();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}
}
