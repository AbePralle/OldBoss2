package boss.vm;

import java.io.*;
import java.util.*;

public class BossTokens
{
  // PROPERTIES
  public BossIntList definitions = new BossIntList( 256 );     // [flags, name string index]
  public BossIntList   tokenData = new BossIntList( 8192 );    // [type, content value/index, filepath index, line, column]
  public BossStringList  strings = new BossStringList( 512 );
  public BossDoubleList  reals   = new BossDoubleList( 512 );
  public HashMap<String,Integer> stringLookup = new HashMap<String,Integer>();

  int    nextFilepath;
  int    nextLine;
  int    nextColumn;

  // METHODS
  public BossTokens()
  {
    int isKeyword    = BossTokenType.KEYWORD;
    int isSymbol     = BossTokenType.SYMBOL;
    int isStructural = BossTokenType.STRUCTURAL;

    define( BossTokenType.EOI,        "[end of input]", isStructural );
    define( BossTokenType.EOL,        "[end of line]",  isSymbol );
    define( BossTokenType.IDENTIFIER, "identifier", 0 );
    define( BossTokenType.STRING,     "String", 0 );

    define( BossTokenType.KEYWORD_CLASS,        "class",      isKeyword | isStructural );
    define( BossTokenType.KEYWORD_ELSE,         "else",       isKeyword | isStructural );
    define( BossTokenType.KEYWORD_ELSE_IF,      "elseIf",     isKeyword | isStructural );
    define( BossTokenType.KEYWORD_END_CLASS,    "endClass",   isKeyword | isStructural );
    define( BossTokenType.KEYWORD_END_IF,       "endIf",      isKeyword | isStructural );
    define( BossTokenType.KEYWORD_END_ROUTINE,  "endRoutine", isKeyword | isStructural );
    define( BossTokenType.KEYWORD_END_WHILE,    "endWhile",   isKeyword | isStructural );
    define( BossTokenType.KEYWORD_FALSE,        "false",      isKeyword );
    define( BossTokenType.KEYWORD_GLOBAL,       "global",     isKeyword );
    define( BossTokenType.KEYWORD_IF,           "if",         isKeyword );
    define( BossTokenType.KEYWORD_IMPORT,       "import",     isKeyword );
    define( BossTokenType.KEYWORD_LOCAL,        "local",      isKeyword );
    define( BossTokenType.KEYWORD_METHOD,       "method",     isKeyword | isStructural );
    define( BossTokenType.KEYWORD_METHODS,      "METHODS",    isKeyword | isStructural );
    define( BossTokenType.KEYWORD_NULL,         "null",       isKeyword );
    define( BossTokenType.KEYWORD_PRINTLN,      "println",    isKeyword );
    define( BossTokenType.KEYWORD_PROPERTIES,   "PROPERTIES", isKeyword | isStructural );
    define( BossTokenType.KEYWORD_RETURN,       "return",     isKeyword );
    define( BossTokenType.KEYWORD_ROUTINE,      "routine",    isKeyword | isStructural );
    define( BossTokenType.KEYWORD_THIS_CALL,    "thisCall",   isKeyword | isStructural );
    define( BossTokenType.KEYWORD_TRUE,         "true",       isKeyword );
    define( BossTokenType.KEYWORD_UNDEFINED,    "undefined",  isKeyword );
    define( BossTokenType.KEYWORD_WHILE,        "while",      isKeyword );

    define( BossTokenType.SYMBOL_AMPERSAND,     "&",   isSymbol );
    define( BossTokenType.SYMBOL_ARROW,         "->",  isSymbol );
    define( BossTokenType.SYMBOL_ASTERISK,      "*",   isSymbol );
    define( BossTokenType.SYMBOL_BANG,          "!",   isSymbol );
    define( BossTokenType.SYMBOL_CARET,         "^",   isSymbol );
    define( BossTokenType.SYMBOL_CLOSE_PAREN,   ")",   isSymbol | isStructural );
    define( BossTokenType.SYMBOL_COLON,         ":",   isSymbol );
    define( BossTokenType.SYMBOL_COLON_COLON,   "::",  isSymbol );
    define( BossTokenType.SYMBOL_COMMA,         ",",   isSymbol );
    define( BossTokenType.SYMBOL_DOLLAR,        "$",   isSymbol );
    define( BossTokenType.SYMBOL_EQUALS,        "=",   isSymbol );
    define( BossTokenType.SYMBOL_EQ,            "==",  isSymbol );
    define( BossTokenType.SYMBOL_GE,            ">=",  isSymbol );
    define( BossTokenType.SYMBOL_GT,            ">",   isSymbol );
    define( BossTokenType.SYMBOL_LEFT_SHIFT,    "<<",  isSymbol );
    define( BossTokenType.SYMBOL_LE,            "<=",  isSymbol );
    define( BossTokenType.SYMBOL_LT,            "<",   isSymbol );
    define( BossTokenType.SYMBOL_MINUS,         "-",   isSymbol );
    define( BossTokenType.SYMBOL_MINUS_MINUS,   "--",  isSymbol );
    define( BossTokenType.SYMBOL_NE,            "!=",  isSymbol );
    define( BossTokenType.SYMBOL_OPEN_PAREN,    "(",   isSymbol );
    define( BossTokenType.SYMBOL_PERCENT,       "%",   isSymbol );
    define( BossTokenType.SYMBOL_PERIOD,        ".",   isSymbol );
    define( BossTokenType.SYMBOL_PLUS,          "+",   isSymbol );
    define( BossTokenType.SYMBOL_PLUS_PLUS,     "++",  isSymbol );
    define( BossTokenType.SYMBOL_RIGHT_SHIFT,   ">>",  isSymbol );
    define( BossTokenType.SYMBOL_RIGHT_SHIFT_X, ">>>", isSymbol );
    define( BossTokenType.SYMBOL_SEMICOLON,     ";",   isSymbol );
    define( BossTokenType.SYMBOL_SLASH,         "/",   isSymbol );
    define( BossTokenType.SYMBOL_TILDE,         "~",   isSymbol );
    define( BossTokenType.SYMBOL_VERTICAL_BAR,  "|",   isSymbol );
  }

  public void define( int tokenType, String name, int flags )
  {
    int index = tokenType * 2;
    definitions.expandToCount( index + 2 );

    definitions.set( index, flags );
    definitions.set( index+1, stringIndex(name) );
  }

  public void setFilepath( String nextFilepath )
  {
    this.nextFilepath = stringIndex( nextFilepath );
  }

  public int stringIndex( String value )
  {
    // TODO: maybe replace HashMap with an efficient custom hash lookup to avoid Integer objects
    Integer existingIndex = stringLookup.get( value );
    if (existingIndex != null)
    {
      return (int) existingIndex;
    }
    else
    {
      stringLookup.put( value, strings.count );
      strings.add( value );
      return strings.count - 1;
    }
  }

  public int token( int type )
  {
    return token( type, 0 );
  }

  public int token( int type, int content )
  {
    BossIntList tokenData = this.tokenData;
    tokenData.reserve( 5 );
    int index = tokenData.count;
    tokenData.add( type );
    tokenData.add( content );
    tokenData.add( nextFilepath );
    tokenData.add( nextLine );
    tokenData.add( nextColumn );
    return index;
  }

  public int token( int type, double content )
  {
    // TODO: maybe consolidate reals with an efficient custom hash lookup
    reals.add( content );
    return token( type, reals.count - 1 );
  }

  public int token( int type, String content )
  {
    return token( type, stringIndex(content) );
  }
}

