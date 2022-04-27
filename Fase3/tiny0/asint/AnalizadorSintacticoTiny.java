/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

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
        public Dec dec() {
            return dec;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
     }
    
	public static class Dec {
        private Tipo tipo;
        private StringLocalizado id;
        public Dec(Tipo tipo, StringLocalizado id) {
            this.id = id;
            this.tipo = tipo;
        }
        public Tipo type() {return tipo;}
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
    }
	
	// ------------ TIPOS --------------
	
	public static abstract class Tipo{
		public Tipo() {
        }   
		public String type() {throw new UnsupportedOperationException("decs");}
		public abstract void procesa(Procesamiento p);
	}	
	
	public static class Int extends Tipo{
		private String type;
		public Int() {
			type = "int";
		}   
		public String type() { return type;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
	
	public static class Real extends Tipo{
		private String type;
		public Real() {
			this.type = "real";
        }   
		public String type() { return type;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
        }   
	}
		
	public static class Bool extends Tipo{
		private String type;
		public Bool() {
			this.type = "bool";
        }  
		public String type() { return type;}
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
    
	public static class Instr {
        private StringLocalizado id;
        private Exp expresion;
        public Instr(StringLocalizado id, Exp expresion) {
            this.id = id;
            this.expresion = expresion;
        }
        public StringLocalizado id() {return id;}
        public Exp expresion() {return expresion;}
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
            return 5;
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
            return 5;
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
            return 5;
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
            return 5;
        }
    }
    
    public static class Suma extends ExpBin {
        public Suma(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
		public int prioridad() {
			return 0;
		}
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
    }
    
    public static class Resta extends ExpBin {
        public Resta(Exp arg0, Exp arg1) {
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
    public Dec dec(Tipo tipo, StringLocalizado id) {
        return new Dec(tipo,id);
    }
    public Int integer() {
        return new Int();
    }
    public Real real() {
        return new Real();
    }
    public Bool bool() {
        return new Bool();
    }
    public Instr_muchas instr_muchas(Instrs instrs, Instr instr) {
        return new Instr_muchas(instrs, instr);
    }
    public Instr_una instr_una(Instr instr) {
        return new Instr_una(instr);
    }
    public Instr instr(StringLocalizado id, Exp expresion) {
        return new Instr(id, expresion);
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
    
    public Exp suma(Exp arg0, Exp arg1) {
        return new Suma(arg0,arg1);
    }
    
    public Exp resta(Exp arg0, Exp arg1) {
        return new Resta(arg0,arg1);
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
    
    public Exp div(Exp arg0, Exp arg1) {
        return new Div(arg0,arg1);
    }
    
    public Exp not(Exp num){
        return new Not(num);
    }
    
    public Exp negativo(Exp num){
        return new Negativo(num);
    }
    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}
