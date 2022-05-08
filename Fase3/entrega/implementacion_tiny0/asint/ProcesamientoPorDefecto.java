package asint;

import asint.AnalizadorSintacticoTiny.Prog;
import asint.AnalizadorSintacticoTiny.Decs_muchas;
import asint.AnalizadorSintacticoTiny.Decs_una;
import asint.AnalizadorSintacticoTiny.Dec;
import asint.AnalizadorSintacticoTiny.Int;
import asint.AnalizadorSintacticoTiny.Real;
import asint.AnalizadorSintacticoTiny.Bool;
import asint.AnalizadorSintacticoTiny.Instr_muchas;
import asint.AnalizadorSintacticoTiny.Instr_una;
import asint.AnalizadorSintacticoTiny.Instr;
import asint.AnalizadorSintacticoTiny.Num_ent;
import asint.AnalizadorSintacticoTiny.Num_real;
import asint.AnalizadorSintacticoTiny.Id;
import asint.AnalizadorSintacticoTiny.Boolean;
import asint.AnalizadorSintacticoTiny.Suma;
import asint.AnalizadorSintacticoTiny.Resta;
import asint.AnalizadorSintacticoTiny.And;
import asint.AnalizadorSintacticoTiny.Or;
import asint.AnalizadorSintacticoTiny.Menor;
import asint.AnalizadorSintacticoTiny.Mayor;
import asint.AnalizadorSintacticoTiny.Menor_igual;
import asint.AnalizadorSintacticoTiny.Mayor_igual;
import asint.AnalizadorSintacticoTiny.Diferente;
import asint.AnalizadorSintacticoTiny.Igual;
import asint.AnalizadorSintacticoTiny.Mult;
import asint.AnalizadorSintacticoTiny.Div;
import asint.AnalizadorSintacticoTiny.Not;
import asint.AnalizadorSintacticoTiny.Negativo;

public class ProcesamientoPorDefecto implements Procesamiento {
	public void procesa(Prog prog) {}
	public void procesa(Decs_muchas decs) {}
	public void procesa(Decs_una decs) {}
	public void procesa(Dec dec) {}
	public void procesa(Int tipo) {}
	public void procesa(Real tipo) {}
	public void procesa(Bool tipo) {}
	public void procesa(Instr_muchas instrs) {}
	public void procesa(Instr_una instrs) {}
	public void procesa(Instr instr) {}
	public void procesa(Num_ent exp) {}
	public void procesa(Num_real exp) {}
	public void procesa(Id exp) {}
	public void procesa(Boolean exp) {}
	public void procesa(Suma exp) {}
	public void procesa(Resta exp) {}
	public void procesa(And exp) {}
	public void procesa(Or exp) {}
	public void procesa(Menor exp) {}
	public void procesa(Mayor exp) {}
	public void procesa(Menor_igual exp) {}
	public void procesa(Mayor_igual exp) {}
	public void procesa(Diferente exp) {}
	public void procesa(Igual exp) {}
	public void procesa(Mult exp) {}
	public void procesa(Div exp) {}
	public void procesa(Not exp) {}
	public void procesa(Negativo exp) {}
}