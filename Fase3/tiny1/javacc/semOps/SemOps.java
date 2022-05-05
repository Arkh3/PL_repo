package semOps;

import asint.AnalizadorSintacticoTiny;

public class SemOps extends AnalizadorSintacticoTiny {
	public Exp exp(String op, Exp arg0, Exp arg1) {
		switch (op) {
		case "and":
			return and(arg0, arg1);
		case "or":
			return or(arg0, arg1);
		case "menor":
			return menor(arg0, arg1);
		case "mayor":
			return mayor(arg0, arg1);
		case "menor_igual":
			return menor_igual(arg0, arg1);
		case "mayor_igual":
			return mayor_igual(arg0, arg1);
		case "diferente":
			return diferente(arg0, arg1);
		case "igual":
			return igual(arg0, arg1);
		case "mult":
			return mult(arg0, arg1);
		case "div":
			return div(arg0, arg1);
		case "porcentaje":
			return porcentaje(arg0, arg1);
		}
		throw new UnsupportedOperationException("exp " + op);
	}
}
