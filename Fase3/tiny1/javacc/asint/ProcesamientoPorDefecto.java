package asint;

import asint.AnalizadorSintacticoTiny.*;
import asint.AnalizadorSintacticoTiny.Boolean;

public class ProcesamientoPorDefecto implements Procesamiento {
	public void procesa(Prog prog){}
	public void procesa(Decs_muchas decs_muchas){}
	public void procesa(Decs_una decs_una){}
	public void procesa(Decs_vacia decs_vacia){}
	public void procesa(Dec_var dec_var){}
	public void procesa(Dec_tipo dec_tipo){}
	public void procesa(Dec_proc dec_proc){}
	public void procesa(ParamsForm_muchos paramsForm_muchos){}
	public void procesa(ParamsForm_uno paramsForm_uno){}
	public void procesa(ParamsForm_vacio paramsForm_vacio){}
	public void procesa(Param_sin_amp param_sin_amp){}
	public void procesa(Param_con_amp param_con_amp){}
	public void procesa(Bloque bloque){}
	public void procesa(Int tInt){}
	public void procesa(Real real){}
	public void procesa(Bool bool){}
	public void procesa(TString tString){}
	public void procesa(TipoId tipoId){}
	public void procesa(TipoArray tipoArray){}
	public void procesa(TipoRegistro tipoRegistro){}
	public void procesa(TipoPuntero tipoPuntero){}
	public void procesa(Campos_muchos campos_muchos){}
	public void procesa(Campos_uno campos_uno){}
	public void procesa(Campo campo){}
	public void procesa(Instr_muchas instr_muchas){}
	public void procesa(Instr_una instr_una){}
	public void procesa(Instr_vacia instr_vacia){}
	public void procesa(InstruccionAsig instruccionAsig){}
	public void procesa(InstruccionIf instruccionIf){}
	public void procesa(InstruccionIfElse instruccionIfElse){}
	public void procesa(InstruccionWhile instruccionWhile){}
	public void procesa(InstruccionRead instruccionRead){}
	public void procesa(InstruccionWrite instruccionWrite){}
	public void procesa(InstruccionNew instruccionNew){}
	public void procesa(InstruccionDelete instrs){}
	public void procesa(InstruccionCall instrs){}
	public void procesa(InstruccionBloque instrs){}
	public void procesa(InstruccionNL instrs){}
	public void procesa(ParamReales_muchos params){}
	public void procesa(ParamReales_uno params){}
	public void procesa(ParamReales_vacios params){}
	public void procesa(Num_ent exp){}
	public void procesa(Num_real exp){}
	public void procesa(Id exp){}
	public void procesa(Boolean exp){}
	public void procesa(Cadena exp){}
	public void procesa(Null exp){}
	public void procesa(Mas exp){}
	public void procesa(Menos exp){}
	public void procesa(And exp){}
	public void procesa(Or exp){}
	public void procesa(Menor exp){}
	public void procesa(Mayor exp){}
	public void procesa(Menor_igual exp){}
	public void procesa(Mayor_igual exp){}
	public void procesa(Diferente exp){}
	public void procesa(Igual exp){}
	public void procesa(Mult exp){}
	public void procesa(Asterisco asterisco){}
	public void procesa(Div exp){}
	public void procesa(Porcentaje exp){}
	public void procesa(Not exp){}
	public void procesa(Negativo exp){}
	public void procesa(Indexacion indexacion){}
	public void procesa(Acceso_registro acceso_registro){}
}