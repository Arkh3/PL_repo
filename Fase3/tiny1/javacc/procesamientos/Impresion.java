package procesamientos;

import asint.AnalizadorSintacticoTiny.*;
import asint.AnalizadorSintacticoTiny.Boolean;
import asint.ProcesamientoPorDefecto;


public class Impresion extends ProcesamientoPorDefecto {
    public Impresion() {}
    
    
    public void procesa(Prog prog) {
        if (!prog.decs().esVacia()) {
     		prog.decs().procesa(this);
        	System.out.println();
        	System.out.println("&&");
        }
        prog.instrs().procesa(this);
        System.out.println();       
    }
    public void procesa(Decs_muchas decs_muchas){
		decs_muchas.decs().procesa(this);
		System.out.println(";");
		decs_muchas.dec().procesa(this);
	}
	public void procesa(Decs_una decs_una){
		decs_una.dec().procesa(this);
	}
	public void procesa(Decs_vacia decs_vacia){}
	public void procesa(Dec_var dec_var){
		System.out.print("var ");
		dec_var.type().procesa(this);
		System.out.print(" " + dec_var.id().toString());
	}
	public void procesa(Dec_tipo dec_tipo){
		System.out.print("type ");
		dec_tipo.type().procesa(this);
		System.out.print(dec_tipo.id().toString());
	}
	public void procesa(Dec_proc dec_proc){
		System.out.print("proc ");
		System.out.print(dec_proc.id().toString() + "(");
		dec_proc.paramsForm().procesa(this);
		System.out.print(")");
		dec_proc.bloque().procesa(this);
	}
	public void procesa(ParamsForm_muchos paramsForm_muchos){ //TODO ASEGURARSE QUE LOS PARAMETROS NOS LOS IMPRIME AL REVES
		paramsForm_muchos.params().procesa(this);
		System.out.print(", ");
		paramsForm_muchos.param().procesa(this);
	}
	public void procesa(ParamsForm_uno paramsForm_uno){
		paramsForm_uno.param().procesa(this);
	}
	public void procesa(ParamsForm_vacio paramsForm_vacio){	
	}
	public void procesa(Param_sin_amp param_sin_amp){
		param_sin_amp.type().procesa(this);
		System.out.print(" " + param_sin_amp.id().toString());
	}
	public void procesa(Param_con_amp param_con_amp){
		param_con_amp.type().procesa(this);
		System.out.print(" & " + param_con_amp.id().toString());
	}
	public void procesa(Bloque bloque){
		System.out.println("{");
        if (!bloque.decs().esVacia()) {
        	bloque.decs().procesa(this);
        	System.out.println();
        	System.out.println("&&");
        }
        bloque.instrs().procesa(this);
		System.out.print("}");
	}
	public void procesa(Int tInt){
		System.out.print("int");
	}
	public void procesa(Real real){
		System.out.print("real");
	}
	public void procesa(Bool bool){
		System.out.print("bool");
	}
	public void procesa(TString tString){
		System.out.print("string");
	}
	public void procesa(TipoId tipoId){
		System.out.print(tipoId.id().toString());
	}
	public void procesa(TipoArray tipoArray){
		System.out.print("array [" + tipoArray.tam().toString() + "] of ");
		tipoArray.tipo().procesa(this);
	}
	public void procesa(TipoRegistro tipoRegistro){
		System.out.print("record {");
		tipoRegistro.campos().procesa(this);
		System.out.print("}");
	}
	public void procesa(TipoPuntero tipoPuntero){
		System.out.print("pointer ");
		tipoPuntero.tipo().procesa(this);
	}
	public void procesa(Campos_muchos campos_muchos){
		campos_muchos.campos().procesa(this);
		System.out.print(";");
		campos_muchos.campo().procesa(this);
	}
	public void procesa(Campos_uno campos_uno){
		campos_uno.campo().procesa(this);
	}
	public void procesa(Campo campo){
		campo.tipo().procesa(this);
		System.out.print(" " + campo.id().toString());
	}
	public void procesa(Instr_muchas instr_muchas) {
		instr_muchas.instrs().procesa(this);
		System.out.println(";");
		instr_muchas.instr().procesa(this);
	}

	public void procesa(Instr_una instr_una) {
		instr_una.instr().procesa(this);
	}

	public void procesa(Instr_vacia instr_vacia) {
	}

	public void procesa(InstruccionAsig instruccionAsig) {
		instruccionAsig.expresion_iz().procesa(this);
		System.out.print(" = ");
		instruccionAsig.expresion_der().procesa(this);
	}

	public void procesa(InstruccionIf instruccionIf) {
		System.out.print("if ");
		instruccionIf.expresion().procesa(this);
		System.out.println("then");
		instruccionIf.instrs().procesa(this);
		System.out.println();
		System.out.print("endif");
	}

	public void procesa(InstruccionIfElse instrs) {
		System.out.print("if ");
		instrs.expresion().procesa(this);
		System.out.println("then");
		instrs.instrs_if().procesa(this);
		System.out.println();
		System.out.println("else");
		instrs.instrs_else().procesa(this);
		System.out.println();
		System.out.print("endif");
	}

	public void procesa(InstruccionWhile instrs) {
		System.out.print("while");
		instrs.expresion().procesa(this);
		System.out.println("do");
		instrs.instrs().procesa(this);
		System.out.println();
		System.out.print("endwhile");
	}

	public void procesa(InstruccionRead instrs) {
		System.out.print("read ");
		instrs.expresion().procesa(this);
	}

	public void procesa(InstruccionWrite instrs) {
		System.out.print("write ");
		instrs.expresion().procesa(this);
	}

	public void procesa(InstruccionNew instrs) {
		System.out.print("new ");
		instrs.expresion().procesa(this);
	}

	public void procesa(InstruccionDelete instrs) {
		System.out.print("delete ");
		instrs.expresion().procesa(this);
	}

	public void procesa(InstruccionCall instrs) {
		System.out.print("call " + instrs.str().toString());
		System.out.print("(");
		instrs.params().procesa(this);
		System.out.print(")");
	}

	public void procesa(InstruccionBloque instrs) {
		instrs.bloque().procesa(this);
	}

	public void procesa(InstruccionNL instrs) {
		System.out.print("nl");
	}
	public void procesa(ParamReales_muchos params) {

		params.params().procesa(this);
		System.out.print(", ");
		params.arg0().procesa(this);
	}

	public void procesa(ParamReales_uno params) {
		params.param().procesa(this);
	}

	public void procesa(ParamReales_vacios params) {
	}

	public void procesa(Num_ent exp) {
		System.out.print(exp.num().toString());
	}

	public void procesa(Num_real exp) {

		System.out.print(exp.num().toString());
	}

	public void procesa(Id exp) {
		System.out.print(exp.id().toString());
	}

	public void procesa(Boolean exp) {
		System.out.print(exp.booleano().toString());
	}

	public void procesa(Cadena exp) {
		System.out.print(exp.cadena().toString());
	}

	public void procesa(Null exp) {
		System.out.print("null");
	}

	public void procesa(Mas exp) {
		imprime_arg(exp.arg0(), 1);
		System.out.print("+");
		imprime_arg(exp.arg1(), 0);
	}

	public void procesa(Menos exp) {
		imprime_arg(exp.arg0(), 1);
		System.out.print("-");
		imprime_arg(exp.arg1(), 1);

	}

	public void procesa(And exp) {
		imprime_arg(exp.arg0(), 1);
		System.out.print("and");
		imprime_arg(exp.arg1(), 2);
	}

	public void procesa(Or exp) {
		imprime_arg(exp.arg0(), 1);
		System.out.print("or");
		imprime_arg(exp.arg1(), 2);
	}

	public void procesa(Menor exp) {

		imprime_arg(exp.arg0(), 2);
		System.out.print("<");
		imprime_arg(exp.arg1(), 3);
	}

	public void procesa(Mayor exp) {
		imprime_arg(exp.arg0(), 2);
		System.out.print(">");
		imprime_arg(exp.arg1(), 3);
	}

	public void procesa(Menor_igual exp) {
		imprime_arg(exp.arg0(), 2);
		System.out.print("<=");
		imprime_arg(exp.arg1(), 3);
	}

	public void procesa(Mayor_igual exp) {
		imprime_arg(exp.arg0(), 2);
		System.out.print(">=");
		imprime_arg(exp.arg1(), 3);
	}

	public void procesa(Diferente exp) {
		imprime_arg(exp.arg0(), 2);
		System.out.print("!=");
		imprime_arg(exp.arg1(), 3);

	}

	public void procesa(Igual exp) {
		imprime_arg(exp.arg0(), 2);
		System.out.print("==");
		imprime_arg(exp.arg1(), 3);
	}

	public void procesa(Mult exp) {
		imprime_arg(exp.arg0(), 4);
		System.out.print("*");
		imprime_arg(exp.arg1(), 4);
	}

	public void procesa(Div exp) {
		imprime_arg(exp.arg0(), 4);
		System.out.print("/");
		imprime_arg(exp.arg1(), 4);

	}

	public void procesa(Porcentaje exp) {
		imprime_arg(exp.arg0(), 4);
		System.out.print("%");
		imprime_arg(exp.arg0(), 4);
	}

	public void procesa(Not exp) {
		System.out.print("not ");
		imprime_arg(exp.arg0(), 4);
	}

	public void procesa(Negativo exp) {
		System.out.print("-");
		imprime_arg(exp.arg0(), 5);
	}

	public void procesa(Indexacion indexacion) {
		imprime_arg(indexacion.arg0(), 5);
		System.out.print("[");
		imprime_arg(indexacion.arg1(), 0);
		System.out.print("]");
	}

	public void procesa(Acceso_registro acceso_registro) {
		// TODO HACER ESTO CON LAS FLECHAS Y LOS PUNTOS
		imprime_arg(acceso_registro.arg0(), 5);
		System.out.print("->");
		System.out.print(acceso_registro.str().toString());
	}

	public void procesa(Asterisco asterisco) {
		System.out.print("* ");
		imprime_arg(asterisco.arg0(), 6);
	}

	private void imprime_arg(Exp arg, int p) {
		if (arg.prioridad() < p) {
			System.out.print("(");
			arg.procesa(this);
			System.out.print(")");
		} else {
			arg.procesa(this);
		}
	}
}  

            