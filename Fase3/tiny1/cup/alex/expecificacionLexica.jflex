package alex;

import errors.GestionErroresTiny;

%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public


%{
  private GestionErroresTiny errores;
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPos = [1-9]
digito = ({digitoPos}|0)
numEnt = [\+\-]?(0|{digitoPos}{digito}*)
parteDecimal = \.({digito}*{digitoPos}|0)
parteExp = (e|E){numEnt}
separador = \&\&
int = int
real = real
bool = bool
numReal = {numEnt}({parteDecimal}|{parteExp}|{parteDecimal}{parteExp})
boolean = (true|false)
asignacion = \=
puntoComa = \;
parAbierto = \(
parCerrado = \)
suma = \+
resta = \-
div = \/
mult = \*
and = and
or = or
not = not
mayor = \>
menor = \<
mayorIgual = \>\=
menorIgual = \<\=
igual = \=\=
diferente = \!\=
porcentaje = \%
corcheteAbierto = \[
corcheteCerrado = \]
llaveAbierta = \{
llaveCerrada = \}
punto = \.
flecha = \-\>
coma =\,
ampersand = \&
string = string
null = null
proc = proc
if = if
then = then
else = else
endif = endif
while = while
do = do
endwhile = endwhile
call = call
record = record
array = array
of = of
pointer = pointer
new = new
delete = delete
read = read
write = write
nl = nl
var = var
type = type
comentario = #[^\n]* 
ignorable = [ \t\r\b\n]
cadena = \"[^\n\r\b]*\"
identificador = {letra}({letra}|{digito}|_)*
%%
{comentario}		{}
{ignorable} 		{}
{separador} 		{return ops.unidadSeparador();}
{numEnt} 			{return ops.unidadNumEntero();}
{int} 				{return ops.unidadInt();}
{real} 				{return ops.unidadReal();}
{bool} 				{return ops.unidadBool();}
{numReal} 			{return ops.unidadNumReal();}
{boolean} 			{return ops.unidadBoolean();}
{asignacion} 		{return ops.unidadAsignacion();}
{puntoComa} 		{return ops.unidadPuntoComa();}
{parAbierto} 		{return ops.unidadParAbierto();}
{parCerrado} 		{return ops.unidadParCerrado();}
{suma} 				{return ops.unidadSuma();}
{resta} 			{return ops.unidadResta();}
{div} 				{return ops.unidadDiv();}
{mult} 				{return ops.unidadMult();}
{and} 				{return ops.unidadAnd();}
{or} 				{return ops.unidadOr();}
{not} 				{return ops.unidadNot();}
{mayor} 			{return ops.unidadMayor();}
{menor} 			{return ops.unidadMenor();}
{mayorIgual} 		{return ops.unidadMayorIgual();}
{menorIgual} 		{return ops.unidadMenorIgual();}
{igual} 			{return ops.unidadIgual();}
{diferente} 		{return ops.unidadDiferente();}
{porcentaje} 		{return ops.unidadPorcentaje();}
{corcheteAbierto} 	{return ops.unidadCorcheteAbierto();}
{corcheteCerrado} 	{return ops.unidadCorcheteCerrado();}
{llaveAbierta} 		{return ops.unidadLlaveAbierta();}
{llaveCerrada} 		{return ops.unidadLlaveCerrada();}
{punto} 			{return ops.unidadPunto();}
{flecha}			{return ops.unidadFlecha();}
{coma} 				{return ops.unidadComa();}
{ampersand} 		{return ops.unidadAmpersand();}
{string} 			{return ops.unidadString();}
{null} 				{return ops.unidadNull();}
{proc} 				{return ops.unidadProc();}
{if} 				{return ops.unidadIf();}
{then} 				{return ops.unidadThen();}
{else} 				{return ops.unidadElse();}
{endif} 			{return ops.unidadEndif();}
{while} 			{return ops.unidadWhile();}
{do} 				{return ops.unidadDo();}
{endwhile} 			{return ops.unidadEndwhile();}
{call} 				{return ops.unidadCall();}
{record} 			{return ops.unidadRecord();}
{array} 			{return ops.unidadArray();}
{of} 				{return ops.unidadOf();}
{pointer} 			{return ops.unidadPointer();}
{new} 				{return ops.unidadNew();}
{delete} 			{return ops.unidadDelete();}
{read} 				{return ops.unidadRead();}
{write} 			{return ops.unidadWrite();}
{nl} 				{return ops.unidadNl();}
{var} 				{return ops.unidadVar();}
{type} 				{return ops.unidadType();}
{cadena} 			{return ops.unidadCadena();}
{identificador} 	{return ops.unidadId();}
[^]                 {errores.errorLexico(fila(),lexema());}  