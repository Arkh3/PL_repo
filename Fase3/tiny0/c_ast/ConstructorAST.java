package c_ast;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import alex.UnidadLexica;
import asint.AnalizadorSintacticoTiny.Prog;
import asint.AnalizadorSintacticoTiny.Decs;
import asint.AnalizadorSintacticoTiny.Instrs;
import asint.AnalizadorSintacticoTiny.Dec;
import asint.AnalizadorSintacticoTiny.Tipo;
import asint.AnalizadorSintacticoTiny.Decs_una;
import asint.AnalizadorSintacticoTiny.Exp;
import asint.AnalizadorSintacticoTiny.Instr;
import asint.AnalizadorSintacticoTiny.Instr_una;
import asint.AnalizadorSintacticoTiny.Decs_muchas;
import asint.AnalizadorSintacticoTiny.Instr_muchas;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
import semOps.SemOps;

public class ConstructorAST {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;

	public ConstructorAST(Reader input) throws IOException {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input);
		alex.fijaGestionErrores(errores);
		sigToken();
		sem = new SemOps();
	}

	public Prog SAux() {
		
		switch (anticipo.clase()) {
		case BOOL:
		case REAL:
		case INT:
			Prog prog = S();
			empareja(ClaseLexica.EOF);
			return prog;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
			return null;
		}
		
	}

	public Prog S() {
		switch (anticipo.clase()) {
		case BOOL:
		case REAL:
		case INT:
			Decs decs = SecDec();
			empareja(ClaseLexica.SEPARADOR);
			Instrs instrs = SecInstr();
			return sem.prog(decs, instrs);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
			return null;
		}
	}

	public Decs SecDec() {
		switch (anticipo.clase()) {
		case BOOL:
		case REAL:
		case INT:
			Decs_una decs_una = sem.decs_una(Declaracion());
			return SecDecAux(decs_una);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
			return null;
		}
	}

	public Decs SecDecAux(Decs decs_una) {
		switch (anticipo.clase()) {
		case PUNTOCOMA:
			empareja(ClaseLexica.PUNTOCOMA);
			Decs_muchas decs_muchas = sem.decs_muchas(decs_una, Declaracion());
			return SecDecAux(decs_muchas);
		case SEPARADOR:
			return decs_una;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PUNTOCOMA,
					ClaseLexica.SEPARADOR);
			return null;
		}
	}

	public Dec Declaracion() {
		switch (anticipo.clase()) {
		case BOOL:
		case REAL:
		case INT:
			Tipo t = Tipo();
			UnidadLexica tknId = anticipo;
			empareja(ClaseLexica.IDENTIFICADOR);
			return sem.dec(t, sem.str(tknId.lexema(), tknId.fila(), tknId.columna()));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
			return null;
		}
	}

	public Tipo Tipo() {

		switch (anticipo.clase()) {
		case BOOL:
			empareja(ClaseLexica.BOOL);
			return sem.bool();
		case REAL:
			empareja(ClaseLexica.REAL);
			return sem.real();
		case INT:
			empareja(ClaseLexica.INT);
			return sem.integer();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.REAL, ClaseLexica.INT);
			return null;
		}
	}

	public Instrs SecInstr() {
		switch (anticipo.clase()) {
		case IDENTIFICADOR:
			Instr_una instr_una = sem.instr_una(Instruccion());
			return SecInstrAux(instr_una);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.IDENTIFICADOR);
			return null;
		}
	}

	public Instrs SecInstrAux(Instrs instr_una) {
		switch (anticipo.clase()) {
		case PUNTOCOMA:
			empareja(ClaseLexica.PUNTOCOMA);
			Instr_muchas instr_muchas = sem.instr_muchas(instr_una, Instruccion());
			return SecInstrAux(instr_muchas);
		case EOF:
			return instr_una;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PUNTOCOMA,
					ClaseLexica.EOF);
			return null;
		}
	}

	public Instr Instruccion() {
		switch (anticipo.clase()) {
		case IDENTIFICADOR:
			UnidadLexica tknId = anticipo;
			empareja(ClaseLexica.IDENTIFICADOR);
			empareja(ClaseLexica.ASIGNACION);
			Exp e = E0();
			return sem.instr(sem.str(tknId.lexema(), tknId.fila(), tknId.columna()), e);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.IDENTIFICADOR);
			return null;
		}
	}

	public Exp E0() {
		switch (anticipo.clase()) {
		case NOT:
		case NUM_REAL:
		case RESTA:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			Exp e = E1();
			return E0_AUX(e);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NOT,
					ClaseLexica.NUM_REAL, ClaseLexica.RESTA, ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR,
					ClaseLexica.BOOLEAN, ClaseLexica.PARABIERTO);
			return null;
		}
	}

	public Exp E0_AUX(Exp e) {
		switch (anticipo.clase()) {
		case SUMA:
			empareja(ClaseLexica.SUMA);
			Exp e2 = E0();
			return sem.exp("suma", e, e2);
		case RESTA:
			empareja(ClaseLexica.RESTA);
			Exp e3 = E1();
			return sem.exp("resta", e, e3);
		case PUNTOCOMA:
		case PARCERRADO:
		case EOF:
			return e;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SUMA,
					ClaseLexica.RESTA);
			return null;
		}
	}

	public Exp E1() {
		switch (anticipo.clase()) {
		case NOT:
		case NUM_REAL:
		case RESTA:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			Exp e = E2();
			return E1_AUX(e);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NOT,
					ClaseLexica.NUM_REAL, ClaseLexica.RESTA, ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR,
					ClaseLexica.BOOLEAN, ClaseLexica.PARABIERTO);
			return null;
		}
	}

	public Exp E1_AUX(Exp e) {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			String op = Op1();
			Exp e2 = E2();
			return E1_AUX(sem.exp(op, e, e2));
		case EOF:
		case RESTA:
		case PUNTOCOMA:
		case SUMA:
		case PARCERRADO:
			return e;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR, ClaseLexica.RESTA, ClaseLexica.SUMA);
			return null;
		}
	}

	public Exp E2() {
		switch (anticipo.clase()) {
		case NOT:
		case NUM_REAL:
		case RESTA:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			Exp e = E3();
			return E2_AUX(e);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NOT,
					ClaseLexica.RESTA, ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR, ClaseLexica.BOOLEAN,
					ClaseLexica.PARABIERTO);
			return null;
		}

	}

	public Exp E2_AUX(Exp e) {

		switch (anticipo.clase()) {
		case MENORIGUAL:
		case DIFERENTE:
		case MAYOR:
		case MAYORIGUAL:
		case MENOR:
		case IGUAL:
			String op = Op2();
			Exp e2 = E3();
			return E2_AUX(sem.exp(op, e, e2));
		case OR:
		case EOF:
		case AND:
		case RESTA:
		case PUNTOCOMA:
		case SUMA:
		case PARCERRADO:
			return e;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MENORIGUAL,
					ClaseLexica.DIFERENTE, ClaseLexica.MAYOR, ClaseLexica.MAYORIGUAL, ClaseLexica.MENOR,
					ClaseLexica.IGUAL, ClaseLexica.OR, ClaseLexica.AND, ClaseLexica.RESTA, ClaseLexica.SUMA);
			return null;
		}
	}

	public Exp E3() {
		switch (anticipo.clase()) {
		case NOT:
		case NUM_REAL:
		case RESTA:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			Exp e = E4();
			return E3_AUX(e);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NOT,
					ClaseLexica.NUM_REAL, ClaseLexica.RESTA, ClaseLexica.NUM_ENT, ClaseLexica.IDENTIFICADOR,
					ClaseLexica.BOOLEAN, ClaseLexica.PARABIERTO);
			return null;
		}

	}

	public Exp E3_AUX(Exp e) {

		switch (anticipo.clase()) {
		case MULT:
		case DIV:
			String op = Op3();
			Exp e2 = E4();
			return sem.exp(op, e, e2);
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
			return e;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MULT,
					ClaseLexica.DIV, ClaseLexica.MULT, ClaseLexica.OR, ClaseLexica.MENOR, ClaseLexica.IGUAL,
					ClaseLexica.RESTA, ClaseLexica.MENORIGUAL, ClaseLexica.DIFERENTE, ClaseLexica.MAYOR,
					ClaseLexica.MAYORIGUAL, ClaseLexica.AND);
			return null;
		}
	}

	public Exp E4() {

		switch (anticipo.clase()) {
		case RESTA:
			empareja(ClaseLexica.RESTA);
			Exp e = E5();
			return sem.negativo(e);
		case NOT:
			empareja(ClaseLexica.NOT);
			Exp e2 = E4();
			return sem.not(e2);
		case NUM_REAL:
		case NUM_ENT:
		case IDENTIFICADOR:
		case BOOLEAN:
		case PARABIERTO:
			return E5();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENT,
					ClaseLexica.NUM_REAL, ClaseLexica.IDENTIFICADOR, ClaseLexica.PARABIERTO, ClaseLexica.BOOLEAN,
					ClaseLexica.RESTA, ClaseLexica.NOT);
			return null;
		}
	}

	
	

	public Exp E5() {

		switch (anticipo.clase()) {
		case NUM_ENT:
			UnidadLexica tknId = anticipo;
			empareja(ClaseLexica.NUM_ENT);
			return sem.num_ent(sem.str(tknId.lexema(), tknId.fila(), tknId.columna()));
		case NUM_REAL:
			UnidadLexica tknId2 = anticipo;
			empareja(ClaseLexica.NUM_REAL);
			return sem.num_real(sem.str(tknId2.lexema(), tknId2.fila(), tknId2.columna()));
		case IDENTIFICADOR:
			UnidadLexica tknId3 = anticipo;
			empareja(ClaseLexica.IDENTIFICADOR);
			return sem.id(sem.str(tknId3.lexema(), tknId3.fila(), tknId3.columna()));
		case PARABIERTO:
			empareja(ClaseLexica.PARABIERTO);
			Exp e = E0();
			empareja(ClaseLexica.PARCERRADO);
			return e;
		case BOOLEAN:
			UnidadLexica tknId4 = anticipo;
			empareja(ClaseLexica.BOOLEAN);
			return sem.booleano(sem.str(tknId4.lexema(), tknId4.fila(), tknId4.columna()));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENT,
					ClaseLexica.NUM_REAL, ClaseLexica.IDENTIFICADOR, ClaseLexica.PARABIERTO, ClaseLexica.BOOLEAN);
			return null;
		}
	}

	public String Op1() {

		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			return "and";
		case OR:
			empareja(ClaseLexica.OR);
			return "or";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
			return null;
		}
	}

	public String Op2() {

		switch (anticipo.clase()) {
		case MENOR:
			empareja(ClaseLexica.MENOR);
			return "menor";
		case MAYOR:
			empareja(ClaseLexica.MAYOR);
			return "mayor";
		case MENORIGUAL:
			empareja(ClaseLexica.MENORIGUAL);
			return "menor_igual";
		case MAYORIGUAL:
			empareja(ClaseLexica.MAYORIGUAL);
			return "mayor_igual";
		case DIFERENTE:
			empareja(ClaseLexica.DIFERENTE);
			return "diferente";
		case IGUAL:
			empareja(ClaseLexica.IGUAL);
			return "igual";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MENOR,
					ClaseLexica.MAYOR, ClaseLexica.MENORIGUAL, ClaseLexica.MAYORIGUAL, ClaseLexica.DIFERENTE,
					ClaseLexica.IGUAL);
			return null;
		}
	}

	public String Op3() {

		switch (anticipo.clase()) {
		case MULT:
			empareja(ClaseLexica.MULT);
			return "mult";
		case DIV:
			empareja(ClaseLexica.DIV);
			return "div";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MULT,
					ClaseLexica.DIV);
			return null;
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
