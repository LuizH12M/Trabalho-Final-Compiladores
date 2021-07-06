//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploSem.y"
  import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short DOUBLE=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short STRING=262;
public final static short LITERAL=263;
public final static short AND=264;
public final static short VOID=265;
public final static short MAIN=266;
public final static short IF=267;
public final static short STRUCT=268;
public final static short FUNC=269;
public final static short RETURN=270;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    4,    0,    3,    3,    5,    5,    9,    6,    6,   11,
   12,   12,    7,    7,    7,   14,   13,   13,   15,   15,
   20,   17,   19,    8,    8,   10,   10,   21,    1,    1,
    1,    1,   18,   22,   16,   16,   23,   24,   23,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
   25,   25,
};
final static short yylen[] = {                            2,
    0,    4,    2,    0,    2,    0,    0,    5,    1,    6,
    2,    0,   10,    1,    3,    1,    1,    1,    3,    1,
    2,    2,    1,    4,    0,    3,    1,    1,    1,    1,
    1,    1,    5,    3,    2,    0,    4,    0,    6,    3,
    3,    3,    3,    1,    3,    1,    4,    5,    3,    4,
    3,    1,
};
final static short yydefred[] = {                         1,
    0,    4,    0,   32,   29,   30,   31,    0,    6,    7,
    3,    9,    0,    0,    0,   12,    0,    0,   17,    5,
    0,   14,    0,    0,    0,    0,    0,   16,    0,    0,
    0,   28,    0,   27,    0,   11,    0,    0,   15,    0,
    8,    0,   10,    0,    0,    0,   20,   24,   26,   36,
   33,   21,    0,    0,    0,   36,   19,    0,    0,   34,
   35,    0,    0,    0,    0,    0,    0,   44,    0,    0,
    0,   23,   22,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   37,   38,   13,    0,    0,    0,   45,    0,
    0,    0,    0,    0,   49,    0,   47,    0,    0,   50,
   39,   48,    0,
};
final static short yydgoto[] = {                          1,
   10,   70,    3,    2,   14,   11,   20,   25,   15,   33,
   12,   26,   21,   29,   46,   55,   66,   22,   23,   47,
   34,   51,   61,   96,   88,
};
final static short yysindex[] = {                         0,
    0,    0, -139,    0,    0,    0,    0, -223,    0,    0,
    0,    0,  -80, -174,  -28,    0,    0, -199,    0,    0,
 -177,    0,   49, -171, -159, -112,   59,    0,   65,   74,
   23,    0,   18,    0,   63,    0,   82, -132,    0,  -28,
    0, -159,    0,   16, -159,   53,    0,    0,    0,    0,
    0,    0,   19, -132, -105,    0,    0,   73,   98,    0,
    0, -175,  -36,  -36, -116,   84,  104,    0,  -36,    2,
  -31,    0,    0,   24,    6,  -13,  -36,  -36,  -36,  -36,
  -36,  -36,    0,    0,    0,   94,   26,   62,    0,   33,
   -4,    9,  -21,   11,    0, -231,    0,   96,  -36,    0,
    0,    0,   26,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  150, -106,    0,  -17, -103,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -106,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   91,    0,    0,  -22,
   15,  -27,  -35,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   92,
};
final static short yygindex[] = {                         0,
   27,   32,    0,    0,    0,  131,    0,  118,    0,    0,
    0,    0,    0,    0,    0,  103,    0,    0,   99,  106,
   95,    0,   67,    0,    0,
};
final static int YYTABLESIZE=290;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         46,
   46,   46,   46,   69,   46,   41,   41,   41,   41,   84,
   80,   79,   35,   40,   82,   40,   40,   46,   43,   60,
   46,   43,   23,   41,   82,   58,   41,   89,   80,   79,
   78,   40,   82,   13,   40,   59,   43,   80,   79,   43,
   19,   82,   16,   80,   79,   69,   86,   82,   78,   46,
   80,   46,   80,   79,   82,   42,   82,   41,   42,   81,
   83,   42,   24,   78,   45,   40,   27,   80,   79,   81,
   43,   82,   78,   42,   80,   79,   41,   81,   82,   28,
   45,   58,   17,    5,    6,    7,   81,   78,   30,   31,
   18,   59,   81,   53,   65,   71,   54,   32,   37,   81,
   76,   81,   98,  100,   38,   99,   87,   42,   90,   91,
   92,   93,   94,   95,   39,   40,   81,    4,    5,    6,
    7,   43,   44,   81,    4,    5,    6,    7,    8,    9,
  103,   52,   51,   63,   52,   51,   49,   64,   50,   52,
   72,   56,   74,   75,    4,    5,    6,    7,   85,    2,
   25,   58,   97,   18,  102,    8,   36,   48,   62,   57,
    0,   59,  101,   73,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   67,    0,   46,    0,   68,    0,    0,    0,   41,    0,
    0,    0,   77,    0,    0,    0,   40,    0,    0,   32,
    0,   43,    0,    0,    0,    0,    0,    0,    0,    0,
   77,    0,    0,    0,    0,    0,    0,    0,    0,   77,
    0,    0,   67,    0,    0,   77,   68,    0,    0,    0,
    0,    0,    0,    0,   77,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   77,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,   44,   40,   46,   41,   42,   43,   44,   41,
   42,   43,  125,   41,   46,   43,   44,   59,   41,  125,
   62,   44,   40,   59,   46,  257,   62,   41,   42,   43,
   62,   59,   46,  257,   62,  267,   59,   42,   43,   62,
   14,   46,  123,   42,   43,   40,   41,   46,   62,   91,
   42,   93,   42,   43,   46,   41,   46,   93,   44,   91,
   59,   44,   91,   62,   38,   93,  266,   42,   43,   91,
   93,   46,   62,   59,   42,   43,   59,   91,   46,  257,
   54,  257,  257,  258,  259,  260,   91,   62,   40,  261,
  265,  267,   91,   41,  270,   64,   44,  257,   40,   91,
   69,   91,   41,   93,   40,   44,   75,   93,   77,   78,
   79,   80,   81,   82,   41,   93,   91,  257,  258,  259,
  260,   59,   41,   91,  257,  258,  259,  260,  268,  269,
   99,   41,   41,   61,   44,   44,   42,   40,  123,   45,
  257,  123,   59,   40,  257,  258,  259,  260,  125,    0,
  257,  257,   59,  257,   59,  268,   26,   40,   56,   54,
   -1,  267,   96,   65,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,   -1,  264,   -1,  261,   -1,   -1,   -1,  264,   -1,
   -1,   -1,  264,   -1,   -1,   -1,  264,   -1,   -1,  257,
   -1,  264,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  264,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  264,
   -1,   -1,  257,   -1,   -1,  264,  261,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  264,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  264,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=270;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
null,"'.'",null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","DOUBLE","BOOL","NUM",
"STRING","LITERAL","AND","VOID","MAIN","IF","STRUCT","FUNC","RETURN",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 declList FUNC funcList",
"declList : declList decl",
"declList :",
"funcList : funcList func",
"funcList :",
"$$2 :",
"decl : type $$2 TArray Lid ';'",
"decl : declStruct",
"declStruct : STRUCT IDENT '{' listaCampos '}' ';'",
"listaCampos : listaCampos decl",
"listaCampos :",
"func : typeRet Fid '(' listaParam ')' '{' listacmd retorno ';' '}'",
"func : main",
"func : IDENTNULL '(' ')'",
"Fid : IDENT",
"typeRet : type",
"typeRet : VOID",
"listaParam : listaParam ',' param",
"listaParam : param",
"param : type id",
"retorno : RETURN IDENTNULL",
"IDENTNULL : IDENT",
"TArray : '[' NUM ']' TArray",
"TArray :",
"Lid : Lid ',' id",
"Lid : id",
"id : IDENT",
"type : INT",
"type : DOUBLE",
"type : BOOL",
"type : IDENT",
"main : VOID MAIN '(' ')' bloco",
"bloco : '{' listacmd '}'",
"listacmd : listacmd cmd",
"listacmd :",
"cmd : IDENT '=' exp ';'",
"$$3 :",
"cmd : IF '(' exp ')' $$3 cmd",
"exp : exp '+' exp",
"exp : exp '*' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : '(' exp ')'",
"exp : IDENT",
"exp : IDENT '(' ')' ';'",
"exp : IDENT '(' listaExp ')' ';'",
"exp : exp '.' exp",
"exp : exp '[' exp ']'",
"listaExp : listaExp ',' exp",
"listaExp : exp",
};

//#line 170 "exemploSem.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static TS_entry Tp_VOID = new TS_entry("void", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;

  private ClasseID currClass;

  private TS_entry currentType;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_ARRAY);
    ts.insert(Tp_VOID);
    

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
              case '*' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                              (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && 
                         (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

//#line 454 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 25 "exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 7:
//#line 37 "exemploSem.y"
{ currentType = (TS_entry)val_peek(0).obj; /*System.out.println((TS_entry)$1);*/ }
break;
case 15:
//#line 50 "exemploSem.y"
{System.out.println("Detectou");}
break;
case 16:
//#line 54 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                  if (nodo != null){ 
                              yyerror("(sem) Funcao >" + val_peek(0).sval + "< jah declarada");
                  }
                   else ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass)); 
                       }
break;
case 17:
//#line 62 "exemploSem.y"
{ currentType = (TS_entry)val_peek(0).obj; System.out.println((TS_entry)val_peek(0).obj); }
break;
case 23:
//#line 77 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                              if (nodo == null) 
                                          yyerror("(sem) variavel >" + val_peek(0).sval + "< nao declarada");
                          }
break;
case 28:
//#line 93 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                  if (nodo != null){ 
                              yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");
                  }
                   else ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass)); 
                  }
break;
case 29:
//#line 105 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 30:
//#line 106 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 31:
//#line 107 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 32:
//#line 108 "exemploSem.y"
{ yyval.obj = null; }
break;
case 37:
//#line 123 "exemploSem.y"
{   TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                          if (nodo == null) {
                            yyerror("(sem) var <" + val_peek(3).sval + "> nao declarada"); 
                            /*$$ = Tp_ERRO;  */
                          }  
                          else   
                            validaTipo(ATRIB, nodo.getTipo(), (TS_entry)val_peek(1).obj); 
                          }
break;
case 38:
//#line 131 "exemploSem.y"
{  if ( ((TS_entry)val_peek(1).obj) != Tp_BOOL) 
                              yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(1).obj).getTipo());
                             }
break;
case 40:
//#line 137 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 41:
//#line 138 "exemploSem.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 42:
//#line 139 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 43:
//#line 140 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 44:
//#line 141 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 45:
//#line 142 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 46:
//#line 143 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                       yyval.obj = Tp_ERRO;    
                       }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 47:
//#line 151 "exemploSem.y"
{ yyval.obj=null;}
break;
case 48:
//#line 152 "exemploSem.y"
{ yyval.obj = null; }
break;
case 49:
//#line 154 "exemploSem.y"
{ yyval.obj = null; }
break;
case 50:
//#line 156 "exemploSem.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
//#line 735 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
