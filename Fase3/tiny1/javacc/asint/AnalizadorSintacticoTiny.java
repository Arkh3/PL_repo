/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import asint.AnalizadorSintacticoTiny.TipoPuntero;

public class AnalizadorSintacticoTiny {

	public static class StringLocalizado {
		private String s;
		private int fila;
		private int col;

		public StringLocalizado(String s, int fila, int col) {
			this.s = s;
			this.fila = fila;
			this.col = col;
		}

		public int fila() {
			return fila;
		}

		public int col() {
			return col;
		}

		public String toString() {
			return s;
		}

		public boolean equals(Object o) {
			return (o == this) || ((o instanceof StringLocalizado) && (((StringLocalizado) o).s.equals(s)));
		}

		public int hashCode() {
			return s.hashCode();
		}
	}

	/*TODO: CAMBIAR TANTO EN LA MEMORIA COMO EN LOS PROCESAMIENTOS 
	 * COMO AQUI: PROG DEBERIA HABER 2 PROG_SIN_DECS Y PROG_CON_DECS*/
	public static class Prog{
		private Decs decs;
		private Instrs instrs;
		
		public Prog(Decs decs, Instrs instrs) {
			this.decs = decs;
			this.instrs = instrs;
		}
       public Instrs instrs() {return instrs;}
       public Decs decs() {return decs;}
       
       public void procesa(Procesamiento p) {
           p.procesa(this); 
       }     
	}
	
	// ------------ DECLARACIONES --------------
	
    public static abstract class Decs {
        public Decs() {
        }   
        public abstract boolean esVacia();
        public abstract void procesa(Procesamiento p);
    }
    
    public static class Decs_muchas extends Decs {
        private Dec dec;
        private Decs decs;
        public Decs_muchas(Decs decs, Dec dec) {
           super();
           this.dec = dec;
           this.decs = decs;
        }
        public boolean esVacia() {
        	return false;
        }
        public Dec dec() {
            return dec;
        }
        public Decs decs() {
            return decs;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
     }
    
    public static class Decs_una extends Decs {
        private Dec dec; 
        public Decs_una(Dec dec) {
           super();
           this.dec = dec;
        }  
        public boolean esVacia() {
        	return false;
        }
        public Dec dec() {
            return dec;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
    public static class Decs_vacia extends Decs {
        public Decs_vacia() {
           super();
        }  
        public boolean esVacia() {
        	return true;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
	public static abstract class Dec {
        public Dec() {}   
        public abstract void procesa(Procesamiento p);  
    }
	
	public static class Dec_var extends Dec {
        private Tipo tipo;
        private StringLocalizado id;
        public Dec_var(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public Tipo type() {return tipo;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class Dec_tipo extends Dec {
        private Tipo tipo;
        private StringLocalizado id;
        public Dec_tipo(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public Tipo type() {return tipo;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class Dec_proc extends Dec {
        private StringLocalizado id;
        private ParamsForm params;
        private Bloque bloque;
        public Dec_proc(StringLocalizado id, ParamsForm params, Bloque bloque) {
            this.id = id;
            this.params = params;
            this.bloque = bloque;
        }
        public ParamsForm paramsForm() {return params;}
        public Bloque bloque() {return bloque;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
    public static abstract class ParamsForm {
        public ParamsForm() {}   
        public abstract void procesa(Procesamiento p);
    }
    
    public static class ParamsForm_muchos extends ParamsForm {
        private ParamsForm params;
        private Param param;
        public ParamsForm_muchos(ParamsForm params, Param param) {
           super();
           this.params = params;
           this.param = param;
        }
        public ParamsForm params() {
            return params;
        }
        public Param param() {
            return param;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
     }
    
    public static class ParamsForm_uno extends ParamsForm {
        private Param param;
        public ParamsForm_uno(Param param) {
           super();
           this.param = param;
        }
        public Param param() {
            return param;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }    
     }
    
    public static class ParamsForm_vacio extends ParamsForm {
        public ParamsForm_vacio() {
           super();
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
    public static abstract class Param {
        public Param() {}   
        public abstract void procesa(Procesamiento p);
    }
    
    public static class Param_sin_amp extends Param{
        private Tipo tipo;
        private StringLocalizado id;
        public Param_sin_amp(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public Tipo type() {return tipo;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

    public static class Param_con_amp extends Param{
        private Tipo tipo;
        private StringLocalizado id;
        public Param_con_amp(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public Tipo type() {return tipo;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
    
    public static class Bloque{
		private Decs decs;
		private Instrs instrs;
		
		public Bloque(Decs decs, Instrs instrs) {
			this.decs = decs;
			this.instrs = instrs;
		}
       public Instrs instrs() {return instrs;}
       public Decs decs() {return decs;}
       
       public void procesa(Procesamiento p) {
           p.procesa(this); 
       }     
    }
    
    
	// ------------ TIPOS --------------
	
	public static abstract class Tipo{
		public Tipo() {}
		public abstract void procesa(Procesamiento p);
	}	
	
	public static class Int extends Tipo{
		public Int() {
		}   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class Real extends Tipo{
		public Real() {
        }   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
		
	public static class Bool extends Tipo{
		public Bool() {
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class TString extends Tipo{
		public TString() {
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class TipoId extends Tipo{
		private StringLocalizado id;
		public TipoId(StringLocalizado id) {
			this.id = id;
        }  
		public StringLocalizado id() { return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class TipoArray extends Tipo{
		private StringLocalizado tam;
		private Tipo tipo;
		public TipoArray(StringLocalizado tam, Tipo tipo) {
			this.tipo = tipo;
			this.tam = tam;
        }  
		public StringLocalizado tam() { return tam;}
		public Tipo tipo() { return tipo;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class TipoRegistro extends Tipo{
		private Campos campos;
		public TipoRegistro(Campos campos) {
			this.campos = campos;
        }  
		public Campos campos() { return campos;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class TipoPuntero extends Tipo{
		private Tipo tipo;
		public TipoPuntero( Tipo tipo) {
			this.tipo = tipo;
        }  
		public Tipo tipo() { return tipo;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static abstract class Campos{
		public Campos() {}
		public abstract void procesa(Procesamiento p);
	}	
	
	public static class Campos_muchos extends Campos{
		private Campos campos;
		private Campo campo;
		
		public Campos_muchos(Campos campos, Campo campo) {
			this.campos = campos;
			this.campo = campo;
		}
		public Campo campo() { return campo;}
		public Campos campos() { return campos;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class Campos_uno extends Campos{
		private Campo campo;
		
		public Campos_uno(Campo campo) {
			this.campo = campo;
		}
		public Campo campo() { return campo;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class Campo {
		private Tipo tipo;
		private StringLocalizado id;
		public Campo(Tipo tipo,  StringLocalizado id) {
			this.tipo = tipo;
			this.id = id;
        }  
		public Tipo tipo() { return tipo;}
		public StringLocalizado id() { return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	// ------------ INSTRUCCIONES --------------
	
    public static abstract class Instrs {
        public Instrs() {
        }   
        public abstract void procesa(Procesamiento p);
     }
	
    public static class Instr_muchas extends Instrs {
        private Instr instr;
        private Instrs instrs;
        public Instr_muchas(Instrs instrs, Instr instr) {
           super();
           this.instr = instr;
           this.instrs = instrs;
        } 
        public Instr instr() {
            return instr;
        }
        public Instrs instrs() {
            return instrs;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
    public static class Instr_una extends Instrs {
        private Instr instr;
        public Instr_una(Instr instr) {
           super();
           this.instr = instr;
        } 
        public Instr instr() {
            return instr;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
    public static class Instr_vacia extends Instrs {
        public Instr_vacia() {} 
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
	public static abstract class Instr {
        public Instr() {
        }   
        public abstract void procesa(Procesamiento p);
     }
	
	public static class InstruccionAsig extends Instr{
		private Exp expresion_iz;
		private Exp expresion_der;
		public InstruccionAsig(Exp expresion_iz, Exp expresion_der) {
			this.expresion_iz = expresion_iz;
			this.expresion_der = expresion_der;
		}
        public Exp expresion_iz() {return expresion_iz;}
        public Exp expresion_der() {return expresion_der;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionIf extends Instr{
		private Exp expresion;
		private Instrs instrs;
		public InstruccionIf(Exp expresion, Instrs instrs) {
			this.expresion = expresion;
			this.instrs = instrs;
		}
        public Exp expresion() {return expresion;}
        public Instrs instrs() {return instrs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

	public static class InstruccionIfElse extends Instr{
		private Exp expresion;
		private Instrs instrs_if;
		private Instrs instrs_else;
		public InstruccionIfElse(Exp expresion, Instrs instrs_if, Instrs instrs_else) {
			this.expresion = expresion;
			this.instrs_if = instrs_if;
			this.instrs_else = instrs_else;
		}
        public Exp expresion() {return expresion;}
        public Instrs instrs_if() {return instrs_if;}
        public Instrs instrs_else() {return instrs_else;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

	public static class InstruccionWhile extends Instr{
		private Exp expresion;
		private Instrs instrs;
		public InstruccionWhile(Exp expresion, Instrs instrs) {
			this.expresion = expresion;
			this.instrs = instrs;
		}
        public Exp expresion() {return expresion;}
        public Instrs instrs() {return instrs;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }

	public static class InstruccionRead extends Instr{
		private Exp expresion;
		public InstruccionRead(Exp expresion) {
			this.expresion = expresion;
		}
        public Exp expresion() {return expresion;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionWrite extends Instr{
		private Exp expresion;
		public InstruccionWrite(Exp expresion) {
			this.expresion = expresion;
		}
        public Exp expresion() {return expresion;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionNew extends Instr{
		private Exp expresion;
		public InstruccionNew(Exp expresion) {
			this.expresion = expresion;
		}
        public Exp expresion() {return expresion;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionDelete extends Instr{
		private Exp expresion;
		public InstruccionDelete(Exp expresion) {
			this.expresion = expresion;
		}
        public Exp expresion() {return expresion;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionCall extends Instr{
		private StringLocalizado str;
		private ParamReales params;
		public InstruccionCall(StringLocalizado str, ParamReales params) {
			this.str = str;
			this.params = params;
		}
        public StringLocalizado str() {return str;}
        public ParamReales params() {return params;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionBloque extends Instr{
		private Bloque bloque;
		public InstruccionBloque(Bloque bloque) {
			this.bloque = bloque;
		}
        public Bloque bloque() {return bloque;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	public static class InstruccionNL extends Instr{
		public InstruccionNL() {
		}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
    public static abstract class ParamReales {
        public ParamReales() {
        }   
        public abstract void procesa(Procesamiento p);
    }
	
	public static class ParamReales_muchos extends ParamReales{
        private Exp arg0;
        private ParamReales params;
        public ParamReales_muchos(ParamReales params, Exp arg0) {
           super();
           this.arg0 = arg0;
           this.params = params;
        } 
        public ParamReales params() {
            return params;
        }
        public Exp arg0(){
            return arg0;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
       
    }
    
    public static class ParamReales_uno extends ParamReales{
        
        private Exp param;
        public ParamReales_uno(Exp param) {
           super();
           this.param = param;
        } 
        public Exp param() {
            return param;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }
    }
    
    public static class ParamReales_vacios extends ParamReales{
       
        public ParamReales_vacios() {
           super();
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
       
    }
    
	// ------------ EXPRESIONES --------------
    
	public static abstract class Exp {

		public Exp() {}   
		public abstract int prioridad();
		public abstract void procesa(Procesamiento procesamiento);
	}

    private static abstract class ExpBin extends Exp {
        private Exp arg0;
        private Exp arg1;
        public Exp arg0() {return arg0;}
        public Exp arg1() {return arg1;}
        public ExpBin(Exp arg0, Exp arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
    }

    public static class Num_ent extends Exp {
        private StringLocalizado num;
        public Num_ent(StringLocalizado num) {
            super();
            this.num = num;
        }
        public StringLocalizado num() {return num;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
	
    public static class Num_real extends Exp {
        private StringLocalizado num;
        public Num_real(StringLocalizado num) {
            super();
            this.num = num;
        }
        public StringLocalizado num() {return num;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    
    public static class Id extends Exp {
        private StringLocalizado id;
        public Id(StringLocalizado id) {
            super();
            this.id = id;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    
    public static class Boolean extends Exp {
        private StringLocalizado bool;
        public Boolean(StringLocalizado bool) {
            super();
            this.bool = bool;
        }
        public StringLocalizado booleano() {return bool;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    
    public static class Cadena extends Exp{
        private StringLocalizado cadena;
        public Cadena(StringLocalizado cadena) {
            super();
            this.cadena = cadena;
        }
        public StringLocalizado cadena() {return cadena;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 7;
        }
    }
    
    public static class Null extends Exp{
        public Null() {}   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
        public final int prioridad() {
            return 7;
        }
    }
    
    public static class Mas extends ExpBin {
        public Mas(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
		public int prioridad() {
			return 0;
		}
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Menos extends ExpBin {
        public Menos(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 0;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class And extends ExpBin {
        public And(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 1;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Or extends ExpBin {
        public Or(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 1;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Menor extends ExpBin {
        public Menor(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }

    public static class Mayor extends ExpBin {
        public Mayor(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }

    public static class Menor_igual extends ExpBin {
        public Menor_igual(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }

    public static class Mayor_igual extends ExpBin {
        public Mayor_igual(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Diferente extends ExpBin {
        public Diferente(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }

    public static class Igual extends ExpBin {
        public Igual(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Mult extends ExpBin {
        public Mult(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 3;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Asterisco extends Exp {
        private Exp arg0;
        public Asterisco(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        public Exp arg0() {return arg0;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 6;
        }
    }

    public static class Div extends ExpBin {
        public Div(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 3;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
     public static class Porcentaje extends ExpBin {
        public Porcentaje(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 3;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Not extends Exp {
        private Exp arg0;
        public Not(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        public Exp arg0() {return arg0;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 4;
        }
    }
    
    public static class Negativo extends Exp {
        private Exp arg0;
        public Negativo(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
        public Exp arg0() {return arg0;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 4;
        }
    }
    
    public static class Indexacion extends ExpBin {
        public Indexacion(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 5;
        }
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Acceso_registro extends Exp{
        
        private StringLocalizado str;
        private Exp arg0;
        public Acceso_registro(Exp arg0, StringLocalizado str) {
            super();
            this.arg0 = arg0;
            this.str = str;
        }
        public Exp arg0() {return arg0;}
        public StringLocalizado str(){return str;}
        public final int prioridad() {
            return 5;
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }  
        
    }
 
    // -----------------------------------------
    // ------------ CONSTRUCTORES --------------
    
    public Prog prog(Decs decs, Instrs instrs) {
        return new Prog(decs,instrs);
    }
    public Decs_muchas decs_muchas(Decs decs, Dec dec) {
        return new Decs_muchas(decs,dec);
    }
    public Decs_una decs_una(Dec dec) {
        return new Decs_una(dec);
    }
    public Decs_vacia decs_vacia() {
        return new Decs_vacia() ;
    }
    public Dec_var dec_var(Tipo tipo, StringLocalizado id) {
        return new Dec_var(tipo,id);
    }
    public Dec_tipo dec_tipo(Tipo tipo, StringLocalizado id) {
        return new Dec_tipo(tipo,id);
    }
    public Dec_proc dec_proc(StringLocalizado id, ParamsForm params, Bloque bloque) {
        return new Dec_proc(id,params, bloque);
    }   
    public ParamsForm_muchos paramsForm_muchos(ParamsForm params, Param param){
        return new ParamsForm_muchos(params,param);
    }
    public ParamsForm_uno paramsForm_uno(Param param){
        return new ParamsForm_uno(param);
    }
    public ParamsForm_vacio paramsForm_vacio(){
        return new ParamsForm_vacio();
    }
    public Param_sin_amp param_sin_amp(Tipo tipo, StringLocalizado id){
        return new Param_sin_amp(tipo, id);
    }
    public Param_con_amp param_con_amp(Tipo tipo, StringLocalizado id){
        return new Param_con_amp(tipo, id);
    }
    public Bloque bloque(Decs decs, Instrs instrs){
        return new Bloque(decs, instrs);
    }
    public Int tipoInt() {
        return new Int();
    }
    public Real tipoReal() {
        return new Real();
    }
    public Bool tipoBool() {
        return new Bool();
    }
    public TString tipoString() {
        return new TString();
    }
    public TipoId tipoId(StringLocalizado id) {
        return new TipoId(id);
    }
    public TipoPuntero tipoPuntero(Tipo tipo) {
        return new TipoPuntero(tipo);
    }
    public TipoArray tipoArray(StringLocalizado tam, Tipo tipo) {
        return new TipoArray(tam,tipo);
    }
    public TipoRegistro tipoRegistro(Campos campos) {
        return new TipoRegistro(campos);
    }
    public Campos_muchos campos_muchos(Campos campos, Campo campo){
        return new Campos_muchos(campos, campo);
    }
    public Campos_uno campos_uno(Campo campo) {
        return new Campos_uno(campo);
    }
    public Campo campo(Tipo tipo,  StringLocalizado id) {
        return new Campo(tipo, id);
    }
    public Instr_muchas instr_muchas(Instrs instrs, Instr instr) {
        return new Instr_muchas(instrs, instr);
    }
    public Instr_una instr_una(Instr instr) {
        return new Instr_una(instr);
    }
    public Instr_vacia instr_vacia() {
        return new Instr_vacia();
    }
    public InstruccionAsig instruccionAsig(Exp expresion_iz, Exp expresion_der) {
        return new InstruccionAsig(expresion_iz, expresion_der);
    }
    public InstruccionIf instruccionIf(Exp expresion, Instrs instrs_if){
        return new InstruccionIf(expresion, instrs_if);
    }
    public InstruccionIfElse instruccionIfElse(Exp expresion, Instrs instrs_if, Instrs instrs_else){
        return new InstruccionIfElse(expresion, instrs_if, instrs_else);
    }
    
    public InstruccionWhile instruccionWhile(Exp expresion, Instrs instrs){
        return new InstruccionWhile(expresion, instrs);
    }
    public InstruccionNew instruccionNew(Exp expresion){
        return new InstruccionNew(expresion);
    }
    public InstruccionRead instruccionRead(Exp expresion){
        return new InstruccionRead(expresion);
    }
    public InstruccionWrite instruccionWrite(Exp expresion){
        return new InstruccionWrite(expresion);
    }
    public InstruccionDelete instruccionDelete(Exp expresion){
        return new InstruccionDelete(expresion);
    }
    
    public InstruccionCall instruccionCall(StringLocalizado str, ParamReales params){
        return new InstruccionCall(str, params);
    }
    
    public InstruccionBloque instruccionBloque(Bloque bloque){
        return new InstruccionBloque(bloque);
    }
    
    public InstruccionNL instruccionNL(){
        return new InstruccionNL();
    }
    
    public ParamReales_muchos paramReales_muchos(Exp arg0, ParamReales param){
        return new ParamReales_muchos(param, arg0);
    }
    
    public ParamReales_uno paramReales_uno(Exp arg0){
        return new ParamReales_uno(arg0);
    }
    
    public ParamReales_vacios paramReales_vacios(){
        return new ParamReales_vacios();
    }
    
    public Exp num_ent(StringLocalizado num)  {
        return new Num_ent(num);
    }
    public Exp num_real(StringLocalizado num) {
        return new Num_real(num);
    }
    
    public Exp id(StringLocalizado num) {
        return new Id(num);
    }
    
    public Exp booleano(StringLocalizado num){
        return new Boolean(num);
    }
    
    public Exp cadena(StringLocalizado arg0){
        return new Cadena(arg0);
    }
    public Exp nulo(){
        return new Null();
    }
    
    public Exp mas(Exp arg0, Exp arg1) {
        return new Mas(arg0,arg1);
    }
    
    public Exp menos(Exp arg0, Exp arg1) {
        return new Menos(arg0,arg1);
    }
    
    public Exp and (Exp arg0, Exp arg1) {
        return new And(arg0,arg1);
    }
    
    public Exp or(Exp arg0, Exp arg1) {
        return new Or(arg0,arg1);
    }
    
    public Exp menor(Exp arg0, Exp arg1) {
        return new Menor(arg0,arg1);
    }
    
    public Exp mayor(Exp arg0, Exp arg1) {
        return new Mayor(arg0,arg1);
    }
    
    public Exp menor_igual(Exp arg0, Exp arg1) {
        return new Menor_igual(arg0,arg1);
    }
    
    public Exp mayor_igual(Exp arg0, Exp arg1) {
        return new Mayor_igual(arg0,arg1);
    }
    
    public Exp diferente(Exp arg0, Exp arg1) {
        return new Diferente(arg0,arg1);
    }
    
    public Exp igual(Exp arg0, Exp arg1) {
        return new Igual(arg0,arg1);
    }
    
    public Exp mult(Exp arg0, Exp arg1) {
        return new Mult(arg0,arg1);
    }
    
    public Exp asterisco(Exp arg0){
        return new Asterisco(arg0);
    }
    
    public Exp div(Exp arg0, Exp arg1) {
        return new Div(arg0,arg1);
    }
    
    public Exp porcentaje(Exp arg0, Exp arg1) {
        return new Porcentaje(arg0,arg1);
    }
    
    public Exp not(Exp num){
        return new Not(num);
    }
    
    public Exp negativo(Exp num){
        return new Negativo(num);
    }
    
    public Exp indexacion (Exp arg0, Exp arg1) {
        return new Indexacion(arg0,arg1);
    }
    
    public Exp acceso_registro(StringLocalizado str, Exp arg0){
        return new Acceso_registro(arg0, str);
    }
    
    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}
