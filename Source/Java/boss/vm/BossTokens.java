package boss.vm;

import java.io.*;
import java.util.*;

public class BossTokens
{
  // PROPERTIES
  public BossIntList definitions = new BossIntList( 256 );     // [flags, name string index]
  public BossIntList   tokenData = new BossIntList( 2048 );    // [type,value/index]
  public BossStringList  strings = new BossStringList( 512 );
  public HashMap<String,Integer> stringLookup = new HashMap<String,Integer>();

  // METHODS
  public BossTokens()
  {
    tokenData.add( 0 );
    tokenData.add( 0 );

    int isKeyword    = BossTokenType.KEYWORD;
    int isSymbol     = BossTokenType.SYMBOL;
    int isStructural = BossTokenType.STRUCTURAL;

    define( BossTokenType.EOI,        "[end of input]", isStructural );
    define( BossTokenType.EOL,        "[end of line]",  isSymbol );
    define( BossTokenType.IDENTIFIER, "identifier", 0 );
    define( BossTokenType.STRING,     "String", 0 );
    define( BossTokenType.INT32,      "Int32", 0 );

    /*
    KEYWORD_CLASS       ( "class",      &is_keyword, &is_structural )
    KEYWORD_ELSE        ( "else",       &is_keyword, &is_structural )
    KEYWORD_ELSE_IF     ( "elseIf",     &is_keyword, &is_structural )
    KEYWORD_END_CLASS   ( "endClass",   &is_keyword, &is_structural )
    KEYWORD_END_IF      ( "endIf",      &is_keyword, &is_structural )
    KEYWORD_END_ROUTINE ( "endRoutine", &is_keyword, &is_structural )
    KEYWORD_END_WHILE   ( "endWhile",   &is_keyword, &is_structural )
    KEYWORD_FALSE       ( "false",      &is_keyword )
    KEYWORD_GLOBAL      ( "global",     &is_keyword )
    KEYWORD_IF          ( "if",         &is_keyword )
    KEYWORD_IMPORT      ( "import",     &is_keyword )
    KEYWORD_LOCAL       ( "local",      &is_keyword )
    KEYWORD_METHOD      ( "method",     &is_keyword, &is_structural )
    KEYWORD_METHODS     ( "METHODS",    &is_keyword, &is_structural )
    KEYWORD_NULL        ( "null",       &is_keyword )
    KEYWORD_PRINTLN     ( "println",    &is_keyword )
    KEYWORD_PROPERTIES  ( "PROPERTIES", &is_keyword, &is_structural )
    KEYWORD_RETURN      ( "return",     &is_keyword )
    KEYWORD_ROUTINE     ( "routine",    &is_keyword, &is_structural )
    #KEYWORD_THIS        ( "this",       &is_keyword )
    KEYWORD_THIS_CALL   ( "thisCall",   &is_keyword, &is_structural )
    KEYWORD_TRUE        ( "true",       &is_keyword )
    KEYWORD_UNDEFINED   ( "undefined",  &is_keyword )
    KEYWORD_WHILE       ( "while",      &is_keyword )

    SYMBOL_AMPERSAND    ( "&",   &is_symbol )
    SYMBOL_ARROW        ( "->",  &is_symbol )
    SYMBOL_ASTERISK     ( "*",   &is_symbol )
    SYMBOL_BANG         ( "!",   &is_symbol )
    SYMBOL_CARET        ( "^",   &is_symbol )
    SYMBOL_CLOSE_PAREN  ( ")",   &is_symbol, &is_structural )
    SYMBOL_COLON        ( ":",   &is_symbol )
    SYMBOL_COLON_COLON  ( "::",  &is_symbol )
    SYMBOL_COMMA        ( ",",   &is_symbol )
    SYMBOL_DOLLAR       ( "$",   &is_symbol )
    SYMBOL_EQUALS       ( "=",   &is_symbol )
    SYMBOL_EQ           ( "==",  &is_symbol )
    SYMBOL_GE           ( ">=",  &is_symbol )
    SYMBOL_GT           ( ">",   &is_symbol )
    SYMBOL_LEFT_SHIFT   ( "<<",  &is_symbol )
    SYMBOL_LE           ( "<=",  &is_symbol )
    SYMBOL_LT           ( "<",   &is_symbol )
    SYMBOL_MINUS        ( "-",   &is_symbol )
    SYMBOL_MINUS_MINUS  ( "--",  &is_symbol )
    SYMBOL_NE           ( "!=",  &is_symbol )
    SYMBOL_OPEN_PAREN   ( "(",   &is_symbol )
    SYMBOL_PERCENT      ( "%",   &is_symbol )
    SYMBOL_PERIOD       ( ".",   &is_symbol )
    SYMBOL_PLUS         ( "+",   &is_symbol )
    SYMBOL_PLUS_PLUS    ( "++",  &is_symbol )
    SYMBOL_RIGHT_SHIFT  ( ">>",  &is_symbol )
    SYMBOL_RIGHT_SHIFT_X( ">>>", &is_symbol )
    SYMBOL_SEMICOLON    ( ";",   &is_symbol )
    SYMBOL_SLASH        ( "/",   &is_symbol )
    SYMBOL_TILDE        ( "~",   &is_symbol )
    SYMBOL_VERTICAL_BAR ( "|",   &is_symbol )
    */
  }

  public void define( int tokenType, String name, int flags )
  {
    int index = tokenType * 2;
    definitions.expandToCount( index + 2 );

    definitions.set( index, flags );

    Integer nameIndex = stringLookup.get( name );
    if (nameIndex != null)
    {
      definitions.set( index+1, (int)nameIndex );
    }
    else
    {
      definitions.set( index+1, strings.count );
      stringLookup.put( name, strings.count );
      strings.add( name );
    }
  }
}

