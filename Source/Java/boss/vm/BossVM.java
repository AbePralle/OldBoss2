package boss.vm;

import java.io.*;
import java.util.*;

public class BossVM
{
  public BossTokens tokens = new BossTokens();

  static public void main( String[] args )
  {
    BossStringIntLookup table = new BossStringIntLookup();

    System.out.println( table.add( "C" ) );
    System.out.println( table.add( "A" ) );
    System.out.println( table.add( "B" ) );
    System.out.println();

    System.out.println( table.add( "C" ) );
    System.out.println( table.add( "A" ) );
    System.out.println( table.add( "B" ) );
    System.out.println();

    System.out.println( table.get( 0 ) );
    System.out.println( table.get( 1 ) );
    System.out.println( table.get( 2 ) );
    System.out.println();

    System.out.println( "Hello World!" );
  }

  public BossVM()
  {
  }
}

