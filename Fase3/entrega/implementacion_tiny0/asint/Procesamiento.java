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

public interface Procesamiento {
    void procesa(Prog prog);
    void procesa(Decs_muchas decs);
    void procesa(Decs_una decs);
    void procesa(Dec dec);
    void procesa(Int tipo);
    void procesa(Real tipo);
    void procesa(Bool tipo);
    void procesa(Instr_muchas instrs);
    void procesa(Instr_una instrs);
    void procesa(Instr instr);    
    void procesa(Num_ent exp);   
    void procesa(Num_real exp);  
    void procesa(Id exp);  
    void procesa(Boolean exp);  
    void procesa(Suma exp);  
    void procesa(Resta exp);  
    void procesa(And exp);  
    void procesa(Or exp);  
    void procesa(Menor exp);  
    void procesa(Mayor exp);   
    void procesa(Menor_igual exp);  
    void procesa(Mayor_igual exp);  
    void procesa(Diferente exp);  
    void procesa(Igual exp);  
    void procesa(Mult exp);  
    void procesa(Div exp);  
    void procesa(Not exp);  
    void procesa(Negativo exp);  
}