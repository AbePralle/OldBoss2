package boss.vm;

public class BossIntList
{
  public int[] data;
  public int   count;

  public BossIntList()
  {
  }

  public BossIntList( int initialCapacity )
  {
    data = new int[ initialCapacity ];
  }

  public void add( int value )
  {
    reserve( 1 );
    data[ count++ ] = value;
  }

  public void clear()
  {
    count = 0;
  }

  public void ensureCapacity( int minimumCapacity )
  {
    reserve( minimumCapacity - count );
  }

  public void expandToCount( int minimumCount )
  {
    if (minimumCount > count)
    {
      ensureCapacity( minimumCount );
      int count = this.count;
      int[] data = this.data;
      for (int i=minimumCount; --i>=count; )
      {
        data[ i ] = 0;
      }
      this.count = minimumCount;
    }
  }

  public int first()
  {
    return data[ 0 ];
  }

  public int get( int index )
  {
    return data[ index ];
  }

  public void insert( int value, int atIndex )
  {
    reserve( 1 );
    int[] data = this.data;
    for (int i=count; --i>=atIndex; )
    {
      data[i+1] = data[i];
    }
    data[ atIndex ] = value;
    ++count;
  }

  public int last()
  {
    return data[ count-1 ];
  }

  public void reserve( int additionalElementCount )
  {
    if (additionalElementCount <= 0) return;
    if (data == null)
    {
      if (additionalElementCount < 10) additionalElementCount = 10;
      data = new int[ additionalElementCount ];
    }
    else
    {
      int requiredCapacity = count + additionalElementCount;
      int currentCapacity = data.length;
      if (requiredCapacity > currentCapacity)
      {
        int doubleCapacity = currentCapacity * 2;
        if (doubleCapacity > requiredCapacity) requiredCapacity = doubleCapacity;
        int[] newData = new int[ requiredCapacity ];
        int[] data = this.data;
        for (int i=requiredCapacity; --i>=0; )
        {
          newData[i] = data[i];
        }
        this.data = newData;
      }
    }
  }

  public void set( int index, int value )
  {
    data[ index ] = value;
  }
}
