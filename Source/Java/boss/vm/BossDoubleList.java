package boss.vm;

class BossDoubleList
{
  public double[] data;
  public int      count;

  public BossDoubleList()
  {
  }

  public BossDoubleList( int initialCapacity )
  {
    if (initialCapacity > 0) data = new double[ initialCapacity ];
  }

  public void add( double value )
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
      double[] data = this.data;
      for (int i=minimumCount; --i>=count; )
      {
        data[ i ] = 0;
      }
      this.count = minimumCount;
    }
  }

  public double get( int index )
  {
    return data[ index ];
  }

  public void reserve( int additionalElementCount )
  {
    if (additionalElementCount <= 0) return;
    if (data == null)
    {
      if (additionalElementCount < 10) additionalElementCount = 10;
      data = new double[ additionalElementCount ];
    }
    else
    {
      int requiredCapacity = count + additionalElementCount;
      int currentCapacity = data.length;
      if (requiredCapacity > currentCapacity)
      {
        int doubleCapacity = currentCapacity * 2;
        if (doubleCapacity > requiredCapacity) requiredCapacity = doubleCapacity;
        double[] newData = new double[ requiredCapacity ];
        double[] data = this.data;
        for (int i=requiredCapacity; --i>=0; )
        {
          newData[i] = data[i];
        }
        this.data = newData;
      }
    }
  }

  public void set( int index, double value )
  {
    data[ index ] = value;
  }
}
