package boss.vm;

public class BossTokenType
{
  final static public int KEYWORD    = 1;
  final static public int SYMBOL     = 2;
  final static public int STRUCTURAL = 4;

  final static public int EOI                  =  0;
  final static public int EOL                  =  1;
  final static public int IDENTIFIER           =  2;
  final static public int STRING               =  3;
  final static public int REAL64               =  4;
  final static public int INT64                =  5;
  final static public int INT32                =  6;
  final static public int CHARACTER            =  7;

  final static public int KEYWORD_CLASS        =  8;
  final static public int KEYWORD_ELSE         =  9;
  final static public int KEYWORD_ELSE_IF      = 10;
  final static public int KEYWORD_END_CLASS    = 11;
  final static public int KEYWORD_END_IF       = 12;
  final static public int KEYWORD_END_ROUTINE  = 13;
  final static public int KEYWORD_END_WHILE    = 14;
  final static public int KEYWORD_FALSE        = 15;
  final static public int KEYWORD_GLOBAL       = 16;
  final static public int KEYWORD_IF           = 17;
  final static public int KEYWORD_IMPORT       = 18;
  final static public int KEYWORD_LOCAL        = 19;
  final static public int KEYWORD_METHOD       = 20;
  final static public int KEYWORD_METHODS      = 21;
  final static public int KEYWORD_NULL         = 22;
  final static public int KEYWORD_PRINTLN      = 23;
  final static public int KEYWORD_PROPERTIES   = 24;
  final static public int KEYWORD_RETURN       = 25;
  final static public int KEYWORD_ROUTINE      = 26;
  final static public int KEYWORD_THIS_CALL    = 27;
  final static public int KEYWORD_TRUE         = 28;
  final static public int KEYWORD_UNDEFINED    = 29;
  final static public int KEYWORD_WHILE        = 30;

  final static public int SYMBOL_AMPERSAND     = 31;
  final static public int SYMBOL_ARROW         = 32;
  final static public int SYMBOL_ASTERISK      = 33;
  final static public int SYMBOL_BANG          = 34;
  final static public int SYMBOL_CARET         = 35;
  final static public int SYMBOL_CLOSE_PAREN   = 36;
  final static public int SYMBOL_COLON         = 37;
  final static public int SYMBOL_COLON_COLON   = 38;
  final static public int SYMBOL_COMMA         = 39;
  final static public int SYMBOL_DOLLAR        = 40;
  final static public int SYMBOL_EQUALS        = 41;
  final static public int SYMBOL_EQ            = 42;
  final static public int SYMBOL_GE            = 43;
  final static public int SYMBOL_GT            = 44;
  final static public int SYMBOL_LEFT_SHIFT    = 45;
  final static public int SYMBOL_LE            = 46;
  final static public int SYMBOL_LT            = 47;
  final static public int SYMBOL_MINUS         = 48;
  final static public int SYMBOL_MINUS_MINUS   = 49;
  final static public int SYMBOL_NE            = 50;
  final static public int SYMBOL_OPEN_PAREN    = 51;
  final static public int SYMBOL_PERCENT       = 52;
  final static public int SYMBOL_PERIOD        = 53;
  final static public int SYMBOL_PLUS          = 54;
  final static public int SYMBOL_PLUS_PLUS     = 55;
  final static public int SYMBOL_RIGHT_SHIFT   = 56;
  final static public int SYMBOL_RIGHT_SHIFT_X = 57;
  final static public int SYMBOL_SEMICOLON     = 58;
  final static public int SYMBOL_SLASH         = 59;
  final static public int SYMBOL_TILDE         = 60;
  final static public int SYMBOL_VERTICAL_BAR  = 61;
}

