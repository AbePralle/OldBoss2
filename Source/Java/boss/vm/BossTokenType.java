package boss.vm;

public class BossTokenType
{
  final static public int KEYWORD    = 1;
  final static public int SYMBOL     = 2;
  final static public int STRUCTURAL = 4;

  final static public int EOI        = 0;
  final static public int EOL        = 1;
  final static public int IDENTIFIER = 2;
  final static public int STRING     = 3;
  final static public int INT32      = 4;
}

