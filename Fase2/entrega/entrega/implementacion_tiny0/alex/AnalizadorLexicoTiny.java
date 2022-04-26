package alex;

import java.io.Reader;
import errors.GestionErroresTiny;

import java.io.IOException;

public class AnalizadorLexicoTiny {
   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   private GestionErroresTiny errores;

   private static enum Estado {
    INI, REC_MAYOR, REC_MAYOIGUAL, REC_MENOR, REC_MENORIGUAL, REC_ASIGNACION, REC_IGUAL,
    REC_ID, REC_MULT, REC_DIV, REC_SEPARADOR,REC_I_SEPARADOR, REC_PUNTOCOMA,
    REC_PARABIERTO, REC_PARCERRADO,
    REC_SUMA, REC_INT, REC_RESTA, REC_REAL, REC_EOF,
    REC_I_DIFERENTE, REC_DIFERENTE,
    REC_0, REC_I_REAL1, REC_I_REAL2, REC_I_REAL3, REC_REAL2,REC_REAL3, REC_I_REAL4,REC_REAL4
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   } 
   
   public void fijaGestionErrores(GestionErroresTiny err) {
	   this.errores = err;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INI;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) {
           case INI:
        	  if (hayExclamacion()) transita(Estado.REC_I_DIFERENTE);
        	  else if (hayMayor()) transita(Estado.REC_MAYOR);
        	  else if (hayMenor()) transita(Estado.REC_MENOR);
        	  else if (hayIgual()) transita(Estado.REC_ASIGNACION);
        	  else if (hayLetra())  transita(Estado.REC_ID);
        	  else if (hayMul()) transita(Estado.REC_MULT);
        	  else if (hayDiv()) transita(Estado.REC_DIV);
        	  else if (hayAmpersand()) transita(Estado.REC_I_SEPARADOR);
        	  else if (hayEOF()) transita(Estado.REC_EOF);
        	  else if (hayPuntoComa()) transita(Estado.REC_PUNTOCOMA);
        	  else if (hayPAp()) transita(Estado.REC_PARABIERTO);
        	  else if (hayPCierre()) transita(Estado.REC_PARCERRADO);
        	  else if (hayCero()) transita(Estado.REC_0);
        	  else if (hayDigitoPos()) transita(Estado.REC_INT);
        	  else if (hayResta()) transita(Estado.REC_RESTA);
        	  else if (haySuma()) transita(Estado.REC_SUMA);
              else if (haySep()) transitaIgnorando(Estado.INI);
              else error();
              break;
              
           case REC_I_DIFERENTE:
        	   if(hayIgual()) transita(Estado.REC_DIFERENTE);
        	   else error();
        	   break;
           case REC_DIFERENTE:
        	   return unidadDiferent();
		case REC_MAYOR:
        	   if(hayIgual()) transita(Estado.REC_MAYOIGUAL);
        	   else return unidadMayor();
        	   break;
           case REC_MAYOIGUAL:
        	   return unidadMayorIgual();
		case REC_MENOR:
        	   if(hayIgual()) transita(Estado.REC_MENORIGUAL);
        	   else return unidadMenor();
        	   break;
           case REC_MENORIGUAL:
        	   return unidadMenorIgual();   
           case REC_ASIGNACION:
        	   if(hayIgual()) transita(Estado.REC_IGUAL);
        	   else return unidadAsignacion();
        	   break;
           case REC_IGUAL:
        	   return unidadIgual();
           case REC_ID:
        	   if(hayLetra() || hayDigito()|| hayUnderscore()) transita(Estado.REC_ID);
        	   else return unidadId();
        	   break;
           case REC_MULT: return unidadPor();
           case REC_DIV: return unidadDiv();
           case REC_I_SEPARADOR: 
        	   if(hayAmpersand()) transita(Estado.REC_SEPARADOR);
        	   else error();
        	   break;
           case REC_SEPARADOR: 
        	   return unidadSeparador();
           case REC_EOF: return unidadEof();
           case REC_PUNTOCOMA: return unidadPuntoComa();
           case REC_PARABIERTO: return unidadPAp();
           case REC_PARCERRADO: return unidadPCierre();
           case REC_0:
        	   if (hayPunto()) transita(Estado.REC_I_REAL1);
        	   else if(hayE()) transita(Estado.REC_I_REAL2);
        	   else return unidadInt();
        	   break;
           case REC_INT:
        	   if(hayDigito()) transita(Estado.REC_INT);
        	   else if (hayPunto()) transita(Estado.REC_I_REAL1);
        	   else if (hayE()) transita(Estado.REC_I_REAL2);
        	   else return unidadInt();
        	   break;
           case REC_SUMA:
        	   if (hayDigitoPos()) transita(Estado.REC_INT);
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadMas();
               break;
           case REC_RESTA: 
        	   if (hayDigitoPos()) transita(Estado.REC_INT);
        	   else if(hayCero()) transita(Estado.REC_0);
        	   else return unidadMenos();
        	   break;
           case REC_I_REAL1:
        	   if(hayCero()) transita(Estado.REC_REAL4);
        	   else if (hayDigitoPos()) transita(Estado.REC_REAL);
        	   else error();
               break;
           case REC_REAL:
        	   if(hayE()) transita(Estado.REC_I_REAL2);
        	   else if(hayCero()) transita(Estado.REC_I_REAL4);
        	   else if (hayDigitoPos()) transita(Estado.REC_REAL);
        	   else return unidadReal();
               break;
           case REC_I_REAL4:
        	   if(hayCero()) transita(Estado.REC_I_REAL4);
        	   else if (hayDigitoPos()) transita(Estado.REC_REAL);
        	   else error();
        	   break;
           case REC_REAL4:
        	   if(hayCero()) transita(Estado.REC_I_REAL4);
				else if (hayDigitoPos())
					transita(Estado.REC_REAL);
				else if (hayE())
					transita(Estado.REC_I_REAL2);
				else
					return unidadReal();
				break;
			case REC_I_REAL2:
				if (hayCero())
					transita(Estado.REC_REAL2);
				else if (hayDigitoPos())
					transita(Estado.REC_REAL3);
				else if (haySuma() || hayResta())
					transita(Estado.REC_I_REAL3);
				else
					error();
				break;
			case REC_I_REAL3:
				if (hayCero())
					transita(Estado.REC_REAL2);
				else if (hayDigitoPos())
					transita(Estado.REC_REAL3);
				else
					error();
				break;
			case REC_REAL2:
				return unidadReal();
			case REC_REAL3:
				if (hayDigito())
					transita(Estado.REC_REAL3);
				else
					return unidadReal();
				break;
			}
		}
	}

	private void transita(Estado sig) throws IOException {
		lex.append((char) sigCar);
		sigCar();
		estado = sig;
	}

	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}

	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0))
			saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual = 0;
		} else {
			columnaActual++;
		}
	}

	private void saltaFinDeLinea() throws IOException {
		for (int i = 1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i))
				error();
		}
		sigCar = '\n';
	}

	private boolean hayExclamacion() {
		return sigCar == '!';
	}

	private boolean hayMayor() {
		return sigCar == '>';
	}

	private boolean hayMenor() {
		return sigCar == '<';
	}

	private boolean hayIgual() {
		return sigCar == '=';
	}

	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'Z';
	}

	private boolean hayUnderscore() {
		return sigCar == '_';
	}

	private boolean hayMul() {
		return sigCar == '*';
	}

	private boolean hayDiv() {
		return sigCar == '/';
	}

	private boolean hayAmpersand() {
		return sigCar == '&';
	}

	private boolean hayPuntoComa() {
		return sigCar == ';';
	}

	private boolean hayPAp() {
		return sigCar == '(';
	}

	private boolean hayPCierre() {
		return sigCar == ')';
	}

	private boolean hayCero() {
		return sigCar == '0';
	}

	private boolean haySuma() {
		return sigCar == '+';
	}

	private boolean hayResta() {
		return sigCar == '-';
	}

	private boolean hayE() {
		return sigCar == 'e' || sigCar == 'E';
	}

	private boolean hayDigitoPos() {
		return sigCar >= '1' && sigCar <= '9';
	}

	private boolean hayDigito() {
		return hayDigitoPos() || hayCero();
	}

	private boolean hayPunto() {
		return sigCar == '.';
	}

	private boolean haySep() {
		return sigCar == ' ' || sigCar == '\t' || sigCar == '\n';
	}

	private boolean hayEOF() {
		return sigCar == -1;
	}

	private UnidadLexica unidadDiferent() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIFERENTE);
	}

	private UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}

	private UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORIGUAL);
	}

	private UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}

	private UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORIGUAL);
	}

	private UnidadLexica unidadAsignacion() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ASIGNACION);
	}

	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL);
	}

	private UnidadLexica unidadId() {
		switch (lex.toString()) {
		case "real":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.REAL);
		case "int":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INT);
		case "bool":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);
		case "true":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOLEAN);
		case "false":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOLEAN);
		case "and":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
		case "or":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
		case "not":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
		default:
			return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDENTIFICADOR, lex.toString());
		}
	}

	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MULT);
	}

	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
	}

	private UnidadLexica unidadSeparador() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SEPARADOR);
	}

	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
	}

	private UnidadLexica unidadPuntoComa() {

		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOCOMA);
	}

	private UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PARABIERTO);
	}

	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PARCERRADO);
	}

	private UnidadLexica unidadInt() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_ENT, lex.toString());
	}

	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SUMA);
	}

	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.RESTA);
	}

	private UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_REAL, lex.toString());
	}

	private void error() {
		errores.errorLexico(filaActual, columnaActual, lex.toString());
	}
}