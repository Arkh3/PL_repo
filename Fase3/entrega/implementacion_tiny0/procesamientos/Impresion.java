package procesamientos;

import asint.AnalizadorSintacticoTiny.Prog;
import asint.AnalizadorSintacticoTiny.Real;
import asint.AnalizadorSintacticoTiny.Resta;
import asint.AnalizadorSintacticoTiny.Suma;
import asint.AnalizadorSintacticoTiny.Int;
import asint.AnalizadorSintacticoTiny.Mayor;
import asint.AnalizadorSintacticoTiny.Mayor_igual;
import asint.AnalizadorSintacticoTiny.Menor;
import asint.AnalizadorSintacticoTiny.Menor_igual;
import asint.AnalizadorSintacticoTiny.Mult;
import asint.AnalizadorSintacticoTiny.Negativo;
import asint.AnalizadorSintacticoTiny.Not;
import asint.AnalizadorSintacticoTiny.Num_ent;
import asint.AnalizadorSintacticoTiny.Num_real;
import asint.AnalizadorSintacticoTiny.Or;
import asint.AnalizadorSintacticoTiny.Dec;
import asint.AnalizadorSintacticoTiny.Decs_una;
import asint.AnalizadorSintacticoTiny.Diferente;
import asint.AnalizadorSintacticoTiny.Div;
import asint.AnalizadorSintacticoTiny.Exp;
import asint.AnalizadorSintacticoTiny.Id;
import asint.AnalizadorSintacticoTiny.Igual;
import asint.AnalizadorSintacticoTiny.Instr;
import asint.AnalizadorSintacticoTiny.Instr_una;
import asint.AnalizadorSintacticoTiny.Decs_muchas;
import asint.AnalizadorSintacticoTiny.Instr_muchas;
import asint.AnalizadorSintacticoTiny.And;
import asint.AnalizadorSintacticoTiny.Bool;
import asint.AnalizadorSintacticoTiny.Boolean;
import asint.ProcesamientoPorDefecto;


public class Impresion extends ProcesamientoPorDefecto {
   public Impresion() {
   }
   
   public void procesa(Prog prog) {
       prog.decs().procesa(this);
       System.out.println();
       System.out.println("&&");
       prog.instrs().procesa(this);
       System.out.println();       
   }
   public void procesa(Decs_muchas decs) {
       decs.decs().procesa(this);
       System.out.println(";");
       decs.dec().procesa(this);
   }
   public void procesa(Decs_una decs) {
       decs.dec().procesa(this);
   }
   public void procesa(Dec dec) {
	   dec.type().procesa(this);
	   System.out.print(" "+dec.id().toString());
   }
   public void procesa(Int type) {
	   System.out.print("int");
   }
   public void procesa(Real type) {
	   System.out.print("real");
   }
   public void procesa(Bool type) {
	   System.out.print("bool");
   }
   public void procesa(Instr_muchas instrs) {
	   instrs.instrs().procesa(this);
       System.out.println(";");
       instrs.instr().procesa(this);
   }
   public void procesa(Instr_una instrs) {
	   instrs.instr().procesa(this);
   }
   public void procesa(Instr instr) {
	   System.out.print(instr.id().toString()+" = ");
	   instr.expresion().procesa(this);
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
   public void procesa(Suma exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print(" + ");
      imprime_arg(exp.arg1(),0);       
   }
   public void procesa(Resta exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print(" - ");
      imprime_arg(exp.arg1(),1);       
   }
   public void procesa(And exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" and ");
	      imprime_arg(exp.arg1(),3);       
   }
   public void procesa(Or exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" or ");
	      imprime_arg(exp.arg1(),3);       
   }
   public void procesa(Mayor exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" > ");
	      imprime_arg(exp.arg1(),3);       
}	
   public void procesa(Menor exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" < ");
	      imprime_arg(exp.arg1(),3);       
}
   public void procesa(Mayor_igual exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" >= ");
	      imprime_arg(exp.arg1(),3);       
}
   public void procesa(Menor_igual exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" <= ");
	      imprime_arg(exp.arg1(),3);       
}
   public void procesa(Diferente exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" != ");
	      imprime_arg(exp.arg1(),3);       
}
   public void procesa(Igual exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" == ");
	      imprime_arg(exp.arg1(),3);       
}
   public void procesa(Mult exp) {
      imprime_arg(exp.arg0(),4); 
      System.out.print(" * ");
      imprime_arg(exp.arg1(),4);       
   }
   public void procesa(Div exp) {
      imprime_arg(exp.arg0(),4); 
      System.out.print(" / ");
      imprime_arg(exp.arg1(),4);       
   }
   public void procesa(Not exp) {
	   System.out.print("not");
	   imprime_arg(exp.arg0(),4);
   }
   public void procesa(Negativo exp) {
	   System.out.print("-");
	   imprime_arg(exp.arg0(),5);
   }
   private void imprime_arg(Exp arg, int p) {
       if (arg.prioridad() < p) {
           System.out.print("(");
           arg.procesa(this);
           System.out.print(")");
       }
       else {
           arg.procesa(this);
       }
   }
}   

            